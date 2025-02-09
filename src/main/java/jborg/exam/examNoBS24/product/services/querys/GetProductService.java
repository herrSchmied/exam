package jborg.exam.examNoBS24.product.services.querys;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import jborg.exam.examNoBS24.product.ProductRepository;
import jborg.exam.examNoBS24.product.model.Product;
import jborg.exam.examNoBS24.product.model.ProductDTO;
import jborg.exam.examNoBS24.product.services.Query;

@Repository
public class GetProductService implements Query<ProductDTO, String>
{

	private Logger logger = LoggerFactory.getLogger(getClass());

	private ProductRepository productRepository;
	
	public GetProductService(ProductRepository productRepository)
	{
		this.productRepository = productRepository;
	}

	@Override
	@Cacheable("productCache")
	public ResponseEntity<ProductDTO> execute(String input)
	{
		
		logger.info("Executing GetProductService");

		Optional<Product> opProduct = productRepository.findById(input); 
		
		if(opProduct.isPresent())
		{
			
			ProductDTO dto = new ProductDTO(opProduct.get());
			
			return ResponseEntity.ok(dto);
		}
		
		throw new RuntimeException("Product Not Found!");
	}

}
