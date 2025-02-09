package jborg.exam.examNoBS24.product.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProductNotValideException extends RuntimeException
{
	
	private Logger logger = LoggerFactory.getLogger(getClass());

	private final String message;
	
	public ProductNotValideException(String message)
	{
		super(message);
		this.message = message;
		
		logger.error(message + " ProductNotValideException thrown.");
	}
	
	public String getMessage()
	{
		return message;
	}
}
