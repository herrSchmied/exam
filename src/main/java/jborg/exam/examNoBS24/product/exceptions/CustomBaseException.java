package jborg.exam.examNoBS24.product.exceptions;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class CustomBaseException extends RuntimeException
{
	
	private HttpStatus status;
	private ErrorResponse errorResponse;
	
	public CustomBaseException(HttpStatus status, ErrorResponse errorResponse)
	{
		super(errorResponse.getMessage());
		this.status = status;
		this.errorResponse = errorResponse;
	}
	
}
