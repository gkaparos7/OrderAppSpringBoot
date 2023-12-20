package gr.aueb.cf4.orderapp.dto;

import lombok.Data;

import java.util.List;

@Data
public class WishlistUpdateDTO extends BaseDTO {
    private List<WishlistItemReadOnlyDTO> wishlistItems;

    public WishlistUpdateDTO(Long id, List<WishlistItemReadOnlyDTO> wishlistItems) {
        this.setId(id);
        this.wishlistItems = wishlistItems;
    }
}

