package gra.h2;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import gra.Producer;

@DataJpaTest
@Import(H2GatewaysConfiguration.class)
class H2FindProducersGatewayTest {

	@Autowired
	private H2FindProducersGateway findProducersGateway;

	@Autowired
	private H2ProducerRepository producerRepository;

	@Test
	void shouldExecuteSuccessfully() {
		final Set<Producer> producers = findProducersGateway.execute();
		assertThat(producers, not(empty()));

		assertThat(producers, hasItem(
			allOf(
				hasProperty("name", is("Adam Sandler")),
				hasProperty("movies", hasItem(
					hasProperty("name", is("Jack and Jill"))))
			)));
	}

}
