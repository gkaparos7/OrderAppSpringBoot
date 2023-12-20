package gr.aueb.cf4.orderapp.dto;

import gr.aueb.cf4.orderapp.model.User;
import lombok.Data;

import java.util.List;

@Data
public class WishlistCreateDTO {
    private User user;
    private List<WishlistItemReadOnlyDTO> wishlistItems;

    public WishlistCreateDTO(User user, List<WishlistItemReadOnlyDTO> wishlistItems) {
        this.user = user;
        this.wishlistItems = wishlistItems;
    }
}

