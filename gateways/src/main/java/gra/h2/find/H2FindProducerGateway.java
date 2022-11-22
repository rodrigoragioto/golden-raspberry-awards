package gra.h2.find;

import org.springframework.stereotype.Component;

import gra.Producer;
import gra.exceptions.ProducerNotFoundException;
import gra.h2.H2ProducerRepository;
import gra.ports.FindProducerGateway;

@Component
class H2FindProducerGateway implements FindProducerGateway {

	H2FindProducerGateway(H2ProducerRepository producerRepository) {
		this.producerRepository = producerRepository;
	}

	private final H2ProducerRepository producerRepository;

	@Override
	public Producer execute(String name) {
		return producerRepository.findByName(name)
			.orElseThrow(() -> new ProducerNotFoundException(name))
			.toProducer();
	}

}

