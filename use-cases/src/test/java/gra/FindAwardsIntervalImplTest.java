package gra;

import static gra.boundaries.input.EmptyInputBoundary.emptyInputBoundary;
import static java.util.Arrays.asList;
import static java.util.Collections.emptySet;
import static java.util.Collections.singletonList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;

import java.util.HashSet;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import gra.boundaries.output.AwardIntervalOutputBoundary;
import gra.boundaries.output.AwardsIntervalOutputBoundary;
import gra.ports.FindProducersGateway;

@SpringBootTest
class FindAwardsIntervalImplTest {

	@TestConfiguration
	static class FindAwardsIntervalImplTestContextConfiguration {

		@Autowired
		private FindProducersGateway findProducersGateway;

		@Bean
		public FindAwardsInterval findAwardsInterval() {
			return new FindAwardsIntervalImpl(findProducersGateway);
		}

	}

	@Autowired
	private FindProducersGateway findProducersGateway;

	@Autowired
	private FindAwardsInterval findAwardsInterval;

	@BeforeEach
	public void before() {
		Mockito.reset(findProducersGateway);
	}

	@Test
	void shouldExecuteSuccessfully() {
		final Producer john = createProducer(
			"John",
			asList(
				createMovie(2000),
				createMovie(1980),
				createMovie(1995)
			));

		final Producer mary = createProducer(
			"Mary",
			asList(
				createMovie(2000),
				createMovie(1998),
				createMovie(1999),
				createMovie(2015)
			));

		given(findProducersGateway.execute())
			.willReturn(new HashSet<>(
				asList(
					john,
					mary
				)));

		final AwardsIntervalOutputBoundary boundary = findAwardsInterval.execute(emptyInputBoundary());

		assertThat(boundary.getMin(), hasSize(2));
		assertThat(boundary.getMin(), hasItems(
			createOutputBoundary(mary.getName(), 1998, 1999, 1),
			createOutputBoundary(mary.getName(), 1999, 2000, 1)
		));

		assertThat(boundary.getMin(), hasSize(2));
		assertThat(boundary.getMax(), hasItems(
			createOutputBoundary(john.getName(), 1980, 1995, 15),
			createOutputBoundary(mary.getName(), 2000, 2015, 15)
		));
	}

	@Test
	void shouldExecuteSuccessfullyWithProducerWithOnlyOneMovie() {
		final Producer mary = createProducer(
			"Mary",
			singletonList(
				createMovie(2000)
			));

		given(findProducersGateway.execute())
			.willReturn(new HashSet<>(
				singletonList(
					mary
				)));

		final AwardsIntervalOutputBoundary boundary = findAwardsInterval.execute(emptyInputBoundary());

		assertThat(boundary.getMin(), empty());
		assertThat(boundary.getMin(), empty());
	}

	@Test
	void shouldExecuteSuccessfullyWithoutProducers() {
		given(findProducersGateway.execute())
			.willReturn(emptySet());

		final AwardsIntervalOutputBoundary boundary = findAwardsInterval.execute(emptyInputBoundary());

		assertThat(boundary.getMin(), empty());
		assertThat(boundary.getMin(), empty());
	}

	public AwardIntervalOutputBoundary createOutputBoundary(String name, Integer previousWin, Integer followingWin, Integer interval) {
		return AwardIntervalOutputBoundary.builder()
			.producer(name)
			.previousWin(previousWin)
			.followingWin(followingWin)
			.interval(interval)
			.build();
	}

	private Producer createProducer(String name, List<Movie> movies) {
		return Producer.builder()
			.id(100L)
			.name(name)
			.movies(new HashSet<>(movies))
			.build();
	}

	private Movie createMovie(Integer year) {
		return Movie.builder()
			.id(100L)
			.name(randomName())
			.year(year)
			.build();
	}

	private String randomName() {
		int leftLimit = 97;
		int rightLimit = 122;
		int targetStringLength = 10;

		return new Random().ints(leftLimit, rightLimit + 1)
			.limit(targetStringLength)
			.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
			.toString();
	}

}
