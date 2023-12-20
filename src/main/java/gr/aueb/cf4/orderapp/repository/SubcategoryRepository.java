package gr.aueb.cf4.orderapp.repository;

import gr.aueb.cf4.orderapp.model.Subcategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubcategoryRepository extends JpaRepository<Subcategory, Long> {

}
