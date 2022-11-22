package gra.h2.create;

import org.springframework.stereotype.Component;

import gra.Producer;
import gra.h2.H2Producer;
import gra.h2.H2ProducerRepository;
import gra.ports.CreateProducerGateway;

@Component
class H2CreateProducerGateway implements CreateProducerGateway {

	H2CreateProducerGateway(H2ProducerRepository producerRepository) {
		this.producerRepository = producerRepository;
	}

	private final H2ProducerRepository producerRepository;

	@Override
	public Producer execute(Producer producer) {
		return producerRepository.save(
				H2Producer.builder()
					.id(producer.getId())
					.name(producer.getName())
					.build())
			.toProducer();
	}

}

