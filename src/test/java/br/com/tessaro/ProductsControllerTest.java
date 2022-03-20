package br.com.tessaro;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.tessaro.model.Product;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductsControllerTest {

	@Autowired
	private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;
	
	@Test
	public void getAllProductsTest() throws Exception {
		mockMvc.perform(get("/api/v1/products")).andExpect(status().isOk());
	}
	
	@Test
	public void postProductsTest() throws Exception {
		Product product = new Product("Teste1", "Teste1", BigDecimal.valueOf(25));
		String json = objectMapper.writeValueAsString(product);
		
		mockMvc.perform(post("/api/v1/products")
	               .contentType(MediaType.APPLICATION_JSON_VALUE)
	               .content(json)).andExpect(status().isCreated());
	}
	
}
