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
import lombok.Data;

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
	
	public Product(Double price, Region region, String...args)
	{
		if(args.length<4)throw new ProductNotValideException("Product Constructor parameter not valide.");
		this.Id = UUID.randomUUID().toString();
		this.price = price;
		this.created_timestamp = System.currentTimeMillis();
		this.up_dated_timestamp = null;
		this.region = region;
		this.name = args[0];
		this.description = args[1];
		this.manufacturer = args[2];
		this.category = args[3];
	}
	
	public Product()
	{}
}
