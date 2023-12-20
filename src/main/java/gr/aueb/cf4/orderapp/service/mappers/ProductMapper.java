package gr.aueb.cf4.orderapp.service.mappers;

import gr.aueb.cf4.orderapp.dto.ProductCreateDTO;
import gr.aueb.cf4.orderapp.dto.ProductReadOnlyDTO;
import gr.aueb.cf4.orderapp.dto.ProductUpdateDTO;
import gr.aueb.cf4.orderapp.dto.SubcategoryReadOnlyDTO;
import gr.aueb.cf4.orderapp.model.Product;
import gr.aueb.cf4.orderapp.model.Subcategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class ProductMapper {

    private final SubcategoryMapper subcategoryMapper;

    @Autowired
    public ProductMapper(SubcategoryMapper subcategoryMapper) {
        this.subcategoryMapper = subcategoryMapper;
    }

    public ProductReadOnlyDTO convertToProductReadOnlyDTO(Product product) {
        return new ProductReadOnlyDTO(
                product.getId(),
                product.getName(),
                product.getPhotoUrl(),
                product.getSizes(),
                subcategoryMapper.convertToSubcategoryReadOnlyDTO(product.getSubcategory())
        );
    }

    public Product convertToProductEntity(ProductReadOnlyDTO productReadOnlyDTO, Subcategory subcategory) {
        if (productReadOnlyDTO == null) {
            return null;
        }

        Product product = new Product();
        product.setName(productReadOnlyDTO.getName());
        product.setPhotoUrl(productReadOnlyDTO.getPhotoUrl());
        product.setSizes(productReadOnlyDTO.getSizes());
        product.setSubcategory(subcategory);

        return product;
    }

    public Product convertToProductEntity(ProductCreateDTO productCreateDTO, Subcategory subcategory) {
        if (productCreateDTO == null) {
            return null;
        }

        Product product = new Product();
        product.setName(productCreateDTO.getName());
        product.setPhotoUrl(productCreateDTO.getPhotoUrl());
        product.setSizes(productCreateDTO.getSizes());
        product.setSubcategory(subcategory);

        return product;
    }

    public void updateProductFromDTO(Product product, ProductUpdateDTO productUpdateDTO) {
        if (productUpdateDTO == null) {
            return;
        }

        if (productUpdateDTO.getName() != null) {
            product.setName(productUpdateDTO.getName());
        }

        if (productUpdateDTO.getPhotoUrl() != null) {
            product.setPhotoUrl(productUpdateDTO.getPhotoUrl());
        }

        if (productUpdateDTO.getSizes() != null) {
            product.setSizes(productUpdateDTO.getSizes());
        }

        if (productUpdateDTO.getSubcategoryId() != null) {
            Subcategory subcategory = subcategoryMapper.convertToSubcategoryEntity(productUpdateDTO.getSubcategoryId());
            product.setSubcategory(subcategory);
        }
    }

    public Product convertToProductEntity(ProductReadOnlyDTO productReadOnlyDTO) {
        if (productReadOnlyDTO == null) {
            return null;
        }

        Product product = new Product();
        product.setId(productReadOnlyDTO.getId());
        product.setName(productReadOnlyDTO.getName());
        product.setPhotoUrl(productReadOnlyDTO.getPhotoUrl());
        product.setSizes(productReadOnlyDTO.getSizes());

        SubcategoryReadOnlyDTO subcategoryDTO = productReadOnlyDTO.getSubcategory();
        if (subcategoryDTO != null) {
            Subcategory subcategory = subcategoryMapper.convertToSubcategoryEntity(subcategoryDTO);
            product.setSubcategory(subcategory);
        }

        return product;
    }
}
