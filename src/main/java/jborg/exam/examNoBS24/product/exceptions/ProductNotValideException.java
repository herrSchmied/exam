package jborg.exam.examNoBS24.product.exceptions;

public class ProductNotValideException extends RuntimeException
{
	
	private final String message;
	
	public ProductNotValideException(String message)
	{
		super(message);
		
		this.message = message;
	}
	
	public String getMessage()
	{
		return message;
	}
}
