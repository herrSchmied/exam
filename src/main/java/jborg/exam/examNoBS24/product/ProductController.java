package jborg.exam.examNoBS24.product;





import java.util.List;


import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


import jborg.exam.examNoBS24.product.model.ProductDTO;
import jborg.exam.examNoBS24.product.model.SortByCode;
import jborg.exam.examNoBS24.product.services.GetProductService;
import jborg.exam.examNoBS24.product.services.GetProductsOrderedService;
import jborg.exam.examNoBS24.product.services.GetProductsService;
import jborg.exam.examNoBS24.product.services.SearchProductService;





@RestController
public class ProductController
{

	private GetProductService getProductService;

	private GetProductsService getProductsService;
	
	private SearchProductService searchProductService;
	
	private GetProductsOrderedService getProductsOrderedService;
	
	public ProductController(GetProductsService getProductsService, 
			GetProductService getProductService, 
			SearchProductService searchProductService,
			GetProductsOrderedService getProductsOrderedService
			)
	{
		this.getProductService = getProductService;
		this.getProductsService = getProductsService;
		this.searchProductService = searchProductService;
		this.getProductsOrderedService = getProductsOrderedService;
	}

	@GetMapping("/products")
	public ResponseEntity<List<ProductDTO>> listProducts()
	{
		return getProductsService.execute(null);
	}

	@GetMapping("/product/{Id}")
	public ResponseEntity<ProductDTO> getProduct(@PathVariable String Id)
	{
		return getProductService.execute(Id);
	}

	@GetMapping("/pSearch/{Id}")
	public ResponseEntity<List<ProductDTO>> searchProduct(@PathVariable String Id)
	{	
		return searchProductService.execute(Id);
	}

	@GetMapping("/pSort/{Id}")
	public ResponseEntity<List<ProductDTO>> sortProduct(@PathVariable String Id)
	{
		
		return getProductsOrderedService.execute(Id);
	}
}
