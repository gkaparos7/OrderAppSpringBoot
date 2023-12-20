package gr.aueb.cf4.orderapp.repository;

import gr.aueb.cf4.orderapp.model.Product;
import gr.aueb.cf4.orderapp.model.Subcategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findBySubcategory(Subcategory subcategory);
}
