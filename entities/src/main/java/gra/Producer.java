package gra;

import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Producer {

	private final Long id;

	private final String name;

	private final Set<Movie> movies;

	public AwardInterval getMinAwardInterval() {
		return getAwardIntervalStream()
			.min(comparingInt(AwardInterval::getInterval))
			.orElse(null);
	}

	public AwardInterval getMaxAwardInterval() {
		return getAwardIntervalStream()
			.max(comparingInt(AwardInterval::getInterval))
			.orElse(null);
	}

	private Stream<AwardInterval> getAwardIntervalStream() {
		final List<Movie> orderedMovies = movies
			.stream()
			.sorted(comparingInt(Movie::getYear))
			.collect(toList());

		return IntStream.range(0, orderedMovies.size() - 1)
			.boxed()
			.collect(toMap(orderedMovies::get, i -> orderedMovies.get(i + 1)))
			.entrySet()
			.stream()
			.map(entry -> AwardInterval.builder()
				.producer(this)
				.previousWin(entry.getKey().getYear())
				.followingWin(entry.getValue().getYear())
				.build());
	}

}

