package gra;

import static java.util.Collections.emptySet;
import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toSet;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Component;

import gra.boundaries.input.EmptyInputBoundary;
import gra.boundaries.output.AwardIntervalOutputBoundary;
import gra.boundaries.output.AwardsIntervalOutputBoundary;
import gra.ports.FindProducersGateway;

@Component
public class FindAwardsIntervalImpl implements FindAwardsInterval {
	
	private final FindProducersGateway findProducersGateway;

	public FindAwardsIntervalImpl(FindProducersGateway findProducersGateway) {
		this.findProducersGateway = findProducersGateway;
	}

	@Override
	public AwardsIntervalOutputBoundary execute(EmptyInputBoundary input) {
		final Set<Producer> producers = findProducersGateway.execute();

		final Map<Integer, Set<AwardInterval>> intervals = producers
			.stream()
			.map(Producer::getAwardsIntervals)
			.flatMap(Collection::stream)
			.collect(groupingBy(AwardInterval::getInterval, toSet()));

		if (intervals.isEmpty()) {
			intervals.put(-1, emptySet());
		}

		final Integer min = intervals.keySet()
			.stream()
			.min(comparingInt(interval -> interval))
			.orElse(-1);

		final Integer max = intervals.keySet()
			.stream()
			.max(comparingInt(interval -> interval))
			.orElse(-1);

		return AwardsIntervalOutputBoundary
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
