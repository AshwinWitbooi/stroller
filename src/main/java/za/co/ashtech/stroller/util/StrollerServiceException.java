package za.co.ashtech.stroller.util;

public class StrollerServiceException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public StrollerServiceException(String message) {
		super(message);
	}

	public StrollerServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public StrollerServiceException(Throwable cause) {
		super(cause);
	}

}
