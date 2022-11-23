package gra;

import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.toSet;

import java.util.Set;
import java.util.stream.Stream;

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
			.filter(producer -> producer.getMovies().size() > 1)
			.map(producer -> {
					final Stream<Movie> moviesStream = producer.getMovies().stream();

					final Integer previousWin = moviesStream.min(comparingInt(Movie::getYear))
						.orElseThrow(() -> new RuntimeException("min"))
						.getYear();

					final Integer followingWin = moviesStream.max(comparingInt(Movie::getYear))
						.orElseThrow(() -> new RuntimeException("max"))
						.getYear();

					return AwardedProducer.builder()
						.producer(producer)
						.interval(followingWin - previousWin)
						.previousWin(previousWin)
						.followingWin(followingWin)
						.build();
				}
			).collect(toSet());


		return null;
	}

}
