package jborg.exam.examNoBS24.product;

import jborg.exam.examNoBS24.product.exceptions.ProductNotValideException;
import jborg.exam.examNoBS24.product.model.Product;
import jborg.exam.examNoBS24.product.model.Region;

public class ProductValidator
{

	public static void validate(Product product)
	{
		
		nullCheck(product, "Product");		
		
		nullCheck(product.getId(), "Id");
		if(!(product.getId().trim().length()==36))throw new ProductNotValideException("Id of Product not valide.");
		
		nullCheck(product.getDescription(), "Description");
		if(!(product.getDescription().trim().length()>19))throw new ProductNotValideException("Description too short.");


		nullCheck(product.getPrice(), "Price");
		if(!(product.getPrice()>0.0))throw new ProductNotValideException("Price is Zero or below.");
		
		nullCheck(product.getManufacturer(), "Manufacturer");
		if(product.getManufacturer().trim().equals(""))throw new ProductNotValideException("No Manufacturer");
	
		nullCheck(product.getCategory(), "Category");
		if(product.getCategory().trim().equals(""))throw new ProductNotValideException("No Product Category.");

		nullCheck(product.getCreated_timestamp(), "Created Timestamp");
		
		//Updated timestamp is allowed to be null!!!!
		
		nullCheck(product.getRegion(), "Region");
		
	}
	
	private static void nullCheck(Object obj, String name)
	{
		if(obj==null)throw new ProductNotValideException(name + " can't be null");
	}
}