package gra.h2;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import gra.Movie;
import gra.Producer;
import gra.exceptions.ProducerNotFoundException;
import gra.h2.H2Movie;
import gra.h2.H2MovieRepository;
import gra.h2.H2Producer;
import gra.h2.H2ProducerRepository;
import gra.ports.CreateMovieGateway;
import gra.ports.CreateProducerGateway;
import gra.ports.FindProducerGateway;

@Component
class H2CreateMovieGateway implements CreateMovieGateway {

	H2CreateMovieGateway(H2MovieRepository movieRepository, H2FindProducerGateway findProducerGateway) {
		this.movieRepository = movieRepository;
		this.findProducerGateway = findProducerGateway;
	}

	private final H2MovieRepository movieRepository;

	private final H2FindProducerGateway findProducerGateway;

	@Override
	public Movie execute(Movie movie) {
		final H2Producer producer = findProducerGateway.execute(movie.getProducer().getId());

		return movieRepository.save(H2Movie.builder().name(movie.getName()).producer(producer).build()).toMovie();
	}
}

