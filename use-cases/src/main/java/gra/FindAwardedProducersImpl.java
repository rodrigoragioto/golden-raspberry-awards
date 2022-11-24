package gra;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toSet;

import java.util.Map;
import java.util.Objects;
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
		final Set<Producer> producers = findProducersGateway.execute();

		final Map<Integer, Set<AwardInterval>> minIntervals = producers
			.stream()
			.map(Producer::getMinAwardInterval)
			.filter(Objects::nonNull)
			.collect(groupingBy(AwardInterval::getInterval, toSet()));

		final Map<Integer, Set<AwardInterval>> maxIntervals = producers
			.stream()
			.map(Producer::getMaxAwardInterval)
			.filter(Objects::nonNull)
			.collect(groupingBy(AwardInterval::getInterval, toSet()));

		return null;
	}

}
