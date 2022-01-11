package dev.luanfernandes.app.service.exceptions;

public class ObjectNotFoundException extends RuntimeException {
	private static final long serialVersionUID = -6764436141570532769L;

	public ObjectNotFoundException(String msg) {
		super(msg);
	}
}
