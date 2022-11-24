package gra;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;

import java.util.HashSet;

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

		assertThat(producer.getMinAwardInterval().getInterval(), is(1));
		assertThat(producer.getMaxAwardInterval().getInterval(), is(10));
	}

	@Test
	void shouldExecuteSuccessfullyWithoutMovies() {
		final Producer producer = Producer.builder()
			.name("Jonny")
			.movies(new HashSet<>(emptyList()))
			.build();

		assertThat(producer.getMinAwardInterval(), nullValue());
		assertThat(producer.getMaxAwardInterval(), nullValue());
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

		assertThat(producer.getMinAwardInterval(), nullValue());
		assertThat(producer.getMaxAwardInterval(), nullValue());
	}

	private Movie createMovie(Integer year) {
		return Movie.builder()
			.id(100L)
			.name("Name")
			.year(year)
			.build();
	}

}
