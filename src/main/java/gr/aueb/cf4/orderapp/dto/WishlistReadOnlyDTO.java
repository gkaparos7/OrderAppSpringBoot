package gr.aueb.cf4.orderapp.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WishlistReadOnlyDTO extends BaseDTO {
    @JsonIgnore
    private UserReadOnlyDTO user; // User associated with the wishlist
    private List<WishlistItemReadOnlyDTO> wishlistItems;

    public WishlistReadOnlyDTO(Long id, UserReadOnlyDTO user, List<WishlistItemReadOnlyDTO> wishlistItems) {
        this.setId(id);
        this.user = user;
        this.wishlistItems = wishlistItems;
    }
}

