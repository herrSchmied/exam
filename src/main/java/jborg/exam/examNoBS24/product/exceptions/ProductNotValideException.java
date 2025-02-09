package jborg.exam.examNoBS24.product.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

public class ProductNotValideException extends CustomBaseException
{
	
	private Logger logger = LoggerFactory.getLogger(getClass());

	private final String message;
	
	public ProductNotValideException(String message)
	{
		super(HttpStatus.BAD_REQUEST, new ErrorResponse(message));
		this.message = message;
		
		logger.error(message + " ProductNotValideException thrown.");
	}
	
	public String getMessage()
	{
		return message;
	}
}
