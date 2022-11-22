package gra.h2.create;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import gra.h2.H2MovieRepository;
import gra.h2.H2ProducerRepository;
import gra.ports.CreateMovieGateway;
import gra.ports.CreateProducerGateway;

@TestConfiguration
public class H2CreateGatewaysConfiguration {

	@Autowired
	private H2ProducerRepository producerRepository;

	@Autowired
	private H2MovieRepository movieRepository;

	@Bean
	public CreateProducerGateway createProducerGateway() {
		return new H2CreateProducerGateway(producerRepository);
	}

	@Bean
	public CreateMovieGateway createMovieGateway() {
		return new H2CreateMovieGateway(movieRepository, producerRepository);
	}

}

