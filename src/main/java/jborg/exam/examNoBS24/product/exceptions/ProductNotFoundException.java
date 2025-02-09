package jborg.exam.examNoBS24.product.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProductNotFoundException extends RuntimeException
{

	private Logger logger = LoggerFactory.getLogger(getClass());

	private String message;
	
	public ProductNotFoundException()
	{
		super("Product Not Found.");
		this.message = "Product Not Found.";
		logger.error(message + " ProductNotFoundException thrown.");
	}
	
	public String getMessage()
	{
		return message;
	}
}
