package br.com.tessaro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.tessaro.model.Product;

@Repository
public interface ProductsRepository extends JpaRepository<Product, String>{
	
}