package gra.h2;

import static java.lang.Integer.parseInt;
import static java.nio.file.Files.readAllLines;

import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import gra.DataLoader;

@Component
public class H2DataLoader implements DataLoader {

	@Autowired
	private H2ProducerRepository producerRepository;

	@Autowired
	private H2MovieRepository movieRepository;

	@Override
	public void execute(String fileName) throws Exception {
		final Map<String, Set<H2Movie>> map = new HashMap<>();
		readAllLines(Paths.get(fileName)).forEach(line -> processLine(map, line));
		map.forEach(this::createProducer);
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
		map.putIfAbsent(producer, new HashSet<>());
		map.get(producer).add(movie);
	}

	private void createProducer(String name, Set<H2Movie> movies) {
		producerRepository.save(
			H2Producer.builder()
				.name(name)
				.movies(movies)
				.build()
		);
	}

}
