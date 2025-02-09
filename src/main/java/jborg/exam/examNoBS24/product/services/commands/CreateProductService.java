package jborg.exam.examNoBS24.product.services.commands;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import jborg.exam.examNoBS24.product.ProductRepository;
import jborg.exam.examNoBS24.product.ProductValidator;
import jborg.exam.examNoBS24.product.exceptions.ProductNotValideException;
import jborg.exam.examNoBS24.product.model.Product;
import jborg.exam.examNoBS24.product.model.ProductDTO;
import jborg.exam.examNoBS24.product.model.Region;
import jborg.exam.examNoBS24.product.services.Command;
import jborg.exam.examNoBS24.profanityFilter.Config;
import jborg.exam.examNoBS24.profanityFilter.service.ProfanityService;

@Service
public class CreateProductService implements Command<ProductDTO, ProductDTO>
{

	private Logger logger = LoggerFactory.getLogger(getClass());
	private ProductRepository productRepository;
	private ProfanityService profanityService;

	
	public CreateProductService(ProductRepository productRepository,
								ProfanityService profanityService)
	{
		this.productRepository = productRepository;
		this.profanityService = profanityService;
	}


	@Override
	public ResponseEntity<ProductDTO> execute(ProductDTO dto)
	{
		
		logger.info("Executing CreateProductService");

		Double price = dto.getPrice();
		Region region = dto.getRegion();
		String name = dto.getName();
		String description = dto.getDescription();
		String manufacturer = dto.getManufacturer();
		String category = dto.getCategory();
		
		Product toBeSaved = new Product(price, region, name, description, manufacturer, category);
		
		String msg = ProductValidator.validate(toBeSaved);
		
		boolean nameIsProfane = profanityService.execute(toBeSaved.getName()).getBody();
		boolean descriptionIsProfane = profanityService.execute(toBeSaved.getName()).getBody();
		
		if(nameIsProfane||descriptionIsProfane)throw new ProductNotValideException("Profanity.");

		
		productRepository.save(toBeSaved);
		
		return ResponseEntity.ok(dto);
	}



}
