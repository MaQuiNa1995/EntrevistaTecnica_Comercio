package inditex.christian.exception;

public class EntityNotFoundException extends Exception {

	public EntityNotFoundException() {
		super("The entity was not found");
	}

}
