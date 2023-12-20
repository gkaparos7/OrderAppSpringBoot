package gr.aueb.cf4.orderapp.service;

import gr.aueb.cf4.orderapp.dto.SubcategoryCreateDTO;
import gr.aueb.cf4.orderapp.dto.SubcategoryReadOnlyDTO;
import gr.aueb.cf4.orderapp.dto.SubcategoryUpdateDTO;
import gr.aueb.cf4.orderapp.model.Subcategory;

import java.util.List;
import java.util.Optional;

public interface ISubcategoryService {
    SubcategoryReadOnlyDTO createSubcategory(SubcategoryCreateDTO subcategoryCreateDTO);

    SubcategoryReadOnlyDTO updateSubcategory(Long subcategoryId, SubcategoryUpdateDTO subcategoryUpdateDTO);

    List<SubcategoryReadOnlyDTO> getAllSubcategories();

    SubcategoryReadOnlyDTO getSubcategoryById(Long subcategoryId);

    SubcategoryReadOnlyDTO deleteSubcategory(Long subcategoryId);
    List<SubcategoryReadOnlyDTO> getSubcategoriesInCategory(Long categoryId);
    Optional<Subcategory> getSubcategoryEntityById(Long subcategoryId);

}

