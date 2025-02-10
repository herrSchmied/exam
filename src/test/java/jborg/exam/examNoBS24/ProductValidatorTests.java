package jborg.exam.examNoBS24;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import jborg.exam.examNoBS24.product.ProductValidator;
import jborg.exam.examNoBS24.product.exceptions.ProductNotValideException;
import jborg.exam.examNoBS24.product.model.Product;
import jborg.exam.examNoBS24.product.model.Region;


@SpringBootTest
public class ProductValidatorTests
{
	
	@Test
	public void valideProduct_validated_NoExceptionThrownTest()
	{
		String name = "Test";
		String description = "Bla bla talk walk melken Try to fill this.";
		double price = 100.01;
		String manufacturer = "TestEngine";
		String category = "Familie";
		Region region = Region.Canada;
		
		Product product = new Product();

		String check = ProductValidator.validate(product);
		assert(check.equals("OK"));
	}

	@Test
	public void notValideProduct_validated_ProductNotValideExceptionThrownTest()
	{
		String name = "Test";
		String description = "Bla bla";
		double price = 100.01;
		String manufacturer = "TestEngine";
		String category = "Familie";
		Region region = Region.Canada;
		
		Product product = new Product();

		
		Exception exception =assertThrows(ProductNotValideException.class, ()->
		{
			ProductValidator.validate(product);
		});
		
		assert(exception.getMessage().equals("Description too short."));
	}

}
