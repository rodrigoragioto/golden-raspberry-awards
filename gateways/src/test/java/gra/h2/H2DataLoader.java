package gra.h2;

import static java.lang.Integer.parseInt;
import static java.nio.charset.StandardCharsets.UTF_8;
import static java.nio.file.Files.readAllLines;
import static java.nio.file.Paths.get;
import static java.util.Objects.requireNonNull;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class H2DataLoader implements ApplicationRunner {

	private final H2ProducerRepository producerRepository;

	private final H2MovieRepository movieRepository;

	public H2DataLoader(H2ProducerRepository producerRepository, H2MovieRepository movieRepository) {
		this.producerRepository = producerRepository;
		this.movieRepository = movieRepository;
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		final Map<String, Set<H2Movie>> map = new HashMap<>();
		getLines().forEach(line -> processLine(map, line));
		map.forEach(this::createProducer);
	}

	private List<String> getLines() throws Exception {
		final List<String> lines = readAllLines(get(requireNonNull(getClass().getClassLoader().getResource("movielist.csv")).toURI()), UTF_8);
		lines.remove(0);
		return lines;
	}

	private void processLine(Map<String, Set<H2Movie>> map, String line) {
		final String[] data = line.split(";");
		if (!isWinner(data)) {
			return;
		}

		createProducers(map, data[3], createMovie(data));
	}

	private boolean isWinner(String[] data) {
		return data.length == 5 && "yes".equals(data[4]);
	}

	private H2Movie createMovie(String[] data) {
		return movieRepository.save(
			H2Movie.builder()
				.name(data[1].trim())
				.year(parseInt(data[0]))
				.build()
		);
	}

	private void createProducers(Map<String, Set<H2Movie>> map, String producers, H2Movie movie) {
		Arrays.stream(producers.split(","))
			.map(producer -> Arrays.stream(producer.split(" and ")))
			.reduce(Stream.empty(), Stream::concat)
			.filter(producer -> producer != null && !producer.trim().equals(""))
			.forEach(producer -> addProducer(map, producer.trim(), movie));
	}

	private void addProducer(Map<String, Set<H2Movie>> map, String producer, H2Movie movie) {
		if (!map.containsKey(producer)) {
			map.put(producer, new HashSet<>());
		}
		map.get(producer).add(movie);
	}

	private H2Producer createProducer(String name, Set<H2Movie> movies) {
		return producerRepository.save(
			H2Producer.builder()
				.name(name)
				.movies(movies)
				.build()
		);
	}

}
