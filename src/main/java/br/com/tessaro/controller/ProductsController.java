package br.com.tessaro.controller;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.tessaro.domain.dto.ProductDTO;
import br.com.tessaro.service.impl.ProductsServiceImpl;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("api/v1/products")
public class ProductsController {
	
	private Logger logger = LoggerFactory.getLogger(ProductsServiceImpl.class);
	
	@Autowired
	private ProductsServiceImpl productsService;
	
	@GetMapping
	@ApiOperation(value = "List of all products")
	@Cacheable(value="retrieveExcahngeValueCache")
	public ResponseEntity<List<ProductDTO>> getAllProducts () {
		logger.info("CONTROLLER - Using the getAllProducts method");
		List<ProductDTO> productResponse = productsService.getAllProducts();
		// I make this in the other way, only for study propose.
		return new ResponseEntity<>(productResponse, HttpStatus.OK);
	}
	
	@GetMapping("/page")
	@ApiOperation(value = "List of all products with page")
	@Cacheable(value="retrieveExcahngeValueCache")
	public ResponseEntity<Page<ProductDTO>> findPage(
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="3") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="name") String orderBy, 
			@RequestParam(value="direction", defaultValue="ASC") String direction) {
		logger.info("CONTROLLER - Using the findPage method");
		Page<ProductDTO> ProductListDto = productsService.getProductsWithPage(page, linesPerPage, orderBy, direction);
		return ResponseEntity.status(HttpStatus.OK).body(ProductListDto);
	}

	@GetMapping("/{id}")
	@ApiOperation(value = "Details of a product")
	@Cacheable(value="retrieveExcahngeValueCache")
	public ResponseEntity<ProductDTO> getProductById (@PathVariable String id) {
		logger.info("CONTROLLER - Using the getProductById method");
		ProductDTO productResponse = productsService.getProductById(id);
		return ResponseEntity.status(HttpStatus.OK).body(productResponse);
	}
	
	@GetMapping("/search")
	@ApiOperation(value = "List of all products in the filter range")
	@Cacheable(value="retrieveExcahngeValueCache")
	public ResponseEntity<List<ProductDTO>> getFilterProducts (
			@RequestParam(required = false, value = "min_price") BigDecimal minPrice,
			@RequestParam(required = false, value = "max_price") BigDecimal maxPrice,
			@RequestParam(required = false, value = "q") String q) {
		logger.info("CONTROLLER - Using the getFilterProducts method");
		List<ProductDTO> productResponse = productsService.getFilterProducts(q, minPrice, maxPrice);
		return ResponseEntity.status(HttpStatus.OK).body(productResponse);
	}
	
	@PostMapping
	@ApiOperation(value = "Adding a product")
	@CacheEvict(value = "retrieveExcahngeValueCache", allEntries = true)
	public ResponseEntity<ProductDTO> postProducts (@Valid @RequestBody ProductDTO productDtoRequest) {
		logger.info("CONTROLLER - Using the postProduct method");
		ProductDTO productResponse = productsService.postProduct(productDtoRequest);
		return ResponseEntity.status(HttpStatus.CREATED).body(productResponse);
	}
	
	@PutMapping("/{id}")
	@ApiOperation(value = "Updating a product")
	@CacheEvict(value = "retrieveExcahngeValueCache", allEntries = true)
	public ResponseEntity<ProductDTO> putProducts (@PathVariable String id , @Valid @RequestBody ProductDTO productDto) {
		logger.info("CONTROLLER - Using the putProducts method");
		ProductDTO productResponse = productsService.putProduct(id, productDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(productResponse);
	}
	
	@DeleteMapping("/{id}")
	@ApiOperation(value = "Removing a product")
	@CacheEvict(value = "retrieveExcahngeValueCache", allEntries = true)
	public ResponseEntity<?> deleteProductById (@PathVariable String id) {
		logger.info("CONTROLLER - Using the deleteProductById method");
		productsService.deleteProductById(id);
		return ResponseEntity.status(HttpStatus.OK).build();
	}
}