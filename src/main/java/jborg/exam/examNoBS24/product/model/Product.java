package jborg.exam.examNoBS24.product.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
	private double price;
	
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
}
