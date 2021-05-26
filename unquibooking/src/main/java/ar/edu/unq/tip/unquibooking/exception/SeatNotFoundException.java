package ar.edu.unq.tip.unquibooking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Seat not found")
public class SeatNotFoundException extends Exception{
	
	private static final long serialVersionUID = 1L;
	
	public SeatNotFoundException(String errorMessage) {
		super(errorMessage);
	}
}

