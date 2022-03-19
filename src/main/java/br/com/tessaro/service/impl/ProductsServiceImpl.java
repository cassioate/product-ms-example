package br.com.tessaro.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tessaro.exceptions.business.NotFindProductByIdException;
import br.com.tessaro.exceptions.business.NotPossibleMakeTheUpdateException;
import br.com.tessaro.model.Product;
import br.com.tessaro.model.dto.ProductDTO;
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
		
		List<ProductDTO> productsDTOList = new ArrayList<>();
		List<Product> products = productRepository.findAll();
		
		products.forEach( p -> {
			ProductDTO productDto = new ProductDTO();
			BeanUtils.copyProperties(p, productDto);
			productsDTOList.add(productDto);
		});
		
		return productsDTOList;
	}
	
	@Override
	public ProductDTO getProductById(String id) {
		logger.info("SERVICE - Using the getProductsById method");
		Optional<Product> productFind = productRepository.findById(id);
		if (productFind.isPresent()) {
			Product product = productFind.get();
			ProductDTO productDto = new ProductDTO();
			BeanUtils.copyProperties(product, productDto);
			return productDto;
		}
		throw new NotFindProductByIdException();
	}

	@Override
	public List<ProductDTO> getFilterProducts(BigDecimal minPrice, BigDecimal maxPrice, String q) {
		logger.info("SERVICE - Using the getFilterProducts method");
		List<ProductDTO> productsDtoList = new ArrayList<>();
		List<Product> products = productsCustomRepository.findProductsByFilter(q, minPrice, maxPrice);
		products.forEach(p -> {
			ProductDTO productDto = new ProductDTO();
			BeanUtils.copyProperties(p, productDto);
			productsDtoList.add(productDto);
		});
		return productsDtoList;
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
		
		Optional<Product> productFind = productRepository.findById(id);
		if (productFind.isPresent()) {
			Product product = new Product();
			BeanUtils.copyProperties(productDto, product);
			product.setProductId(id);
			Product productSave = productRepository.save(product);
			ProductDTO productDtoResponse = new ProductDTO();
			BeanUtils.copyProperties(productSave, productDtoResponse);
			
			return productDtoResponse;
		}
		
		throw new NotPossibleMakeTheUpdateException();
	}

	@Override
	public void deleteProductById(String id) {
		logger.info("SERVICE - Using the deleteProductById method");

		Optional<Product> productFind = productRepository.findById(id);
		
		if (productFind.isPresent()) {
			productRepository.deleteById(productFind.get().getProductId());
		} else {
			throw new NotFindProductByIdException();
		}
	}
}
