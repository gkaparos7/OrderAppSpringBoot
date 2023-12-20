package gr.aueb.cf4.orderapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "wishlists")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Wishlist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", unique = true)
    @JsonIgnore
    private User user;

    @OneToMany(mappedBy = "wishlist", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<WishlistItem> wishlistItems;

    @Override
    public String toString() {
        return "Wishlist{" +
                "id=" + id +
                ", // other properties" +
                ", user=" + user.getUsername() + // Assuming you have a getUsername() method in User class
                '}';
    }

    // Convenient method to add a WishlistItem to the wishlist
    public void addWishlistItem(WishlistItem wishlistItem) {
        if (wishlistItems == null) {
            wishlistItems = new ArrayList<>();
        }
        wishlistItems.add(wishlistItem);
        wishlistItem.setWishlist(this);
    }
}

