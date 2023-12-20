package gr.aueb.cf4.orderapp.rest;

import gr.aueb.cf4.orderapp.dto.SubcategoryCreateDTO;
import gr.aueb.cf4.orderapp.dto.SubcategoryReadOnlyDTO;
import gr.aueb.cf4.orderapp.dto.SubcategoryUpdateDTO;
import gr.aueb.cf4.orderapp.service.ISubcategoryService;
import gr.aueb.cf4.orderapp.service.exceptions.CategoryNotFoundException;
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
@RequestMapping("/api/subcategories")
public class SubcategoryController {

    private final ISubcategoryService subcategoryService;

    @Autowired
    public SubcategoryController(ISubcategoryService subcategoryService) {
        this.subcategoryService = subcategoryService;
    }

    @Operation(summary = "Create a new Subcategory")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Subcategory created",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = SubcategoryReadOnlyDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid input was supplied",
                    content = @Content)})
    @PostMapping
    public ResponseEntity<SubcategoryReadOnlyDTO> createSubcategory(@RequestBody SubcategoryCreateDTO subcategoryCreateDTO) {
        try {
            SubcategoryReadOnlyDTO createdSubcategory = subcategoryService.createSubcategory(subcategoryCreateDTO);
            return new ResponseEntity<>(createdSubcategory, HttpStatus.CREATED);
        } catch (CategoryNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Update a Subcategory by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Subcategory updated",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = SubcategoryReadOnlyDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid input was supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Subcategory not found",
                    content = @Content)})
    @PutMapping("/{subcategoryId}")
    public ResponseEntity<SubcategoryReadOnlyDTO> updateSubcategory(
            @PathVariable("subcategoryId") Long subcategoryId,
            @RequestBody SubcategoryUpdateDTO subcategoryUpdateDTO) {
        SubcategoryReadOnlyDTO updatedSubcategory = subcategoryService.updateSubcategory(subcategoryId, subcategoryUpdateDTO);
        if (updatedSubcategory != null) {
            return new ResponseEntity<>(updatedSubcategory, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Get all Subcategories")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Subcategories Found",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = SubcategoryReadOnlyDTO.class)))})})
    @GetMapping
    public ResponseEntity<List<SubcategoryReadOnlyDTO>> getAllSubcategories() {
        List<SubcategoryReadOnlyDTO> subcategories = subcategoryService.getAllSubcategories();
        return new ResponseEntity<>(subcategories, HttpStatus.OK);
    }

    @Operation(summary = "Get a Subcategory by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Subcategory Found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = SubcategoryReadOnlyDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Subcategory not found",
                    content = @Content)})
    @GetMapping("/{subcategoryId}")
    public ResponseEntity<SubcategoryReadOnlyDTO> getSubcategoryById(@PathVariable("subcategoryId") Long subcategoryId) {
        SubcategoryReadOnlyDTO subcategory = subcategoryService.getSubcategoryById(subcategoryId);
        if (subcategory != null) {
            return new ResponseEntity<>(subcategory, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Delete a Subcategory by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Subcategory Deleted",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = SubcategoryReadOnlyDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Subcategory not found",
                    content = @Content)})
    @DeleteMapping("/{subcategoryId}")
    public ResponseEntity<SubcategoryReadOnlyDTO> deleteSubcategory(@PathVariable("subcategoryId") Long subcategoryId) {
        SubcategoryReadOnlyDTO deletedSubcategory = subcategoryService.deleteSubcategory(subcategoryId);
        if (deletedSubcategory != null) {
            return new ResponseEntity<>(deletedSubcategory, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Get Subcategories in a Category")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Subcategories Found in Category",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = SubcategoryReadOnlyDTO.class)))}),
            @ApiResponse(responseCode = "404", description = "Category not found",
                    content = @Content)})
    @GetMapping("/in-category/{categoryId}")
    public ResponseEntity<List<SubcategoryReadOnlyDTO>> getSubcategoriesInCategory(@PathVariable Long categoryId) {
        try {
            List<SubcategoryReadOnlyDTO> subcategories = subcategoryService.getSubcategoriesInCategory(categoryId);
            return new ResponseEntity<>(subcategories, HttpStatus.OK);
        } catch (CategoryNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}


