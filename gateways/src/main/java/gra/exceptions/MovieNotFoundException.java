package gra.exceptions;

public class MovieNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public MovieNotFoundException(Long id) {
		super("Movie not found: " + id);
	}

}
