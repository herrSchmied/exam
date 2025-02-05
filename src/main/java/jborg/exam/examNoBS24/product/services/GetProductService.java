package jborg.exam.examNoBS24.product.services;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import jborg.exam.examNoBS24.Query;
import jborg.exam.examNoBS24.product.ProductRepository;
import jborg.exam.examNoBS24.product.model.Product;
import jborg.exam.examNoBS24.product.model.ProductDTO;

@Repository
public class GetProductService implements Query<ProductDTO, String>
{

	private ProductRepository productRepository;
	
	public GetProductService(ProductRepository productRepository)
	{
		this.productRepository = productRepository;
	}

	@Override
	public ResponseEntity<ProductDTO> execute(String input)
	{
		
		Optional<Product> opProduct = productRepository.findById(input); 
		
		if(opProduct.isPresent())
		{
			
			ProductDTO dto = new ProductDTO(opProduct.get());
			
			return ResponseEntity.ok(dto);
		}
		
		throw new RuntimeException("Product Not Found!");
	}

}
