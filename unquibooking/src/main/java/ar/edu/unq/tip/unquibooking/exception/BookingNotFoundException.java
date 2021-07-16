package ar.edu.unq.tip.unquibooking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Booking not found")
public class BookingNotFoundException extends Exception{
	
	private static final long serialVersionUID = 1L;
	
	public BookingNotFoundException(String errorMessage) {
		super(errorMessage);
	}
	
}

