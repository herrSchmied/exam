package jborg.exam.examNoBS24.product.services.querys;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import jborg.exam.examNoBS24.product.ProductRepository;
import jborg.exam.examNoBS24.product.model.Product;
import jborg.exam.examNoBS24.product.model.ProductDTO;
import jborg.exam.examNoBS24.product.model.SortByCode;
import jborg.exam.examNoBS24.product.services.Query;

@Service
public class GetProductsOrderedService implements Query<List<ProductDTO>, String>
{

	private Logger logger = LoggerFactory.getLogger(getClass());

	private ProductRepository productRepository;

	public GetProductsOrderedService(ProductRepository productRepository)
	{
		this.productRepository = productRepository;
	}

	@Override
	public ResponseEntity<List<ProductDTO>> execute(String input)
	{
		
		logger.info("Executing GetProductsOrderedService");

		if(!SortByCode.isKnownSortCode(input))throw new RuntimeException("Unknown sort Code.");

		Comparator<ProductDTO> nameComparator = (p1, p2)->
		{
			return p1.getName().compareTo(p2.getName());
		};

		Comparator<ProductDTO> priceComparator = (p1, p2)->
		{
			return (int) (p1.getPrice()-p2.getPrice());
		};
		
		List<Product> list = productRepository.findAll();		
		List<ProductDTO> dtoList = new ArrayList<>();
		
		for(Product p: list)dtoList.add(new ProductDTO(p));
			
		if(input.equals(SortByCode.alphaNum))dtoList.sort(nameComparator);
		
		if(input.equals(SortByCode.price))dtoList.sort(priceComparator);

		return ResponseEntity.ok(dtoList);
	}

}
