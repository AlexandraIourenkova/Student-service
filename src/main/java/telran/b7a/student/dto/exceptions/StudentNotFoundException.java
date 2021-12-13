package telran.b7a.student.dto.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class StudentNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9118107565204976641L;
	
	public StudentNotFoundException(int id) {
		super("Student with id " + id + " not found");
	}
}
