package gra.h2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import gra.h2.H2AwardRepository;
import gra.h2.H2FindProducerGateway;
import gra.h2.H2MovieRepository;
import gra.h2.H2ProducerRepository;
import gra.ports.FindProducerGateway;

@TestConfiguration
public class H2FindGatewaysConfiguration {

	@Autowired
	private H2ProducerRepository producerRepository;

	@Autowired
	private H2MovieRepository movieRepository;

	@Autowired
	private H2AwardRepository awardRepository;



}

