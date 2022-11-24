package gra;

import static java.util.stream.Collectors.toSet;

import java.util.Set;

import org.springframework.stereotype.Component;

import gra.boundaries.input.EmptyInputBoundary;
import gra.boundaries.output.FindAwardedProducersOutputBoundary;
import gra.ports.FindProducersGateway;

@Component
public class FindAwardedProducersImpl implements FindAwardedProducers {
	private final FindProducersGateway findProducersGateway;

	public FindAwardedProducersImpl(FindProducersGateway findProducersGateway) {
		this.findProducersGateway = findProducersGateway;
	}

	@Override
	public FindAwardedProducersOutputBoundary execute(EmptyInputBoundary input) {
		final Set<AwardedProducer> producers = findProducersGateway.execute()
			.stream()
			.map(AwardedProducer::new)
			.collect(toSet());


		return null;
	}

}
