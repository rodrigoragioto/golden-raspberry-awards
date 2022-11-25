package gra;

import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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

	public Set<AwardInterval> getAwardsIntervals() {
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
				.interval(entry.getValue().getYear() - entry.getKey().getYear())
				.build())
			.collect(Collectors.toSet());
	}

}
