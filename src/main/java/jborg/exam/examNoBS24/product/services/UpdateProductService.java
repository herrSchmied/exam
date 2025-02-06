package jborg.exam.examNoBS24.product.services;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import jborg.exam.examNoBS24.Command;
import jborg.exam.examNoBS24.product.ProductRepository;
import jborg.exam.examNoBS24.product.ProductValidator;
import jborg.exam.examNoBS24.product.exceptions.ProductNotFoundException;
import jborg.exam.examNoBS24.product.model.Product;
import jborg.exam.examNoBS24.product.model.ProductDTO;

@Service
public class UpdateProductService implements Command<ProductDTO, Product>
{

	private ProductRepository productRepository;

	public UpdateProductService(ProductRepository productRepository)
	{
		this.productRepository = productRepository;
	}

	@Override
	public ResponseEntity<ProductDTO> execute(Product input)
	{

		Optional<Product> opOld = productRepository.findById(input.getId());
		
		if(opOld.isPresent())
		{
			
			Product old = opOld.get();
			productRepository.delete(old);
			
			input.setCreated_timestamp(old.getCreated_timestamp());
			input.setUp_dated_timestamp(System.currentTimeMillis());
			
			ProductValidator.validate(input);
			
			productRepository.save(input);
			
			ProductDTO dto = new ProductDTO(input);
			
			return ResponseEntity.ok(dto);
		}
		
		throw new ProductNotFoundException();
	}

}
