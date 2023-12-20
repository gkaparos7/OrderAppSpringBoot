package gr.aueb.cf4.orderapp.rest;

import gr.aueb.cf4.orderapp.dto.CategoryCreateDTO;
import gr.aueb.cf4.orderapp.dto.CategoryReadOnlyDTO;
import gr.aueb.cf4.orderapp.dto.CategoryUpdateDTO;
import gr.aueb.cf4.orderapp.service.ICategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;


@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final ICategoryService categoryService;
    private static final Logger logger = LoggerFactory.getLogger(CategoryController.class);

    @Autowired
    public CategoryController(ICategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Operation(summary = "Create a new Category")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Category created",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CategoryReadOnlyDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid input was supplied",
                    content = @Content)})
    @PostMapping
    public ResponseEntity<CategoryReadOnlyDTO> createCategory(@RequestBody CategoryCreateDTO categoryCreateDTO) {
        CategoryReadOnlyDTO createdCategory = categoryService.createCategory(categoryCreateDTO);
        if (createdCategory != null) {
            return new ResponseEntity<>(createdCategory, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Get all Categories")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Categories Found",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = CategoryReadOnlyDTO.class)))})})
    @GetMapping
    public ResponseEntity<List<CategoryReadOnlyDTO>> getAllCategories(HttpServletRequest request) {
        // Log headers explicitly
        Collections.list(request.getHeaderNames())
                .forEach(headerName -> logger.debug("{}: {}", headerName, request.getHeader(headerName)));
        List<CategoryReadOnlyDTO> categories = categoryService.getAllCategories();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @Operation(summary = "Get a Category by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Category Found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CategoryReadOnlyDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Category not found",
                    content = @Content)})
    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryReadOnlyDTO> getCategoryById(@PathVariable Long categoryId) {
        CategoryReadOnlyDTO category = categoryService.getCategoryById(categoryId);
        if (category != null) {
            return new ResponseEntity<>(category, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Update a Category by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Category updated",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CategoryReadOnlyDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid input was supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Category not found",
                    content = @Content)})
    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryReadOnlyDTO> updateCategory(
            @PathVariable Long categoryId,
            @RequestBody CategoryUpdateDTO categoryUpdateDTO) {
        CategoryReadOnlyDTO updatedCategory = categoryService.updateCategory(categoryId, categoryUpdateDTO);
        if (updatedCategory != null) {
            return new ResponseEntity<>(updatedCategory, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Delete a Category by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Category Deleted",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CategoryReadOnlyDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Category not found",
                    content = @Content)})
    @DeleteMapping("/{categoryId}")
    public ResponseEntity<CategoryReadOnlyDTO> deleteCategory(@PathVariable Long categoryId) {
        CategoryReadOnlyDTO deletedCategory = categoryService.deleteCategory(categoryId);
        if (deletedCategory != null) {
            return new ResponseEntity<>(deletedCategory, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}



