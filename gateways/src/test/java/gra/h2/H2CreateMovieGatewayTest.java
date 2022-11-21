package gra.h2;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.emptyIterable;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import gra.Movie;
import gra.Producer;

@DataJpaTest
@Import(H2GatewaysConfiguration.class)
class H2CreateMovieGatewayTest {

	@Autowired
	private H2CreateMovieGateway createMovieGateway;

	@Autowired
	private H2CreateProducerGateway createProducerGateway;

	@Autowired
	private H2MovieRepository movieRepository;

	@Test
	void shouldExecuteSuccessfully() {
		final Producer producer = createProducerGateway.execute(
			Producer.builder()
				.name("Producer")
				.build());

		final Movie movie = createMovieGateway.execute(
			Movie.builder()
				.name("Movie")
				.producer(producer)
				.build()
		);

		assertThat(movie.getId(), notNullValue());
		assertThat(movie.getName(), is("Movie"));
		assertThat(movie.getProducer(), is(producer));

		assertThat(movieRepository.findAll(), not(emptyIterable()));

		final Movie other = createMovieGateway.execute(movie);

		assertThat(other.getId(), is(movie.getId()));
	}

}
