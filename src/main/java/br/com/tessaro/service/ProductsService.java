package br.com.tessaro.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.domain.Page;

import br.com.tessaro.domain.dto.ProductDTO;

public interface ProductsService {
	
	public ProductDTO postProduct(ProductDTO productDto);

	public ProductDTO putProduct(String id, ProductDTO productDto);
	
	public List<ProductDTO> getAllProducts();
	
	public List<ProductDTO> getFilterProducts(String q, BigDecimal minPrice, BigDecimal maxPrice);
	
	public Page<ProductDTO> getProductsWithPage(Integer page, Integer linesPerPage, String orderBy, String direction);

	public ProductDTO getProductById(String id);
	
	public void deleteProductById(String id);
	

}
