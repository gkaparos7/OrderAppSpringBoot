package gr.aueb.cf4.orderapp.rest;

import gr.aueb.cf4.orderapp.dto.ProductCreateDTO;
import gr.aueb.cf4.orderapp.dto.ProductReadOnlyDTO;
import gr.aueb.cf4.orderapp.dto.ProductUpdateDTO;
import gr.aueb.cf4.orderapp.service.IProductService;
import gr.aueb.cf4.orderapp.service.exceptions.SubcategoryNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final IProductService productService;

    @Autowired
    public ProductController(IProductService productService) {
        this.productService = productService;
    }

    @Operation(summary = "Create a new Product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Product created",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProductReadOnlyDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid input was supplied",
                    content = @Content)})
    @PostMapping
    public ResponseEntity<ProductReadOnlyDTO> createProduct(@RequestBody ProductCreateDTO productCreateDTO) {
        ProductReadOnlyDTO createdProduct = productService.createProduct(productCreateDTO);
        if (createdProduct != null) {
            return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Get all Products")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Products Found",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = ProductReadOnlyDTO.class)))})})
    @GetMapping
    public ResponseEntity<List<ProductReadOnlyDTO>> getAllProducts() {
        List<ProductReadOnlyDTO> products = productService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @Operation(summary = "Get a Product by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product Found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProductReadOnlyDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Product not found",
                    content = @Content)})
    @GetMapping("/{productId}")
    public ResponseEntity<ProductReadOnlyDTO> getProductById(@PathVariable Long productId) {
        ProductReadOnlyDTO product = productService.getProductById(productId);
        if (product != null) {
            return new ResponseEntity<>(product, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Update a Product by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product updated",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProductReadOnlyDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid input was supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Product not found",
                    content = @Content)})
    @PutMapping("/{productId}")
    public ResponseEntity<ProductReadOnlyDTO> updateProduct(
            @PathVariable Long productId,
            @RequestBody ProductUpdateDTO productUpdateDTO) {
        ProductReadOnlyDTO updatedProduct = productService.updateProduct(productId, productUpdateDTO);
        if (updatedProduct != null) {
            return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Delete a Product by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product Deleted",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProductReadOnlyDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Product not found",
                    content = @Content)})
    @DeleteMapping("/{productId}")
    public ResponseEntity<ProductReadOnlyDTO> deleteProduct(@PathVariable Long productId) {
        ProductReadOnlyDTO deletedProduct = productService.deleteProduct(productId);
        if (deletedProduct != null) {
            return new ResponseEntity<>(deletedProduct, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Get Products by Subcategory")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Products Found for Subcategory",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = ProductReadOnlyDTO.class)))}),
            @ApiResponse(responseCode = "404", description = "Subcategory not found",
                    content = @Content)})
    @GetMapping("/by-subcategory/{subcategoryId}")
    public ResponseEntity<List<ProductReadOnlyDTO>> getProductsBySubcategory(@PathVariable Long subcategoryId) {
        try {
            List<ProductReadOnlyDTO> products = productService.getProductsBySubcategory(subcategoryId);
            return new ResponseEntity<>(products, HttpStatus.OK);
        } catch (SubcategoryNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}



