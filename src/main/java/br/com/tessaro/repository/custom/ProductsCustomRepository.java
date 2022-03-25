package br.com.tessaro.repository.custom;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.tessaro.domain.Product;

@Repository
public class ProductsCustomRepository {

	@Autowired
	private EntityManager em;

	@SuppressWarnings("unchecked")
	public List<Product> findProductsByFilter(String nameOrDescription, BigDecimal minPrice, BigDecimal maxPrice) {
		StringBuilder queryBuilder = new StringBuilder("SELECT P.* FROM TB_Product p ");
		String param = "WHERE ";

		if (nameOrDescription != null) {
			queryBuilder.append(param).append("(p.name = :nameOrDescription OR p.description = :nameOrDescription) ");
			param = "AND ";
		};

		if (minPrice != null) {
			queryBuilder.append(param).append("p.price >= :minPrice ");
			param = "AND ";
		};

		if (maxPrice != null) {
			queryBuilder.append(param).append("p.price <= :maxPrice ");
			param = "AND ";
		};

		Query entityManagerQuery = em.createNativeQuery(queryBuilder.toString(), Product.class);

		if (nameOrDescription != null) {
			entityManagerQuery.setParameter("nameOrDescription", nameOrDescription);
		};

		if (minPrice != null) {
			entityManagerQuery.setParameter("minPrice", minPrice);
		};

		if (maxPrice != null) {
			entityManagerQuery.setParameter("maxPrice", maxPrice);
		};

		return entityManagerQuery.getResultList();
	}
}