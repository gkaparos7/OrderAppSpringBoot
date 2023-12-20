package gr.aueb.cf4.orderapp.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import gr.aueb.cf4.orderapp.model.Size;
import gr.aueb.cf4.orderapp.model.Wishlist;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WishlistItemReadOnlyDTO extends BaseDTO {
    private int quantity;
    private Size size;
    private ProductReadOnlyDTO product;
    @JsonIgnore
    private Wishlist wishlist;

    public WishlistItemReadOnlyDTO() {
    }

    public WishlistItemReadOnlyDTO(Long id, int quantity, Size size, ProductReadOnlyDTO product, Wishlist wishlist) {
        this.setId(id);
        this.quantity = quantity;
        this.size = size;
        this.product = product;
        this.wishlist = wishlist;
    }

}
