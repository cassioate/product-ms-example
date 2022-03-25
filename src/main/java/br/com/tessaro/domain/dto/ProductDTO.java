package br.com.tessaro.domain.dto;

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
	
	@NotEmpty(message="You forgot to fill a field name.")
	private String name;
	
	@NotEmpty(message="You forgot to fill a field description.")
	private String description;
	
	@NotNull(message="You forgot to fill a field price.")
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

