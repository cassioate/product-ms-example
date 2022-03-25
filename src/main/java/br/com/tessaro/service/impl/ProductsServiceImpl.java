package br.com.tessaro.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.tessaro.exceptions.business.NotFindProductByIdException;
import br.com.tessaro.exceptions.business.NotPossibleMakeTheUpdateException;
import br.com.tessaro.domain.Product;
import br.com.tessaro.domain.dto.ProductDTO;
import br.com.tessaro.repository.ProductsRepository;
import br.com.tessaro.repository.custom.ProductsCustomRepository;
import br.com.tessaro.service.ProductsService;

@Service
public class ProductsServiceImpl implements ProductsService{
	
	private Logger logger = LoggerFactory.getLogger(ProductsServiceImpl.class);
	
	@Autowired
	private ProductsRepository productRepository;
	
	@Autowired
	private ProductsCustomRepository productsCustomRepository;

	@Override
	public List<ProductDTO> getAllProducts() {
		logger.info("SERVICE - Using the getAllProducts method");

		List<ProductDTO> productsDTOList = productRepository.findAll().stream().map( obj -> {
				ProductDTO productDto = new ProductDTO();
				BeanUtils.copyProperties(obj, productDto);
				return productDto;
		}).collect(Collectors.toList());

		return productsDTOList;
	}
	
	@Override
	public Page<ProductDTO> getProductsWithPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		logger.info("SERVICE - Using the getProductsWithPage method");

		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		Page<ProductDTO> productList = productRepository.findAll(pageRequest).map(obj ->
				new ProductDTO(obj.getProductId(),
								obj.getName(),
								obj.getDescription(),
								obj.getPrice())
		);

		return productList;
	}
	
	@Override
	public ProductDTO getProductById(String id) {
		logger.info("SERVICE - Using the getProductsById method");

		Product product = productRepository.findById(id).orElseThrow(() -> new NotFindProductByIdException());
		ProductDTO productDto = new ProductDTO();
		BeanUtils.copyProperties(product, productDto);
		return productDto;
	}

	@Override
	public List<ProductDTO> getFilterProducts(String q, BigDecimal minPrice, BigDecimal maxPrice) {
		logger.info("SERVICE - Using the getFilterProducts method");

		List<ProductDTO> productsDto = productsCustomRepository.findProductsByFilter(q, minPrice, maxPrice)
				.stream().
						map(obj -> {
							ProductDTO productDto = new ProductDTO();
							BeanUtils.copyProperties(obj, productDto);
							return productDto;
						}).collect(Collectors.toList());
		return productsDto;
	}

	@Override
	public ProductDTO postProduct(ProductDTO productDtoRequest) {
		logger.info("SERVICE - Using the postProduct method");

		Product product = new Product();
		BeanUtils.copyProperties(productDtoRequest, product);
		Product productSave = productRepository.save(product);
		ProductDTO productDtoResponse = new ProductDTO();
		BeanUtils.copyProperties(productSave, productDtoResponse);
		
		return productDtoResponse;
	}
	
	@Override
	public ProductDTO putProduct(String id, ProductDTO productDto) {
		logger.info("SERVICE - Using the putProduct method");

		Product productFind = productRepository.findById(id).orElseThrow(() -> new NotPossibleMakeTheUpdateException());

		Product product = new Product();
		BeanUtils.copyProperties(productDto, product);
		product.setProductId(id);

		Product productSave = productRepository.save(product);
		ProductDTO productDtoResponse = new ProductDTO();
		BeanUtils.copyProperties(productSave, productDtoResponse);

		return productDtoResponse;
	}

	@Override
	public void deleteProductById(String id) {
		logger.info("SERVICE - Using the deleteProductById method");

		Product productFind = productRepository.findById(id).orElseThrow(() -> new NotFindProductByIdException());
		productRepository.deleteById(productFind.getProductId());
	}

}
