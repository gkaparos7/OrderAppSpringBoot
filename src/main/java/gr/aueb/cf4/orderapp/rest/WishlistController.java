package gr.aueb.cf4.orderapp.rest;

import gr.aueb.cf4.orderapp.dto.WishlistReadOnlyDTO;
import gr.aueb.cf4.orderapp.service.IWishlistService;
import gr.aueb.cf4.orderapp.service.exceptions.WishlistNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/wishlists")
public class WishlistController {

    private final IWishlistService wishlistService;


    @Autowired
    public WishlistController(IWishlistService wishlistService) {
        this.wishlistService = wishlistService;

    }

    @Operation(summary = "Clear Wishlist for the Current User")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Wishlist cleared",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = WishlistReadOnlyDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Wishlist not found",
                    content = @Content)})
    @DeleteMapping("/clear")
    public ResponseEntity<WishlistReadOnlyDTO> clearWishlist() {
        try {
            WishlistReadOnlyDTO clearedWishlist = wishlistService.clearWishlist();
            return new ResponseEntity<>(clearedWishlist, HttpStatus.OK);
        } catch (WishlistNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Get Wishlist for the Current User")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Wishlist found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = WishlistReadOnlyDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Wishlist not found",
                    content = @Content)})
    @GetMapping("/current-user")
    public ResponseEntity<WishlistReadOnlyDTO> getWishlistForCurrentUser() {
        try {
            WishlistReadOnlyDTO wishlist = wishlistService.getWishlistForCurrentUser();
            return new ResponseEntity<>(wishlist, HttpStatus.OK);
        } catch (WishlistNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}



