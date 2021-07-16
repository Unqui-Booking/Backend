package ar.edu.unq.tip.unquibooking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Booking registered with admin user")
public class BookingRegisteredWithAdminUserException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public BookingRegisteredWithAdminUserException(String errorMessage) {
		super(errorMessage);
	}
	
}
