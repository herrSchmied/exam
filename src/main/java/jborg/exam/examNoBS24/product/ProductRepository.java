package jborg.exam.examNoBS24.product;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jborg.exam.examNoBS24.product.model.Product;


@Repository
public interface ProductRepository extends JpaRepository<Product, String>
{
	//JPQL
	@Query("SELECT p FROM Product p WHERE p.category LIKE %:keyword% OR p.description LIKE %:keyword%")
	List<Product> findByCategoryOrDescriptionContaining(@Param("keyword") String name);
}

