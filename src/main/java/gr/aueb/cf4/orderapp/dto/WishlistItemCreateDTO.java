package gr.aueb.cf4.orderapp.dto;


import gr.aueb.cf4.orderapp.model.Product;
import gr.aueb.cf4.orderapp.model.Size;
import gr.aueb.cf4.orderapp.model.Wishlist;
import lombok.Data;

@Data
public class WishlistItemCreateDTO {
    private int quantity;
    private Size size;
    private Product product;
    private Wishlist wishlist;

    public WishlistItemCreateDTO() {
    }

    public WishlistItemCreateDTO(int quantity, Size size, Product product) {
        this.quantity = quantity;
        this.size = size;
        this.product = product;
    }

    public WishlistItemCreateDTO(int quantity, Size size, Product product, Wishlist wishlist) {
        this.quantity = quantity;
        this.size = size;
        this.product = product;
        this.wishlist = wishlist;
    }
}
