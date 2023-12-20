package gr.aueb.cf4.orderapp.rest;

import gr.aueb.cf4.orderapp.dto.WishlistItemCreateDTO;
import gr.aueb.cf4.orderapp.dto.WishlistItemReadOnlyDTO;
import gr.aueb.cf4.orderapp.dto.WishlistItemUpdateDTO;
import gr.aueb.cf4.orderapp.service.IWishlistItemService;
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
@RequestMapping("/api/wishlist-items")
public class WishlistItemController {

    private final IWishlistItemService wishlistItemService;

    @Autowired
    public WishlistItemController(IWishlistItemService wishlistItemService) {
        this.wishlistItemService = wishlistItemService;
    }

    @Operation(summary = "Create a new Wishlist Item For the current user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Wishlist Item created",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = WishlistItemReadOnlyDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid input was supplied",
                    content = @Content)})
    @PostMapping
    public ResponseEntity<WishlistItemReadOnlyDTO> createWishlistItem(@RequestBody WishlistItemCreateDTO wishlistItemCreateDTO) {
        WishlistItemReadOnlyDTO createdWishlistItem = wishlistItemService.createWishlistItem(wishlistItemCreateDTO);
        if (createdWishlistItem != null) {
            return new ResponseEntity<>(createdWishlistItem, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Get all Wishlist Items for a given Wishlist")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Wishlist Items Found",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = WishlistItemReadOnlyDTO.class)))}),
            @ApiResponse(responseCode = "404", description = "Wishlist not found",
                    content = @Content)})
    @GetMapping("/wishlist/{wishlistId}")
    public ResponseEntity<List<WishlistItemReadOnlyDTO>> getAllWishlistItemsByWishlistId(@PathVariable Long wishlistId) {
        List<WishlistItemReadOnlyDTO> wishlistItems = wishlistItemService.getAllWishlistItemsByWishlistId(wishlistId);
        if (!wishlistItems.isEmpty()) {
            return new ResponseEntity<>(wishlistItems, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Get a Wishlist Item by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Wishlist Item Found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = WishlistItemReadOnlyDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Wishlist Item not found",
                    content = @Content)})
    @GetMapping("/{wishlistItemId}")
    public ResponseEntity<WishlistItemReadOnlyDTO> getWishlistItemById(@PathVariable Long wishlistItemId) {
        WishlistItemReadOnlyDTO wishlistItem = wishlistItemService.getWishlistItemById(wishlistItemId);
        if (wishlistItem != null) {
            return new ResponseEntity<>(wishlistItem, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Update a Wishlist Item by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Wishlist Item updated",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = WishlistItemReadOnlyDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid input was supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Wishlist Item not found",
                    content = @Content)})
    @PutMapping("/{wishlistItemId}")
    public ResponseEntity<WishlistItemReadOnlyDTO> updateWishlistItem(
            @PathVariable Long wishlistItemId,
            @RequestBody WishlistItemUpdateDTO wishlistItemUpdateDTO) {
        WishlistItemReadOnlyDTO updatedWishlistItem = wishlistItemService.updateWishlistItem(wishlistItemId, wishlistItemUpdateDTO);
        if (updatedWishlistItem != null) {
            return new ResponseEntity<>(updatedWishlistItem, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Delete a Wishlist Item by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Wishlist Item Deleted",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = WishlistItemReadOnlyDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Wishlist Item no\t found",
                    content = @Content)})
    @DeleteMapping("/{wishlistItemId}")
    public ResponseEntity<WishlistItemReadOnlyDTO> deleteWishlistItem(@PathVariable Long wishlistItemId) {
        WishlistItemReadOnlyDTO deletedWishlistItem = wishlistItemService.deleteWishlistItem(wishlistItemId);
        if (deletedWishlistItem != null) {
            return new ResponseEntity<>(deletedWishlistItem, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
