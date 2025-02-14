package jborg.exam.examNoBS24;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
import jborg.exam.examNoBS24.product.exceptions.ProductNotFoundException;
import jborg.exam.examNoBS24.product.model.Product;
import jborg.exam.examNoBS24.product.model.ProductDTO;
import jborg.exam.examNoBS24.product.model.Region;
import jborg.exam.examNoBS24.product.services.querys.GetProductService;

public class GetProductServiceTest
{

	@Mock
	ProductRepository productRepository;
	
	@InjectMocks
	GetProductService getProductService;
	
	@BeforeEach
	public void setup()
	{
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	public void productExists_doGetProductService_returnsDTO()
	{
		
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
		
		//Is still part of the 'given' setup. Despite calling 'when'.
		when(productRepository.findById(Id)).thenReturn(Optional.of(product));
		
		//when
		ResponseEntity<ProductDTO> response = getProductService.execute(Id);
		
		//Then
		assertEquals(ResponseEntity.ok(new ProductDTO(product)), response);
		verify(productRepository, times(1)).findById(Id);
	}
	
	@Test
	public void productDontExist_doGetProductServic_throwProductNotFoundException()
	{
		//given
		String Id = UUID.randomUUID().toString();
		when(productRepository.findById(Id)).thenReturn(Optional.empty());
		
		//when&&Then
		assertThrows(ProductNotFoundException.class, ()->
		{
			getProductService.execute(Id);
		});
		verify(productRepository, times(1)).findById(Id);
	}
}
