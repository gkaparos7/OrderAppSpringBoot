package gr.aueb.cf4.orderapp.service;

import gr.aueb.cf4.orderapp.dto.ProductCreateDTO;
import gr.aueb.cf4.orderapp.dto.ProductReadOnlyDTO;
import gr.aueb.cf4.orderapp.dto.ProductUpdateDTO;

import gr.aueb.cf4.orderapp.model.Product;
import gr.aueb.cf4.orderapp.model.Subcategory;
import gr.aueb.cf4.orderapp.repository.ProductRepository;
import gr.aueb.cf4.orderapp.service.exceptions.ProductNotFoundException;
import gr.aueb.cf4.orderapp.service.exceptions.SubcategoryNotFoundException;
import gr.aueb.cf4.orderapp.service.mappers.ProductMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements IProductService {

    private final ProductRepository productRepository;
    private final ISubcategoryService subcategoryService;
    private final ProductMapper productMapper;

    @Autowired
    public ProductServiceImpl(
            ProductRepository productRepository,
            ISubcategoryService subcategoryService,
            ProductMapper productMapper
    ) {
        this.productRepository = productRepository;
        this.subcategoryService = subcategoryService;
        this.productMapper = productMapper;
    }

    @Override
    @Transactional
    public ProductReadOnlyDTO createProduct(ProductCreateDTO productCreateDTO) {
        Product product = productMapper.convertToProductEntity(productCreateDTO, null);

        Subcategory subcategory = subcategoryService.getSubcategoryEntityById(productCreateDTO.getSubcategoryId())
                .orElseThrow(() -> new SubcategoryNotFoundException("Subcategory not found with ID: " + productCreateDTO.getSubcategoryId()));

        product.setSubcategory(subcategory);
        Product savedProduct = productRepository.save(product);
        return productMapper.convertToProductReadOnlyDTO(savedProduct);
    }

    @Override
    @Transactional
    public ProductReadOnlyDTO updateProduct(Long productId, ProductUpdateDTO productUpdateDTO) {
        Optional<Product> optionalProduct = Optional.ofNullable(getProductEntityById(productId));
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            productMapper.updateProductFromDTO(product, productUpdateDTO);

            Optional<Subcategory> optionalSubcategory = subcategoryService.getSubcategoryEntityById(productUpdateDTO.getSubcategoryId());
            if (optionalSubcategory.isPresent()) {
                Subcategory subcategory = optionalSubcategory.get();
                product.setSubcategory(subcategory);
                Product updatedProduct = productRepository.save(product);
                return productMapper.convertToProductReadOnlyDTO(updatedProduct);
            } else {
                throw new SubcategoryNotFoundException("Subcategory not found with ID: " + productUpdateDTO.getSubcategoryId());
            }
        } else {
            throw new ProductNotFoundException("Product not found with ID: " + productId);
        }
    }


    @Override
    public List<ProductReadOnlyDTO> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(productMapper::convertToProductReadOnlyDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ProductReadOnlyDTO getProductById(Long productId) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        return optionalProduct.map(productMapper::convertToProductReadOnlyDTO).orElse(null);
    }

    @Override
    @Transactional
    public ProductReadOnlyDTO deleteProduct(Long productId) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            productRepository.delete(product);
            return productMapper.convertToProductReadOnlyDTO(product);
        } else {
            // Throw ProductNotFoundException if the product is not found
            throw new ProductNotFoundException("Product not found with id: " + productId);
        }
    }

    @Override
    public Product getProductEntityById(Long productId) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        return optionalProduct.orElse(null);
    }

    @Override
    public List<ProductReadOnlyDTO> getProductsBySubcategory(Long subcategoryId) {
        Subcategory subcategory = subcategoryService.getSubcategoryEntityById(subcategoryId)
                .orElseThrow(() -> new SubcategoryNotFoundException("Subcategory not found with ID: " + subcategoryId));

        List<Product> products = productRepository.findBySubcategory(subcategory);
        return products.stream()
                .map(productMapper::convertToProductReadOnlyDTO)
                .collect(Collectors.toList());
    }
}
