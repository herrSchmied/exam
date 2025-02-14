package jborg.exam.examNoBS24;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import jborg.exam.examNoBS24.product.ProductRepository;
import jborg.exam.examNoBS24.product.model.Product;
import jborg.exam.examNoBS24.product.model.ProductDTO;
import jborg.exam.examNoBS24.product.model.Region;
import jborg.exam.examNoBS24.product.services.commands.CreateProductService;
import jborg.exam.examNoBS24.profanityFilter.service.ProfanityService;


public class CreateProductServiceTest
{

	@Mock
	ProductRepository productRepository;
	
	@InjectMocks
	CreateProductService createProductService;
	
	@Mock
	ProfanityService profanityService;
	
	@BeforeEach
	public void setup()
	{
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	public void valideProduct_doCreatProductService_returnDTO()
	{
		//Given
		//Given
		String Id = UUID.randomUUID().toString();
		Product product = new Product();
		product.setName("MC Hammer");
		product.setId(Id);
		product.setCreated_timestamp(System.currentTimeMillis());
		product.setUp_dated_timestamp(null);
		product.setManufacturer("United States of America");
		product.setCategory("Electronics");
		product.setPrice(1000000.0);
		product.setRegion(Region.Canada);
		product.setDescription("Hi there why there Bye there go Ham go far go hard");

		ProductDTO dto = new ProductDTO(product);
		
		//Is still part of the 'given' setup. Despite calling 'when'.
		when(productRepository.findById(Id)).thenReturn(Optional.of(product));

		//when
		ResponseEntity<ProductDTO> response = createProductService.execute(dto);
		
		//Then
		assertEquals(ResponseEntity.ok(new ProductDTO(product)), response);
		verify(productRepository, times(1)).findById(Id);

	}
}
