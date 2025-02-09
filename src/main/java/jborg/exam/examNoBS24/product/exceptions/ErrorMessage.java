package jborg.exam.examNoBS24.product.exceptions;

public enum ErrorMessage
{

	product_not_Found("Product not Found."),
	product_not_valide("Product not valide.");
	
	private String message;
	
	private ErrorMessage(String message)
	{
		this.message = message;
	}
	
	public String getMessage()
	{
		return message;
	}
}
