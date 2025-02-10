package jborg.exam.examNoBS24.product.model;

import java.util.UUID;

import org.springframework.context.annotation.Bean;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jborg.exam.examNoBS24.product.exceptions.ProductNotValideException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="product")
@Data
public class Product
{

	@Id
	@Column(name="Id")
	private String Id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="description")
	private String description;
	
	@Column(name="price")
	private Double price;
	
	@Column(name="manufacturer")
	private String manufacturer;
	
	@Column(name="category")
	private String category;
	
	@Column(name="created_timestamp")
	private Long created_timestamp;
	
	@Column(name="up_dated_timestamp")
	private Long up_dated_timestamp;
	
	@Column(name="region")
	@Enumerated(EnumType.STRING)
	private Region region;

	public Product(String Id, String name, String description, Double price, 
			String manufacturer, String category, Long created_timestamp, 
			Long up_dated_timestamp, Region region) 
	{

		this.Id = Id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.manufacturer = manufacturer;
		this.category = category;
		this.created_timestamp = created_timestamp;
		this.up_dated_timestamp = up_dated_timestamp;
		this.region = region;
	}
	
	public Product()
	{}
}