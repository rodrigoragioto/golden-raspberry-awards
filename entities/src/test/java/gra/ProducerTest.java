package gra;

import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.util.HashSet;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

class ProducerTest {

	@Test
	void shouldExecuteSuccessfully() {
		final Producer producer = Producer.builder()
			.name("Jonny")
			.movies(new HashSet<>(
				asList(
					createMovie(1980),
					createMovie(1990),
					createMovie(2000),
					createMovie(2001),
					createMovie(2010)
				)))
			.build();

		assertThat(producer.getMinAwardInterval().getInterval(), is(1900));
	}

	private Movie createMovie(Integer year) {
		return Movie.builder()
			.id(100L)
			.name("Name")
			.year(year)
			.build();
	}
}
