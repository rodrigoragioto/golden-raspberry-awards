package gra;

import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toSet;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Component;

import gra.boundaries.input.EmptyInputBoundary;
import gra.boundaries.output.AwardIntervalOutputBoundary;
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

		final Map<Integer, Set<AwardInterval>> intervals = producers
			.stream()
			.map(Producer::getAwardsIntervals)
			.flatMap(Collection::stream)
			.collect(groupingBy(AwardInterval::getInterval, toSet()));

		final Integer min = intervals.keySet()
			.stream()
			.min(comparingInt(interval -> interval))
			.orElse(null);

		final Integer max = intervals.keySet()
			.stream()
			.max(comparingInt(interval -> interval))
			.orElse(null);

		return FindAwardedProducersOutputBoundary
			.builder()
			.min(createAwardIntervalOutputBoundary(intervals.get(min)))
			.max(createAwardIntervalOutputBoundary(intervals.get(max)))
			.build();
	}

	private Set<AwardIntervalOutputBoundary> createAwardIntervalOutputBoundary(Set<AwardInterval> intervals) {
		return intervals.stream().map(awardInterval ->
				AwardIntervalOutputBoundary.builder()
					.producer(awardInterval.getProducer().getName())
					.previousWin(awardInterval.getPreviousWin())
					.followingWin(awardInterval.getFollowingWin())
					.interval(awardInterval.getInterval())
					.build())
			.collect(toSet());
	}

}
