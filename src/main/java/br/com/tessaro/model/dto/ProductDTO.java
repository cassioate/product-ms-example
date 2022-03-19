package br.com.tessaro.model.dto;

import java.math.BigDecimal;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.tessaro.anotations.NumberPositive;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NumberPositive
@NoArgsConstructor
public class ProductDTO {
	
	private String productId;
	
	@NotEmpty
	private String name;
	
	@NotEmpty 
	private String description;
	
	@NotNull
	private BigDecimal price;

	public ProductDTO(String productId, String name, String description, BigDecimal price) {
		this.productId = productId;
		this.name = name;
		this.description = description;
		this.price = price;
	}
	
	public ProductDTO(String name, String description, BigDecimal price) {
		this.name = name;
		this.description = description;
		this.price = price;
	}
}

