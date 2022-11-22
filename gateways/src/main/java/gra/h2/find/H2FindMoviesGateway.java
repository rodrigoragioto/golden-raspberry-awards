package gra.h2.find;

import static java.util.stream.Collectors.toList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import gra.Movie;
import gra.h2.H2Movie;
import gra.h2.H2MovieRepository;
import gra.ports.FindMoviesGateway;

@Component
class H2FindMoviesGateway implements FindMoviesGateway {

	@Autowired
	private H2MovieRepository movieRepository;

	@Override
	public List<Movie> execute() {
		return movieRepository.findAll()
			.stream()
			.map(H2Movie::toMovie)
			.collect(toList());
	}

}

