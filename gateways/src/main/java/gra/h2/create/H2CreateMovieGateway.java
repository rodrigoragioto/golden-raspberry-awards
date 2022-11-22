package gra.h2.create;

import static java.util.stream.Collectors.toList;

import java.util.List;

import org.springframework.stereotype.Component;

import gra.Movie;
import gra.exceptions.ProducerNotFoundException;
import gra.h2.H2Movie;
import gra.h2.H2MovieRepository;
import gra.h2.H2Producer;
import gra.h2.H2ProducerRepository;
import gra.ports.CreateMovieGateway;

@Component
class H2CreateMovieGateway implements CreateMovieGateway {

	H2CreateMovieGateway(H2MovieRepository movieRepository, H2ProducerRepository producerRepository) {
		this.movieRepository = movieRepository;
		this.producerRepository = producerRepository;
	}

	private final H2MovieRepository movieRepository;

	private final H2ProducerRepository producerRepository;

	@Override
	public Movie execute(Movie movie) {
		final List<H2Producer> producers = movie.getProducers()
			.stream()
			.map(producer -> producerRepository.findById(producer.getId())
				.orElseThrow(() -> new ProducerNotFoundException(producer.getId())))
			.collect(toList());

		return movieRepository.save(H2Movie.builder()
				.id(movie.getId())
				.name(movie.getName())
				.year(movie.getYear())
				.winner(movie.isWinner())
				.producers(producers)
				.build())
			.toMovie();
	}

}

