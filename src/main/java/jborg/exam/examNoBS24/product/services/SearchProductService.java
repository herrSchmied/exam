package jborg.exam.examNoBS24.product.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import jborg.exam.examNoBS24.Query;
import jborg.exam.examNoBS24.product.ProductRepository;
import jborg.exam.examNoBS24.product.model.Product;
import jborg.exam.examNoBS24.product.model.ProductDTO;

@Service
public class SearchProductService implements Query<List<ProductDTO>, String>
{

	private ProductRepository productRepository;

	public SearchProductService(ProductRepository productRepository)
	{
		this.productRepository = productRepository;
	}

	@Override
	public ResponseEntity<List<ProductDTO>> execute(String input)
	{

		List<Product> list = productRepository.findByCategoryOrDescriptionContaining(input);
		List<ProductDTO> dtoList = new ArrayList<>();
		
		for(Product p: list)dtoList.add(new ProductDTO(p));
		
		return ResponseEntity.ok(dtoList);
	}
	
	
}
