package br.com.tessaro;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import br.com.tessaro.model.Product;
import br.com.tessaro.model.dto.ProductDTO;
import br.com.tessaro.repository.ProductsRepository;
import br.com.tessaro.service.impl.ProductsServiceImpl;

public class ProductsServiceImplTest {
	
	@InjectMocks
	private ProductsServiceImpl productsService;
	
	@Mock
	private ProductsRepository productRepository;
	
	@BeforeEach
	public void setUp() {
		 MockitoAnnotations.openMocks(this);
	}
	
	@Test
	@DisplayName("TESTE - getAllProductsTesst() - ProductsServiceImpl")
	public void getAllProductsTest(){
		
		List<Product> products = new ArrayList<>();
		products.add(new Product("Teste1", "Teste1", BigDecimal.valueOf(25)));
		products.add(new Product("Teste2", "Teste2", BigDecimal.valueOf(26)));
		products.add(new Product("Teste3", "Teste3", BigDecimal.valueOf(27)));
		
		Mockito.when(productRepository.findAll()).thenReturn(products);
		
		List<ProductDTO> productsResponse = productsService.getAllProducts();

		List<ProductDTO> productsDto = new ArrayList<>();
		productsDto.add(new ProductDTO("Teste1", "Teste1", BigDecimal.valueOf(25)));
		productsDto.add(new ProductDTO("Teste2", "Teste2", BigDecimal.valueOf(26)));
		productsDto.add(new ProductDTO("Teste3", "Teste3", BigDecimal.valueOf(27)));
		
		assertEquals(productsResponse, productsDto);
	}

}
