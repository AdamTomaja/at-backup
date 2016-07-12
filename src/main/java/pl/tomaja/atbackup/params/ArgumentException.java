package pl.tomaja.atbackup.params;

/**
 * @author Adam Tomaja
 */
public class ArgumentException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ArgumentException(String message, Throwable cause) {
		super(message, cause);
	}

	public ArgumentException(String message) {
		super(message);
	}
}
