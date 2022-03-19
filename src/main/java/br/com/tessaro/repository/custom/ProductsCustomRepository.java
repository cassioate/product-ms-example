package br.com.tessaro.repository.custom;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.tessaro.model.Product;

@Repository
public class ProductsCustomRepository {
	
	@Autowired
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	public List<Product> findProductsByFilter(String q, BigDecimal minPrice, BigDecimal maxPrice) {
		String query = "SELECT P.* FROM TB_Product p ";		
		String param = "WHERE ";
		if (q != null) {
			query += param + "(p.name = :q OR p.description = :q) ";
			param = "AND ";
		};
		
		if (minPrice != null) {
			query += param + "p.price >= :minPrice ";
			param = "AND ";
		};
		
		if (maxPrice != null) {
			query += param + "p.price <= :maxPrice ";
			param = "AND ";
		}
		
		Query entityManagerQuery = em.createNativeQuery(query, Product.class);
		
		if (q != null) {
			entityManagerQuery.setParameter("q", q);
		};
		
		if (minPrice != null) {
			entityManagerQuery.setParameter("minPrice", minPrice);
		};
		
		if (maxPrice != null) {
			entityManagerQuery.setParameter("maxPrice", maxPrice);
		}
		
		return entityManagerQuery.getResultList();
	}

}
