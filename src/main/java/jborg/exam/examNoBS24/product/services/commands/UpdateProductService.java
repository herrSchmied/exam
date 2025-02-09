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
import jborg.exam.examNoBS24.product.services.Command;
import jborg.exam.examNoBS24.profanityFilter.Config;
import jborg.exam.examNoBS24.profanityFilter.service.ProfanityService;

@Service
public class UpdateProductService implements Command<ProductDTO, Product>
{

	private Logger logger = LoggerFactory.getLogger(getClass());
	private ProductRepository productRepository;
	private ProfanityService profanityService;

	
	public UpdateProductService(ProductRepository productRepository,
			ProfanityService profanityService)
	{
		this.productRepository = productRepository;
	}

	@Override
	public ResponseEntity<ProductDTO> execute(Product input)
	{

		logger.info("Executing UpdateProductService");
		Optional<Product> opOld = productRepository.findById(input.getId());
		
		if(opOld.isPresent())
		{
			
			Product old = opOld.get();
			productRepository.delete(old);
			
			input.setCreated_timestamp(old.getCreated_timestamp());
			input.setUp_dated_timestamp(System.currentTimeMillis());
			
			String msg = ProductValidator.validate(input);
			
			boolean nameIsProfane = profanityService.execute(input.getName()).getBody();
			boolean descriptionIsProfane = profanityService.execute(input.getName()).getBody();
			
			if(nameIsProfane||descriptionIsProfane)throw new ProductNotValideException("Profanity.");
			
			productRepository.save(input);
			
			ProductDTO dto = new ProductDTO(input);
			
			return ResponseEntity.ok(dto);
		}
		
		throw new ProductNotFoundException();
	}

}
