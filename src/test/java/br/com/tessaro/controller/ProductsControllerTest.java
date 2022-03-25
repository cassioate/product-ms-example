package br.com.tessaro.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.tessaro.domain.Product;
import br.com.tessaro.repository.ProductsRepository;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ProductsControllerTest {

	@Autowired
	private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;
    
    @Autowired
    private ProductsRepository productRepository;
    
    Product productRes1 = new Product();
    Product productRes2 = new Product();
    Product productRes3 = new Product();
    
	@BeforeEach
	public void setUp() {
		 Product product1 = new Product("teste1", "teste1", BigDecimal.valueOf(100));
		 Product product2 = new Product("teste2", "teste2", BigDecimal.valueOf(200));
		 Product product3 = new Product("teste3", "teste3", BigDecimal.valueOf(300));

		 productRes1 = productRepository.save(product1);
		 productRes2 = productRepository.save(product2);
		 productRes3 = productRepository.save(product3);
	}
	
	@AfterEach
	public void cleanUpEach(){
		productRepository.deleteAll();
	}
	
	@Test
	@DisplayName("TEST - ProductsController - getAllProductsTest()")
	public void getAllProductsTest() throws Exception {
		mockMvc.perform(get("/api/v1/products")).andExpect(status().isOk());
	}
	
	@Test
	@DisplayName("TEST - ProductsController - getProductsByIdTest()")
	public void getProductsByIdTest() throws Exception {
		mockMvc.perform(get("/api/v1/products/"+productRes1.getProductId())).andExpect(status().isOk());
	}
	
	@Test
	@DisplayName("TEST - ProductsController - getProductsByIdTestNotFindProductByIdException()")
	public void getProductsByIdTestNotFindProductByIdException() throws Exception {
		mockMvc.perform(get("/api/v1/products/111")).andExpect(status().isNotFound());
	}
	
	@Test
	@DisplayName("TEST - ProductsController - getFilterProducts()")
	public void getFilterProductsTestWithoutAnyParam() throws Exception {
		mockMvc.perform(get("/api/v1/products/search?")).andExpect(status().isOk());
	}
	
	@Test
	@DisplayName("TEST - ProductsController - getFilterProducts()")
	public void getFilterProductsTest() throws Exception {
		mockMvc.perform(get("/api/v1/products/search?q=teste1")).andExpect(status().isOk());
	}
	
	@Test
	@DisplayName("TEST - ProductsController - getFilterProductsTestWith2Param()")
	public void getFilterProductsTestWith2Param() throws Exception {
		mockMvc.perform(get("/api/v1/products/search?q=teste1&min_price=100")).andExpect(status().isOk());
	}
	
	@Test
	@DisplayName("TEST - ProductsController - getFilterProductsTestWith3Param()")
	public void getFilterProductsTestWith3Param() throws Exception {
		mockMvc.perform(get("/api/v1/products/search?q=teste1&min_price=100&max_price=200")).andExpect(status().isOk());
	}
	
	@Test
	@DisplayName("TEST - ProductsController - postProductsTest()")
	public void postProductsTest() throws Exception {
		Product product = new Product("Teste1", "Teste1", BigDecimal.valueOf(25));
		String json = objectMapper.writeValueAsString(product);
		
		mockMvc.perform(post("/api/v1/products")
	               .contentType(MediaType.APPLICATION_JSON_VALUE)
	               .content(json)).andExpect(status().isCreated());
	}
	
	@Test
	@DisplayName("TEST - ProductsController - postProductsTestBadRequestNameEmpty()")
	public void postProductsTestBadRequestNameEmpty() throws Exception {
		Product product = new Product("Teste1", "", BigDecimal.valueOf(25));
		String json = objectMapper.writeValueAsString(product);
		
		mockMvc.perform(post("/api/v1/products")
	               .contentType(MediaType.APPLICATION_JSON_VALUE)
	               .content(json)).andExpect(status().isBadRequest());
	}
	
	@Test
	@DisplayName("TEST - ProductsController - postProductsTestBadRequestDescriptionEmpty()")
	public void postProductsTestBadRequestDescriptionEmpty() throws Exception {
		Product product = new Product("", "TESTE", BigDecimal.valueOf(25));
		String json = objectMapper.writeValueAsString(product);
		
		mockMvc.perform(post("/api/v1/products")
	               .contentType(MediaType.APPLICATION_JSON_VALUE)
	               .content(json)).andExpect(status().isBadRequest());
	}

	@Test
	@DisplayName("TEST - ProductsController - postProductsTestBadRequestNegativeNumber()")
	public void postProductsTestBadRequestNegativeNumber() throws Exception {
		Product product = new Product("Teste1", "Teste1", BigDecimal.valueOf(-25));
		String json = objectMapper.writeValueAsString(product);
		
		mockMvc.perform(post("/api/v1/products")
	               .contentType(MediaType.APPLICATION_JSON_VALUE)
	               .content(json)).andExpect(status().isBadRequest());
	}
	
	@Test
	@DisplayName("TEST - ProductsController - putProductByIdTest ()")
	public void putProductByIdTest() throws Exception {
		
		Product product = new Product("TESTE-INTEGRA", "TESTE-INTEGRA", BigDecimal.valueOf(500));
		String json = objectMapper.writeValueAsString(product);

		mockMvc.perform(put("/api/v1/products/"+productRes1.getProductId())
					.contentType(MediaType.APPLICATION_JSON_VALUE)
			        .content(json))
					.andExpect(status().isCreated());
	}
	
	@Test
	@DisplayName("TEST - ProductsController - putProductByIdTestBadRequestNegativeNumber ()")
	public void putProductByIdTestBadRequestNegativeNumber() throws Exception {
		
		Product product = new Product("TESTE-INTEGRA", "TESTE-INTEGRA", BigDecimal.valueOf(-500));
		String json = objectMapper.writeValueAsString(product);

		mockMvc.perform(put("/api/v1/products/"+productRes1.getProductId())
					.contentType(MediaType.APPLICATION_JSON_VALUE)
			        .content(json))
					.andExpect(status().isBadRequest());
	}
	
	@Test
	@DisplayName("TEST - ProductsController - putProductByIdTestBadRequestNameEmpty ()")
	public void putProductByIdTestBadRequestNameEmpty() throws Exception {
		
		Product product = new Product("", "TESTE-INTEGRA", BigDecimal.valueOf(500));
		String json = objectMapper.writeValueAsString(product);

		mockMvc.perform(put("/api/v1/products/"+productRes1.getProductId())
					.contentType(MediaType.APPLICATION_JSON_VALUE)
			        .content(json))
					.andExpect(status().isBadRequest());
	}
	
	@Test
	@DisplayName("TEST - ProductsController - putProductByIdTestBadRequestDescriptionEmpty ()")
	public void putProductByIdTestBadRequestDescriptionEmpty() throws Exception {
		
		Product product = new Product("TESTE-INTEGRA", "", BigDecimal.valueOf(500));
		String json = objectMapper.writeValueAsString(product);

		mockMvc.perform(put("/api/v1/products/"+productRes1.getProductId())
					.contentType(MediaType.APPLICATION_JSON_VALUE)
			        .content(json))
					.andExpect(status().isBadRequest());
	}
	
	@Test
	@DisplayName("TEST - ProductsController - putProductByIdTestNotFound ()")
	public void putProductByIdTestNotFound() throws Exception {
		
		Product product = new Product("TESTE-INTEGRA", "TESTE-INTEGRA", BigDecimal.valueOf(500));
		String json = objectMapper.writeValueAsString(product);

		mockMvc.perform(put("/api/v1/products/1111")
					.contentType(MediaType.APPLICATION_JSON_VALUE)
			        .content(json))
					.andExpect(status().isNotFound());
	}
	
	@Test
	@DisplayName("TEST - ProductsController - deleteProductById ()")
	public void deleteProductByIdTest() throws Exception {
		mockMvc.perform(delete("/api/v1/products/"+productRes1.getProductId())).andExpect(status().isOk());
	}
	
	@Test
	@DisplayName("TEST - ProductsController - deleteProductByIdTestNotFound ()")
	public void deleteProductByIdTestNotFound() throws Exception {
		mockMvc.perform(delete("/api/v1/products/1000")).andExpect(status().isNotFound());
	}
}
