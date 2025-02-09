package jborg.exam.examNoBS24.product.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

public class ProductNotFoundException extends CustomBaseException
{

	private Logger logger = LoggerFactory.getLogger(getClass());

	private String message;
	
	public ProductNotFoundException()
	{
		super(HttpStatus.NOT_FOUND, new ErrorResponse(ErrorMessage.product_not_Found.getMessage()));
		this.message = ErrorMessage.product_not_Found.getMessage();
		
		logger.error(message + " ProductNotFoundException thrown.");
	}
	
	public String getMessage()
	{
		return message;
	}
}
