package gr.aueb.cf4.orderapp.service.mappers;

import gr.aueb.cf4.orderapp.dto.WishlistItemReadOnlyDTO;
import gr.aueb.cf4.orderapp.dto.WishlistReadOnlyDTO;
import gr.aueb.cf4.orderapp.model.User;
import gr.aueb.cf4.orderapp.model.Wishlist;
import gr.aueb.cf4.orderapp.model.WishlistItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class WishlistMapper {

    private final UserMapper userMapper;
    private final ProductMapper productMapper;
    private final WishlistItemMapper wishlistItemMapper;

    @Autowired
    public WishlistMapper(UserMapper userMapper, ProductMapper productMapper, WishlistItemMapper wishlistItemMapper) {
        this.userMapper = userMapper;
        this.productMapper = productMapper;
        this.wishlistItemMapper = wishlistItemMapper;
    }

    public WishlistReadOnlyDTO convertToWishlistReadOnlyDTO(Wishlist wishlist) {
        List<WishlistItemReadOnlyDTO> wishlistItems = wishlist.getWishlistItems().stream()
                .map(wishlistItem -> new WishlistItemReadOnlyDTO(
                        wishlistItem.getId(),
                        wishlistItem.getQuantity(),
                        wishlistItem.getSize(),
                        // Assuming you have a ProductMapper
                        productMapper.convertToProductReadOnlyDTO(wishlistItem.getProduct()),
                        wishlistItem.getWishlist()
                ))
                .collect(Collectors.toList());

        return new WishlistReadOnlyDTO(
                wishlist.getId(),
                userMapper.convertToUserReadOnlyDTO(wishlist.getUser()),
                wishlistItems
        );
    }

    public Wishlist convertToWishlistEntity(WishlistReadOnlyDTO wishlistReadOnlyDTO) {
        if (wishlistReadOnlyDTO == null) {
            return null;
        }

        Wishlist wishlist = new Wishlist();
        wishlist.setId(wishlistReadOnlyDTO.getId());

        User user = userMapper.convertToUserEntity(wishlistReadOnlyDTO.getUser());
        wishlist.setUser(user);

        // Convert WishlistItemReadOnlyDTO list to WishlistItem list
        List<WishlistItem> wishlistItems = wishlistItemMapper.convertToWishlistItemEntityList(wishlistReadOnlyDTO.getWishlistItems());
        wishlist.setWishlistItems(wishlistItems);

        return wishlist;
    }
}
