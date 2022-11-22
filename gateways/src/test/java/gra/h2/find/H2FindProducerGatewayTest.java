package gra.h2.find;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import gra.Producer;
import gra.h2.H2Producer;
import gra.h2.H2ProducerRepository;

@DataJpaTest
@Import(H2FindGatewaysConfiguration.class)
class H2FindProducerGatewayTest {

	@Autowired
	private H2FindProducerGateway findProducerGateway;

	@Autowired
	private H2ProducerRepository producerRepository;

	@Test
	void shouldExecuteSuccessfully() {
		final H2Producer producer = producerRepository.save(
			H2Producer.builder()
				.name("Quentin Tarantino")
				.build()
		);

		final Producer find = findProducerGateway.execute(producer.getName());

		assertThat(find.getId(), notNullValue());
		assertThat(find.getName(), is(producer.getName()));
	}

}
