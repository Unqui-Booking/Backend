package ar.edu.unq.tip.unquibooking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Seat bad request")
public class SeatBadRequestException extends Exception {

private static final long serialVersionUID = 1L;
	
	public SeatBadRequestException(String errorMessage) {
		super(errorMessage);
	}
	
}
