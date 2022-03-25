package br.com.tessaro.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import br.com.tessaro.exceptions.business.NotFindProductByIdException;
import br.com.tessaro.exceptions.business.NotPossibleMakeTheUpdateException;
import br.com.tessaro.domain.Product;
import br.com.tessaro.domain.dto.ProductDTO;
import br.com.tessaro.repository.ProductsRepository;
import br.com.tessaro.repository.custom.ProductsCustomRepository;

public class ProductsServiceImplTest {
	
	@InjectMocks
	private ProductsServiceImpl productsService;

	@Mock
	private ProductsCustomRepository productsCustomRepository;
	
	@Mock
	private ProductsRepository productRepository;
	
	@BeforeEach
	public void setUp() {
		 MockitoAnnotations.openMocks(this);
		 Product product1 = new Product("teste1", "teste1", BigDecimal.valueOf(100));
		 Product product2 = new Product("teste2", "teste2", BigDecimal.valueOf(200));
		 Product product3 = new Product("teste3", "teste3", BigDecimal.valueOf(300));
		 product1.setProductId("1");
		 product2.setProductId("1");
		 product3.setProductId("1");
		 productRepository.save(product1);
		 productRepository.save(product2);
		 productRepository.save(product3);
	}
	
	@Test
	@DisplayName("TEST - ProductsServiceImpl - getAllProductsTesst()")
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
	
	@Test
	@DisplayName("TEST - ProductsServiceImpl - getProductByIdTest()")
	public void getProductByIdTest(){
		
		Product product = new Product("Teste1", "Teste1", BigDecimal.valueOf(25));
		product.setProductId("ID-123");
		Mockito.when(productRepository.findById("ID-123")).thenReturn(Optional.of(product));
		
		ProductDTO productsResponse = productsService.getProductById("ID-123");
		
		assertEquals(productsResponse.getProductId(), "ID-123");
	}
	
	@Test
	@DisplayName("TEST - ProductsServiceImpl - getProductByIdTestThrowsNotFindProductByIdException()")
	public void getProductByIdTestThrowsNotFindProductByIdException() {
		
		Mockito.when(productRepository.findById("ID-123")).thenReturn(Optional.empty());
		
		assertThrows(NotFindProductByIdException.class, () -> {
			productsService.getProductById("ID-123");
		});
		
	}

	@Test
	@DisplayName("TEST - ProductsServiceImpl - getFilterProductsTest()")
	public void getFilterProductsTest(){

		List<Product> products = new ArrayList<>();
		products.add(new Product("Teste", "Teste1", BigDecimal.valueOf(101)));
		products.add(new Product("Teste2", "Teste", BigDecimal.valueOf(199)));
				
		Mockito.when(productsCustomRepository.findProductsByFilter("teste", BigDecimal.valueOf(100), BigDecimal.valueOf(200))).thenReturn(products);
		
		List<ProductDTO> productsResponse = productsService.getFilterProducts("teste", BigDecimal.valueOf(100), BigDecimal.valueOf(200));
		
		assertEquals(productsResponse.size(), 2);
		assertEquals(productsResponse.get(0).getName(), "Teste");
		assertEquals(productsResponse.get(1).getDescription(), "Teste");
	}
	
	@Test
	@DisplayName("TEST - ProductsServiceImpl - postProductTest()")
	public void postProductTest(){

		Product product = new Product("Teste", "Teste1", BigDecimal.valueOf(101));
		ProductDTO productDtoRequest = new ProductDTO("Teste", "Teste1", BigDecimal.valueOf(101));
		Product productWhitIdResponse = new Product("Teste", "Teste1", BigDecimal.valueOf(101));
		productWhitIdResponse.setProductId("ID-1");
		
		Mockito.when(productRepository.save(product)).thenReturn(productWhitIdResponse);
		
		ProductDTO productDtoResponse = productsService.postProduct(productDtoRequest);

		assertEquals(productDtoResponse.getProductId(), "ID-1");
		assertEquals(productDtoResponse.getName(), "Teste");
		assertEquals(productDtoResponse.getDescription(), "Teste1");
	}
	
	@Test
	@DisplayName("TEST - ProductsServiceImpl - putProductTest()")
	public void putProductTest(){
		
		Product productFindById = new Product("Teste1", "Teste1", BigDecimal.valueOf(25));
		productFindById.setProductId("ID-123");
		Mockito.when(productRepository.findById("ID-123")).thenReturn(Optional.of(productFindById));
	
		Product productWithID = new Product("TestePut", "Teste1", BigDecimal.valueOf(101));;
		productWithID.setProductId("ID-123");
		ProductDTO productDto = new ProductDTO("TestePut", "Teste1", BigDecimal.valueOf(101));
	
		Mockito.when(productRepository.save(productWithID)).thenReturn(productWithID);
		
		ProductDTO productsResponse = productsService.putProduct("ID-123", productDto);
		
		assertEquals(productsResponse.getProductId(), "ID-123");
	}
	
	@Test
	@DisplayName("TEST - ProductsServiceImpl - putProductTestThrowsNotFindProductByIdException()")
	public void putProductTestThrowsNotFindProductByIdException() {
		
		ProductDTO productDto = new ProductDTO("TestePut", "Teste1", BigDecimal.valueOf(101));
		Mockito.when(productRepository.findById("ID-123")).thenReturn(Optional.empty());
		
		assertThrows(NotPossibleMakeTheUpdateException.class, () -> {
			productsService.putProduct("ID-123", productDto);
		});
		
	}
	
	@Test
	@DisplayName("TEST - ProductsServiceImpl - deleteProductById()")
	public void deleteProductById(){
		
		Product productFindById = new Product("Teste1", "Teste1", BigDecimal.valueOf(25));
		productFindById.setProductId("ID-123");
		Mockito.when(productRepository.findById("ID-123")).thenReturn(Optional.of(productFindById));
		
		productsService.deleteProductById("ID-123");
		
		Mockito.verify(productRepository).deleteById("ID-123");
		Mockito.verify(productRepository, times(1)).deleteById("ID-123");
	}
	
	@Test
	@DisplayName("TEST - ProductsServiceImpl - deleteProductByIdTestThrowsNotFindProductByIdException()")
	public void deleteProductByIdTestThrowsNotFindProductByIdException() {
		
		Mockito.when(productRepository.findById("ID-123")).thenReturn(Optional.empty());
		
		assertThrows(NotFindProductByIdException.class, () -> {
			productsService.deleteProductById("ID-123");
		});
		
	}
	
}
