package gr.aueb.cf4.orderapp.service.mappers;

import gr.aueb.cf4.orderapp.dto.CategoryReadOnlyDTO;
import gr.aueb.cf4.orderapp.dto.SubcategoryReadOnlyDTO;
import gr.aueb.cf4.orderapp.model.Category;
import gr.aueb.cf4.orderapp.model.Subcategory;
import gr.aueb.cf4.orderapp.repository.SubcategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class SubcategoryMapper {

    private final SubcategoryRepository subcategoryRepository;

    @Autowired
    public SubcategoryMapper(SubcategoryRepository subcategoryRepository) {
        this.subcategoryRepository = subcategoryRepository;
    }

    public static Subcategory convertToSubcategoryEntity(SubcategoryReadOnlyDTO subcategoryReadOnlyDTO, Category category) {
        if (subcategoryReadOnlyDTO == null) {
            return null;
        }
        Subcategory subcategory = new Subcategory();
        subcategory.setId(subcategoryReadOnlyDTO.getId());
        subcategory.setName(subcategoryReadOnlyDTO.getName());

        subcategory.setCategory(category);
        return subcategory;
    }



    public static SubcategoryReadOnlyDTO convertToSubcategoryReadOnlyDTO(Subcategory subcategory) {
        return new SubcategoryReadOnlyDTO(
                subcategory.getId(),
                subcategory.getName(),
                subcategory.getPhotoUrl(),
                subcategory.getProducts()
        );
    }


    public static List<SubcategoryReadOnlyDTO> convertSubcategoriesToReadOnlyDTO(List<Subcategory> subcategories) {
        return subcategories.stream()
                .map(subcategory -> new SubcategoryReadOnlyDTO(subcategory.getId(), subcategory.getName(), subcategory.getPhotoUrl(), subcategory.getProducts()))
                .collect(Collectors.toList());
    }

    public Subcategory convertToSubcategoryEntity(Long subcategoryId) {
        if (subcategoryId == null) {
            return null;
        }
        // Fetch Subcategory entity from repository or create a new instance
        Optional<Subcategory> optionalSubcategory = subcategoryRepository.findById(subcategoryId);
        return optionalSubcategory.orElse(null);
    }

    public Subcategory convertToSubcategoryEntity(SubcategoryReadOnlyDTO subcategoryReadOnlyDTO) {
        if (subcategoryReadOnlyDTO == null) {
            return null;
        }
        Category category = convertToCategoryEntity(subcategoryReadOnlyDTO.getCategory());

        return convertToSubcategoryEntity(subcategoryReadOnlyDTO, category);
    }

    private Category convertToCategoryEntity(CategoryReadOnlyDTO categoryReadOnlyDTO) {
        if (categoryReadOnlyDTO == null) {
            return null;
        }

        Category category = new Category();
        category.setId(categoryReadOnlyDTO.getId());
        category.setName(categoryReadOnlyDTO.getName());
        category.setPhotoUrl(categoryReadOnlyDTO.getPhotoUrl());

        return category;
    }
}
