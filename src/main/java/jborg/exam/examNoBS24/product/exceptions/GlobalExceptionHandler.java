package jborg.exam.examNoBS24.product.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler
{

	@ExceptionHandler(CustomBaseException.class)
	public ResponseEntity<ErrorResponse> handleException(CustomBaseException exception)
	{
		HttpStatus status = exception.getStatus();
		ErrorResponse errorResponse = exception.getErrorResponse();
		return ResponseEntity.status(status).body(errorResponse);
	}
}
