package gra.exceptions;

public class AwardNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public AwardNotFoundException(Long id) {
		super("Award not found: " + id);
	}

}
