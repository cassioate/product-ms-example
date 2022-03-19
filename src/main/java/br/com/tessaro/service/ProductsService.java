package br.com.tessaro.service;

import java.math.BigDecimal;
import java.util.List;

import br.com.tessaro.model.dto.ProductDTO;

public interface ProductsService {
	
	public ProductDTO postProduct(ProductDTO productDto);

	public ProductDTO putProduct(String id, ProductDTO productDto);
	
	public List<ProductDTO> getAllProducts();
	
	public List<ProductDTO> getFilterProducts(BigDecimal minPrice, BigDecimal maxPrice, String q);
	
	public ProductDTO getProductById(String id);
	
	public void deleteProductById(String id);
	
}
