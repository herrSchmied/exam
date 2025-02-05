package jborg.exam.examNoBS24.product.model;

public enum Region
{
	
	USA("USA"),
	Canada("Canada");
	
	private String message;
	
	Region(String message)
	{
		this.message = message;
	}
	
	public String getMessage()
	{
		return message;
	}
}
