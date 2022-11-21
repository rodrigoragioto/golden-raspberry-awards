package gra.h2;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import gra.Producer;
import gra.ports.CreateProducerGateway;

@DataJpaTest
@Import({H2GatewaysConfiguration.class, H2FindGatewaysConfiguration.class})
class H2FindProducerGatewayTest {

	@Autowired
	private H2FindProducerGateway findProducerGateway;

	@Autowired
	private CreateProducerGateway createProducerGateway;

	@Test
	void shouldExecuteSuccessfully() {
		final Producer producer = createProducerGateway.execute(
			Producer.builder()
				.name("Producer")
				.build()
		);

		final Producer find = findProducerGateway.execute(producer.getId());

		assertThat(find.getId(), notNullValue());
		assertThat(find.getName(), is("Producer"));
	}

}
