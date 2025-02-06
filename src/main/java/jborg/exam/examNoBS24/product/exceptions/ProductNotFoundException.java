package jborg.exam.examNoBS24.product.exceptions;

public class ProductNotFoundException extends RuntimeException
{

	private String message;
	
	public ProductNotFoundException()
	{
		super("Product Not Found.");
		this.message = "Product Not Found.";
	}
	
	public String getMessage()
	{
		return message;
	}
}
