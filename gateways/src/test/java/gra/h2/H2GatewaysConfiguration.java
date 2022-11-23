package gra.h2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import gra.ports.FindProducersGateway;

@TestConfiguration
class H2GatewaysConfiguration {

	@Autowired
	private H2ProducerRepository producerRepository;


	@Bean
	public FindProducersGateway findMoviesGateway() {
		return new H2FindProducersGateway(producerRepository);
	}

}

