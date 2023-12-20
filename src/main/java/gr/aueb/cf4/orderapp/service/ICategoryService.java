
package gr.aueb.cf4.orderapp.service;

import gr.aueb.cf4.orderapp.dto.CategoryCreateDTO;
import gr.aueb.cf4.orderapp.dto.CategoryReadOnlyDTO;
import gr.aueb.cf4.orderapp.dto.CategoryUpdateDTO;


import java.util.List;

public interface ICategoryService {
    CategoryReadOnlyDTO createCategory(CategoryCreateDTO categoryCreateDTO);

    CategoryReadOnlyDTO updateCategory(Long categoryId, CategoryUpdateDTO categoryUpdateDTO);

    List<CategoryReadOnlyDTO> getAllCategories();

    CategoryReadOnlyDTO getCategoryById(Long categoryId);

    CategoryReadOnlyDTO deleteCategory(Long categoryId);


}
