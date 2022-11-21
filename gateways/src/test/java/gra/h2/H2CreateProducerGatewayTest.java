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

import gra.Producer;

@DataJpaTest
@Import(H2GatewaysConfiguration.class)
class H2CreateProducerGatewayTest {

	@Autowired
	private H2CreateProducerGateway createProducerGateway;

	@Autowired
	private H2ProducerRepository producerRepository;

	@Test
	void shouldExecuteSuccessfully() {
		final Producer producer = createProducerGateway.execute(
			Producer.builder()
				.name("Producer")
				.build()
		);

		assertThat(producer.getId(), notNullValue());
		assertThat(producer.getName(), is("Producer"));

		assertThat(producerRepository.findAll(), not(emptyIterable()));

		final Producer other = createProducerGateway.execute(producer);

		assertThat(other.getId(), is(producer.getId()));
	}

}
