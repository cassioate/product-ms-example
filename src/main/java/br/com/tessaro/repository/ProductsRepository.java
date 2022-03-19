package br.com.tessaro.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.tessaro.model.Product;

public interface ProductsRepository extends JpaRepository<Product, String>{

}
