package jborg.exam.examNoBS24.product.services.commands;

import java.util.UUID;

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

		String id = UUID.randomUUID().toString();
		String name = dto.getName();
		Double price = dto.getPrice();
		String description = dto.getDescription();
		String manufacturer = dto.getManufacturer();
		String category = dto.getCategory();
		Long created_timestamp = System.currentTimeMillis();
		Long up_dated_timestamp = null;
		Region region = dto.getRegion();
		
		Product toBeSaved = new Product(id, name, description, price, manufacturer, category,
				created_timestamp, up_dated_timestamp, region);

		String msg = ProductValidator.validate(toBeSaved);
		
		boolean nameIsProfane = profanityService.execute(toBeSaved.getName()).getBody();
		boolean descriptionIsProfane = profanityService.execute(toBeSaved.getName()).getBody();
		
		if(nameIsProfane||descriptionIsProfane)throw new ProductNotValideException("Profanity.");

		
		productRepository.save(toBeSaved);
		
		return ResponseEntity.ok(dto);
	}



}
