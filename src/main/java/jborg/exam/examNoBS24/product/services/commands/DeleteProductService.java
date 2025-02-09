package jborg.exam.examNoBS24.product.services.commands;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import jborg.exam.examNoBS24.product.ProductRepository;
import jborg.exam.examNoBS24.product.exceptions.ProductNotFoundException;
import jborg.exam.examNoBS24.product.model.Product;
import jborg.exam.examNoBS24.product.services.Command;

@Service
public class DeleteProductService implements Command<String, String>
{
	
	private Logger logger = LoggerFactory.getLogger(getClass());

	private ProductRepository productRepository;
	
	public DeleteProductService(ProductRepository productRepository)
	{
		this.productRepository = productRepository;
	}

	@Override
	public ResponseEntity<String> execute(String input)
	{
		
		logger.info("Trying to Execute a Product Deletion");
		
		Optional<Product> opProduct = productRepository.findById(input);
		
		if(opProduct.isPresent())
		{
			
			Product product = opProduct.get();
			productRepository.delete(product);
			return ResponseEntity.ok("Deleted Product: " + product.getName());
		}

		throw new ProductNotFoundException();
	}

	
}
