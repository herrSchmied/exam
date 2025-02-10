package jborg.exam.examNoBS24.product.services.commands;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import jborg.exam.examNoBS24.product.ProductRepository;
import jborg.exam.examNoBS24.product.ProductValidator;
import jborg.exam.examNoBS24.product.exceptions.ProductNotFoundException;
import jborg.exam.examNoBS24.product.exceptions.ProductNotValideException;
import jborg.exam.examNoBS24.product.model.Product;
import jborg.exam.examNoBS24.product.model.ProductDTO;
import jborg.exam.examNoBS24.product.model.ProductUpdateObject;
import jborg.exam.examNoBS24.product.model.Region;
import jborg.exam.examNoBS24.product.services.Command;
import jborg.exam.examNoBS24.profanityFilter.Config;
import jborg.exam.examNoBS24.profanityFilter.service.ProfanityService;

@Service
public class UpdateProductService implements Command<ProductDTO, ProductUpdateObject>
{

	private Logger logger = LoggerFactory.getLogger(getClass());
	private ProductRepository productRepository;
	private ProfanityService profanityService;

	
	public UpdateProductService(ProductRepository productRepository,
			ProfanityService profanityService)
	{
		this.productRepository = productRepository;
		this.profanityService = profanityService;
	}

	@Override
	public ResponseEntity<ProductDTO> execute(ProductUpdateObject input)
	{

		logger.info("Executing UpdateProductService");
		
		System.out.println(input.getId());
		Optional<Product> opOld = productRepository.findById(input.getId());
		
		if(opOld.isPresent())
		{
			
			Product old = opOld.get();			
			
			String Id = old.getId();
			String name = input.getDto().getName();
			String description = input.getDto().getDescription();
			Double price = input.getDto().getPrice();
			String manufacturer = input.getDto().getManufacturer();
			String category = input.getDto().getCategory();
			Long created_timestamp =  old.getCreated_timestamp();
			Long up_dated_timestamp = System.currentTimeMillis();
			Region region = input.getDto().getRegion();
			
			Product newProduct = new Product(Id, name, description, price, 
					manufacturer, category, created_timestamp, 
					up_dated_timestamp, region);

			String msg = ProductValidator.validate(newProduct);
			
			boolean nameIsProfane = profanityService.execute(newProduct.getName()).getBody();
			boolean descriptionIsProfane = profanityService.execute(newProduct.getDescription()).getBody();
			
			if(nameIsProfane||descriptionIsProfane)throw new ProductNotValideException("Profanity.");
			
			productRepository.save(newProduct);

			return ResponseEntity.ok(new ProductDTO(newProduct));
		}
		
		throw new ProductNotFoundException();
	}

}
