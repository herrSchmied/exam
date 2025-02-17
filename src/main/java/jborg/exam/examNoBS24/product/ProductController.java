package jborg.exam.examNoBS24.product;





import java.util.List;


import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jborg.exam.examNoBS24.product.model.Product;
import jborg.exam.examNoBS24.product.model.ProductDTO;
import jborg.exam.examNoBS24.product.model.ProductUpdateObject;
import jborg.exam.examNoBS24.product.model.SortByCode;
import jborg.exam.examNoBS24.product.services.commands.CreateProductService;
import jborg.exam.examNoBS24.product.services.commands.DeleteProductService;
import jborg.exam.examNoBS24.product.services.commands.UpdateProductService;
import jborg.exam.examNoBS24.product.services.querys.GetProductService;
import jborg.exam.examNoBS24.product.services.querys.GetProductsOrderedService;
import jborg.exam.examNoBS24.product.services.querys.GetProductsService;
import jborg.exam.examNoBS24.product.services.querys.SearchProductService;





@RestController
public class ProductController
{

	private GetProductService getProductService;
	private GetProductsService getProductsService;
	private SearchProductService searchProductService;
	private GetProductsOrderedService getProductsOrderedService;
	private CreateProductService createProductService;
	private UpdateProductService updateProductService;
	private DeleteProductService deleteProductService;
	
	public ProductController(GetProductsService getProductsService, 
			GetProductService getProductService, 
			SearchProductService searchProductService,
			GetProductsOrderedService getProductsOrderedService,
			CreateProductService createProductService,
			UpdateProductService updateProductService,
			DeleteProductService deleteProductService
			)
	{
		this.getProductService = getProductService;
		this.getProductsService = getProductsService;
		this.searchProductService = searchProductService;
		this.getProductsOrderedService = getProductsOrderedService;
		this.createProductService = createProductService;
		this.updateProductService = updateProductService;
		this.deleteProductService = deleteProductService;
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

	@GetMapping("/pSearch/{searchStr}")
	public ResponseEntity<List<ProductDTO>> searchProduct(@PathVariable String searchStr)
	{	
		return searchProductService.execute(searchStr);
	}

	@GetMapping("/pSort/{Id}")
	public ResponseEntity<List<ProductDTO>> sortProduct(@PathVariable String Id)
	{
		
		return getProductsOrderedService.execute(Id);
	}
	
	@PreAuthorize("hasRole('basicuser') or hasRole('admin')")
	@PostMapping("/createproduct")
	public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO dto)
	{
		
		System.out.println("Hi");
		return createProductService.execute(dto);
	}
	
	@PreAuthorize("hasRole('basicuser') or hasRole('admin')")
	@PutMapping("/product/{Id}")
	public ResponseEntity<ProductDTO> updateProduct(@PathVariable String Id, @RequestBody ProductDTO dto)
	{
		
		ProductUpdateObject puo = new ProductUpdateObject(Id, dto);
		return updateProductService.execute(puo);
	}
	
	@PreAuthorize("hasRole('admin')")
	@DeleteMapping("/delete/{Id}")
	public ResponseEntity<String> deleteProduct(@PathVariable String Id)
	{
		return deleteProductService.execute(Id);
	}
}