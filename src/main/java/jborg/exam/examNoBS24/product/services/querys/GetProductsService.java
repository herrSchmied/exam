package jborg.exam.examNoBS24.product.services.querys;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import jborg.exam.examNoBS24.product.ProductRepository;
import jborg.exam.examNoBS24.product.model.Product;
import jborg.exam.examNoBS24.product.model.ProductDTO;
import jborg.exam.examNoBS24.product.services.Query;


@Service
public class GetProductsService implements Query<List<ProductDTO>, String>
{

	private ProductRepository productRepository;

	public GetProductsService(ProductRepository productRepository)
	{

		this.productRepository = productRepository;
	}

	@Override
	public ResponseEntity<List<ProductDTO>> execute(String input)
	{

		List<Product> list = productRepository.findAll();
		List<ProductDTO> dtoList = new ArrayList<>();

		for(Product p: list)dtoList.add(new ProductDTO(p));

		return ResponseEntity.ok(dtoList);
	}
}