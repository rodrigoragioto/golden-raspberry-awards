package gra;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasItems;

import java.util.HashSet;
import java.util.Random;

import org.junit.jupiter.api.Test;

class ProducerTest {

	@Test
	void shouldExecuteSuccessfully() {
		final Producer producer = Producer.builder()
			.name("Jonny")
			.movies(new HashSet<>(
				asList(
					createMovie(2010),
					createMovie(1980),
					createMovie(1990),
					createMovie(2000),
					createMovie(2001)
				)))
			.build();

		assertThat(producer.getAwardsIntervals(), hasItems(
			createAwardInterval(producer, 1980, 1990, 10),
			createAwardInterval(producer, 1990, 2000, 10),
			createAwardInterval(producer, 2000, 2001, 1),
			createAwardInterval(producer, 2001, 2010, 9)
		));
	}

	@Test
	void shouldExecuteSuccessfullyWithoutMovies() {
		final Producer producer = Producer.builder()
			.name("Jonny")
			.movies(new HashSet<>(emptyList()))
			.build();

		assertThat(producer.getAwardsIntervals(), empty());
	}

	@Test
	void shouldExecuteSuccessfullyWithOnlyOneMovie() {
		final Producer producer = Producer.builder()
			.name("Jonny")
			.movies(new HashSet<>(
				singletonList(
					createMovie(1980)
				)))
			.build();

		assertThat(producer.getAwardsIntervals(), empty());
	}

	@Test
	void shouldExecuteSuccessfullyWithTwoMoviesInTheSameYear() {
		final Producer producer = Producer.builder()
			.name("Jonny")
			.movies(new HashSet<>(
				asList(
					createMovie(1980),
					createMovie(1980)
				)))
			.build();

		assertThat(producer.getAwardsIntervals(), hasItem(
			createAwardInterval(producer, 1980, 1980, 0)
		));
	}

	private static AwardInterval createAwardInterval(Producer producer, Integer previousWin, Integer followingWin, Integer interval) {
		return AwardInterval
			.builder()
			.producer(producer)
			.previousWin(previousWin)
			.followingWin(followingWin)
			.interval(interval)
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
