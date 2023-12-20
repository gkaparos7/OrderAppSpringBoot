package gr.aueb.cf4.orderapp.service;

import gr.aueb.cf4.orderapp.dto.ProductCreateDTO;
import gr.aueb.cf4.orderapp.dto.ProductReadOnlyDTO;
import gr.aueb.cf4.orderapp.dto.ProductUpdateDTO;
import gr.aueb.cf4.orderapp.model.Product;

import java.util.List;

public interface IProductService {
    ProductReadOnlyDTO createProduct(ProductCreateDTO productCreateDTO);

    ProductReadOnlyDTO updateProduct(Long productId, ProductUpdateDTO productUpdateDTO);

    List<ProductReadOnlyDTO> getAllProducts();

    ProductReadOnlyDTO getProductById(Long productId);

    ProductReadOnlyDTO deleteProduct(Long productId);

    Product getProductEntityById(Long productId);

    List<ProductReadOnlyDTO> getProductsBySubcategory(Long subcategoryId);
}

