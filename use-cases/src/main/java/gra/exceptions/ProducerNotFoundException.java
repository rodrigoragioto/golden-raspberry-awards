package gra.exceptions;

public class ProducerNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ProducerNotFoundException(Long id) {
		super("Producer not found: " + id);
	}

	public ProducerNotFoundException(String name) {
		super("Producer not found: " + name);
	}

}
