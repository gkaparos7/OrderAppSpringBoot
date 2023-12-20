package gr.aueb.cf4.orderapp.service;

import gr.aueb.cf4.orderapp.dto.SubcategoryCreateDTO;
import gr.aueb.cf4.orderapp.dto.SubcategoryReadOnlyDTO;
import gr.aueb.cf4.orderapp.dto.SubcategoryUpdateDTO;
import gr.aueb.cf4.orderapp.model.Category;
import gr.aueb.cf4.orderapp.model.Subcategory;
import gr.aueb.cf4.orderapp.repository.CategoryRepository;
import gr.aueb.cf4.orderapp.repository.SubcategoryRepository;
import gr.aueb.cf4.orderapp.service.exceptions.CategoryIdIsEmptyException;
import gr.aueb.cf4.orderapp.service.exceptions.CategoryNotFoundException;
import gr.aueb.cf4.orderapp.service.exceptions.SubcategoryNotFoundException;
import gr.aueb.cf4.orderapp.service.mappers.SubcategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SubcategoryServiceImpl implements ISubcategoryService {

    private final SubcategoryRepository subcategoryRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public SubcategoryServiceImpl(SubcategoryRepository subcategoryRepository, CategoryRepository categoryRepository) {
        this.subcategoryRepository = subcategoryRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    @Transactional
    public SubcategoryReadOnlyDTO createSubcategory(SubcategoryCreateDTO subcategoryCreateDTO) {
        Subcategory subcategory = new Subcategory();
        subcategory.setName(subcategoryCreateDTO.getName());

        Long categoryId = subcategoryCreateDTO.getCategory().getId();

        if (categoryId != null) {
            Optional<Category> optionalCategory = categoryRepository.findById(categoryId);

            if (optionalCategory.isPresent()) {
                subcategory.setCategory(optionalCategory.get());
                Subcategory savedSubcategory = subcategoryRepository.save(subcategory);
                return SubcategoryMapper.convertToSubcategoryReadOnlyDTO(savedSubcategory);
            } else {
                throw new CategoryNotFoundException("Category not found with ID: " + categoryId);
            }
        } else {
            // Handle the scenario where categoryId is null
            throw new CategoryIdIsEmptyException("Category ID is empty or null.");
        }
    }

    @Override
    @Transactional
    public SubcategoryReadOnlyDTO updateSubcategory(Long subcategoryId, SubcategoryUpdateDTO subcategoryUpdateDTO) {
        Optional<Subcategory> optionalSubcategory = subcategoryRepository.findById(subcategoryId);

        if (optionalSubcategory.isPresent()) {
            Subcategory subcategory = optionalSubcategory.get();
            subcategory.setName(subcategoryUpdateDTO.getName());

            Long categoryId = subcategoryUpdateDTO.getCategory().getId();

            if (categoryId != null) {
                Optional<Category> optionalCategory = categoryRepository.findById(categoryId);

                if (optionalCategory.isPresent()) {
                    subcategory.setCategory(optionalCategory.get());
                    Subcategory updatedSubcategory = subcategoryRepository.save(subcategory);
                    return SubcategoryMapper.convertToSubcategoryReadOnlyDTO(updatedSubcategory);
                } else {
                    // Handle category not found scenario
                    throw new CategoryNotFoundException("Category not found with ID: " + categoryId);
                }
            } else {
                // Handle null category ID scenario
                throw new CategoryIdIsEmptyException("Category ID is null");
            }
        } else {
            // Handle subcategory not found scenario
            throw new SubcategoryNotFoundException("Subcategory not found with ID: " + subcategoryId);
        }
    }

    @Override
    public Optional<Subcategory> getSubcategoryEntityById(Long subcategoryId) {
        return subcategoryRepository.findById(subcategoryId);
    }

    @Override
    public List<SubcategoryReadOnlyDTO> getAllSubcategories() {
        List<Subcategory> subcategories = subcategoryRepository.findAll();
        return subcategories.stream()
                .map(SubcategoryMapper::convertToSubcategoryReadOnlyDTO)
                .collect(Collectors.toList());
    }

    @Override
    public SubcategoryReadOnlyDTO getSubcategoryById(Long subcategoryId) {
        Optional<Subcategory> optionalSubcategory = subcategoryRepository.findById(subcategoryId);
        return optionalSubcategory.map(SubcategoryMapper::convertToSubcategoryReadOnlyDTO).orElse(null);
    }

    @Override
    @Transactional
    public SubcategoryReadOnlyDTO deleteSubcategory(Long subcategoryId) {
        Subcategory subcategory = subcategoryRepository.findById(subcategoryId)
                .orElseThrow(() -> new SubcategoryNotFoundException("Subcategory not found with id: " + subcategoryId));

        subcategoryRepository.delete(subcategory);
        return SubcategoryMapper.convertToSubcategoryReadOnlyDTO(subcategory);
    }

    @Override
    public List<SubcategoryReadOnlyDTO> getSubcategoriesInCategory(Long categoryId) {
        // Fetch the category from the CategoryRepository
        Optional<Category> optionalCategory = categoryRepository.findById(categoryId);

        if (optionalCategory.isPresent()) {
            Category category = optionalCategory.get();
            List<Subcategory> subcategoriesInCategory = category.getSubcategories();
            return SubcategoryMapper.convertSubcategoriesToReadOnlyDTO(subcategoriesInCategory);
        } else {
            throw new CategoryNotFoundException("Category not found with ID: " + categoryId);
        }
    }

}
