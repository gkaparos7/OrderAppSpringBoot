package gr.aueb.cf4.orderapp.service;

import gr.aueb.cf4.orderapp.dto.*;
import gr.aueb.cf4.orderapp.model.Category;
import gr.aueb.cf4.orderapp.model.Subcategory;
import gr.aueb.cf4.orderapp.repository.CategoryRepository;
import gr.aueb.cf4.orderapp.service.exceptions.CategoryNotFoundException;
import gr.aueb.cf4.orderapp.service.mappers.CategoryMapper;
import gr.aueb.cf4.orderapp.service.mappers.SubcategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import java.util.stream.Collectors;

import static gr.aueb.cf4.orderapp.service.mappers.CategoryMapper.convertToCategoryReadOnlyDTO;


@Service
public class CategoryServiceImpl implements ICategoryService {


    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;

    }


    @Override
    @Transactional
    public CategoryReadOnlyDTO createCategory(CategoryCreateDTO categoryCreateDTO) {
        Category category = new Category();
        category.setName(categoryCreateDTO.getName());
        category.setPhotoUrl(categoryCreateDTO.getPhotoUrl());

        Category savedCategory = categoryRepository.save(category);
        return convertToCategoryReadOnlyDTO(savedCategory);
    }

    @Override
    @Transactional
    public CategoryReadOnlyDTO updateCategory(Long categoryId, CategoryUpdateDTO categoryUpdateDTO) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException("Category not found with id: " + categoryId));

        category.setName(categoryUpdateDTO.getName());
        category.setPhotoUrl(categoryUpdateDTO.getPhotoUrl());

        Category updatedCategory = categoryRepository.save(category);
        return convertToCategoryReadOnlyDTO(updatedCategory);
    }


    @Override
    public List<CategoryReadOnlyDTO> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream()
                .map(category -> CategoryMapper.convertToCategoryReadOnlyDTO(category))
                .collect(Collectors.toList());
    }


    @Override
    public CategoryReadOnlyDTO getCategoryById(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException("Category not found with id: " + categoryId));

        CategoryReadOnlyDTO categoryReadOnlyDTO = convertToCategoryReadOnlyDTO(category);
        List<SubcategoryReadOnlyDTO> subcategoriesDTO = SubcategoryMapper.convertSubcategoriesToReadOnlyDTO(category.getSubcategories());
        List<Subcategory> subcategories = subcategoriesDTO.stream()
                .map(subcategoryDTO -> new Subcategory(subcategoryDTO.getId(), subcategoryDTO.getName()))
                .collect(Collectors.toList());

        categoryReadOnlyDTO.setSubcategories(subcategories);

        return categoryReadOnlyDTO;
    }

    @Override
    @Transactional
    public CategoryReadOnlyDTO deleteCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException("Category not found with id: " + categoryId));
        categoryRepository.delete(category);
        return convertToCategoryReadOnlyDTO(category);
    }
}




