package ar.edu.unq.tip.unquibooking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Desk not found")
public class DeskNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	public DeskNotFoundException(String errorMessage) {
		super(errorMessage);
	}
}