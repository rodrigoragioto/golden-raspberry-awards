package gra.h2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import gra.ports.CreateAwardGateway;
import gra.ports.CreateMovieGateway;
import gra.ports.CreateProducerGateway;
import gra.ports.FindProducerGateway;

@TestConfiguration
public class H2GatewaysConfiguration {

	@Autowired
	private H2ProducerRepository producerRepository;

	@Autowired
	private H2MovieRepository movieRepository;

	@Autowired
	private H2AwardRepository awardRepository;

	@Bean
	public CreateProducerGateway createProducerGateway() {
		return new H2CreateProducerGateway(producerRepository);
	}

	@Bean
	public CreateMovieGateway createMovieGateway() {
		return new H2CreateMovieGateway(movieRepository, producerRepository);
	}

	@Bean
	public CreateAwardGateway createAwardGateway() {
		return new H2CreateAwardGateway(awardRepository);
	}

	@Bean
	public FindProducerGateway findProducerGateway() {
		return new H2FindProducerGateway(producerRepository);
	}

}

