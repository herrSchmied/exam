package jborg.exam.examNoBS24.product.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import jborg.exam.examNoBS24.Command;
import jborg.exam.examNoBS24.product.ProductRepository;
import jborg.exam.examNoBS24.product.ProductValidator;
import jborg.exam.examNoBS24.product.model.Product;
import jborg.exam.examNoBS24.product.model.ProductDTO;
import jborg.exam.examNoBS24.product.model.Region;

@Service
public class CreateProductService implements Command<ProductDTO, ProductDTO>
{

	private ProductRepository productRepository;
	
	
	public CreateProductService(ProductRepository productRepository)
	{
		this.productRepository = productRepository;
	}


	@Override
	public ResponseEntity<ProductDTO> execute(ProductDTO dto)
	{
		
		Double price = dto.getPrice();
		Region region = dto.getRegion();
		String name = dto.getName();
		String description = dto.getDescription();
		String manufacturer = dto.getManufacturer();
		String category = dto.getCategory();
		
		Product toBeSaved = new Product(price, region, name, description, manufacturer, category);
		
		ProductValidator.validate(toBeSaved);
		
		productRepository.save(toBeSaved);
		
		return ResponseEntity.ok(dto);
	}



}
