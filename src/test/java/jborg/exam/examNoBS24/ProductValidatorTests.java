package jborg.exam.examNoBS24;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.UUID;

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
		
		String id = UUID.randomUUID().toString();
		String name = "Test";
		String description = "Bla bla talk walk melken Try to fill this.";
		double price = 100.01;
		String manufacturer = "TestEngine";
		String category = "Familie";
		Long created = System.currentTimeMillis();
		Long updated = null;
		Region region = Region.Canada;
		
		Product product = new Product(id, name, description, price, manufacturer, category, created, updated, region);

		String check = ProductValidator.validate(product);
		assert(check.equals("OK"));
	}

	@Test
	public void notValideProduct_validated_ProductNotValideExceptionThrownTest()
	{
		String id = UUID.randomUUID().toString();
		String name = "Test";
		String description = "Bla bla";
		double price = 100.01;
		String manufacturer = "TestEngine";
		String category = "Familie";
		Region region = Region.Canada;
		Long created = System.currentTimeMillis();
		Long updated = null;
		
		Product product = new Product(id, name, description, price, manufacturer, category, created, updated, region);

		
		Exception exception =assertThrows(ProductNotValideException.class, ()->
		{
			ProductValidator.validate(product);
		});
		
		assert(exception.getMessage().equals("Description too short."));
	}

}
