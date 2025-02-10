package jborg.exam.examNoBS24.product.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductUpdateObject
{
	private String Id;
	private ProductDTO dto;
}
