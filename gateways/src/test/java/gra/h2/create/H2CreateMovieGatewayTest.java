package gra.h2.create;

import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.emptyIterable;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import gra.Movie;
import gra.Producer;
import gra.h2.H2MovieRepository;
import gra.h2.H2Producer;
import gra.h2.H2ProducerRepository;

@DataJpaTest
@Import(H2CreateGatewaysConfiguration.class)
class H2CreateMovieGatewayTest {

	@Autowired
	private H2CreateMovieGateway createMovieGateway;

	@Autowired
	private H2MovieRepository movieRepository;

	@Autowired
	private H2ProducerRepository producerRepository;

	@Test
	void shouldExecuteSuccessfully() {
		final Producer yoram = producerRepository.save(
				H2Producer.builder()
					.name("Yoram Globus")
					.build())
			.toProducer();

		final Producer menahem = producerRepository.save(
				H2Producer.builder()
					.name("Menahem Golan")
					.build())
			.toProducer();

		final Movie movie = createMovieGateway.execute(
			Movie.builder()
				.name("Cobra")
				.year(1986)
				.winner(true)
				.producers(asList(yoram, menahem))
				.build()
		);

		assertThat(movie.getId(), notNullValue());
		assertThat(movie.getName(), is("Cobra"));
		assertThat(movie.getYear(), is(1986));
		assertThat(movie.isWinner(), is(true));
		assertThat(movie.getProducers(), hasItems(yoram, menahem));

		assertThat(movieRepository.findAll(), not(emptyIterable()));

		final Movie other = createMovieGateway.execute(movie);

		assertThat(other.getId(), is(movie.getId()));
	}

}
