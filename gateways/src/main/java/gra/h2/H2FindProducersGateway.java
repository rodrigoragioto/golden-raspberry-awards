package gra.h2;

import static java.util.stream.Collectors.toSet;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import gra.Producer;
import gra.ports.FindProducersGateway;

@Component
public class H2FindProducersGateway implements FindProducersGateway {

	@Autowired
	private H2ProducerRepository producerRepository;

	@Override
	public Set<Producer> execute() {
		return producerRepository.findAll()
			.stream()
			.map(H2Producer::toProducer)
			.collect(toSet());
	}

}

