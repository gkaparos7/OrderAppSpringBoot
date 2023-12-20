package gr.aueb.cf4.orderapp.service.mappers;

import gr.aueb.cf4.orderapp.dto.*;
import gr.aueb.cf4.orderapp.model.Category;
import gr.aueb.cf4.orderapp.model.Subcategory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CategoryMapper {

    public static CategoryReadOnlyDTO convertToCategoryReadOnlyDTO(Category category) {
        if (category == null) {
            return null;
        }

        return new CategoryReadOnlyDTO(
                category.getId(),
                category.getName(),
                category.getPhotoUrl(),
                category.getSubcategories()
        );
    }

    public static List<SubcategoryReadOnlyDTO> convertSubcategoriesToReadOnlyDTO(List<Subcategory> subcategories) {
        return subcategories.stream()
                .map(subcategory -> new SubcategoryReadOnlyDTO(subcategory.getId(), subcategory.getName(), subcategory.getProducts()))
                .collect(Collectors.toList());
    }

    public static List<Subcategory> createSubcategories(List<SubcategoryCreateDTO> subcategoryCreateDTOs, Category category) {
        List<Subcategory> subcategories = new ArrayList<>();
        for (SubcategoryCreateDTO subcategoryCreateDTO : subcategoryCreateDTOs) {
            Subcategory subcategory = new Subcategory();
            subcategory.setName(subcategoryCreateDTO.getName());
            subcategory.setCategory(category);
            subcategories.add(subcategory);
        }
        return subcategories;
    }


}

