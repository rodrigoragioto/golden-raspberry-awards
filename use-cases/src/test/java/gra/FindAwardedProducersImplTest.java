package gra;

import static gra.boundaries.input.EmptyInputBoundary.emptyInputBoundary;
import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import gra.boundaries.output.FindAwardedProducersOutputBoundary;
import gra.ports.FindProducersGateway;

class FindAwardedProducersImplTest {

	private final FindProducersGateway findProducersGateway = mock(FindProducersGateway.class);

	private final FindAwardedProducers findAwardedProducers = new FindAwardedProducersImpl(findProducersGateway);

	@BeforeEach
	public void before() {
		Mockito.reset(findProducersGateway);
	}

	@Test
	void shouldExecuteSuccessfully() {
		when(findProducersGateway.execute())
			.thenReturn(new HashSet<>(asList(
				createProducer(asList(
					createMovie(2000),
					createMovie(1980),
					createMovie(1995)
				))
			)));


		final FindAwardedProducersOutputBoundary boundary = findAwardedProducers.execute(emptyInputBoundary());

		assertThat(boundary.getMax(), hasItem(hasProperty("interval", is(0))));
	}

	private Producer createProducer(List<Movie> movies) {
		return Producer.builder()
			.id(100L)
			.name(randomName())
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
		byte[] array = new byte[7];
		new Random().nextBytes(array);
		return new String(array, StandardCharsets.UTF_8);
	}

}
