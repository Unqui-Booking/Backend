package ar.edu.unq.tip.unquibooking.exception;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Wrong desk")
public class DeskBadRequest extends Exception {
	private static final long serialVersionUID = 1L;
	
	public DeskBadRequest(String errorMessage) {
		super(errorMessage);
	}
	
}

