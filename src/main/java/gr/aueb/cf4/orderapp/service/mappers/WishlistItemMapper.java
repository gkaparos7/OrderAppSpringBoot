package gr.aueb.cf4.orderapp.service.mappers;

import gr.aueb.cf4.orderapp.dto.WishlistItemCreateDTO;
import gr.aueb.cf4.orderapp.dto.WishlistItemReadOnlyDTO;
import gr.aueb.cf4.orderapp.model.Product;
import gr.aueb.cf4.orderapp.model.WishlistItem;
import gr.aueb.cf4.orderapp.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class WishlistItemMapper {

    private final ProductMapper productMapper;

    @Autowired
    public WishlistItemMapper(ProductMapper productMapper) {
        this.productMapper = productMapper;
    }

    public WishlistItem convertToWishlistItemEntity(WishlistItemReadOnlyDTO wishlistItemDTO) {
        if (wishlistItemDTO == null) {
            return null;
        }

        WishlistItem wishlistItem = new WishlistItem();
        wishlistItem.setId(wishlistItemDTO.getId());
        wishlistItem.setQuantity(wishlistItemDTO.getQuantity());
        wishlistItem.setSize(wishlistItemDTO.getSize());
        wishlistItem.setProduct(productMapper.convertToProductEntity(wishlistItemDTO.getProduct()));
        wishlistItem.setWishlist(wishlistItemDTO.getWishlist());

        return wishlistItem;
    }

    public WishlistItem convertToWishlistItemEntity(WishlistItemCreateDTO wishlistItemCreateDTO) {
        if (wishlistItemCreateDTO == null) {
            return null;
        }

        WishlistItem wishlistItem = new WishlistItem();
        wishlistItem.setQuantity(wishlistItemCreateDTO.getQuantity());
        wishlistItem.setSize(wishlistItemCreateDTO.getSize());
        wishlistItem.setProduct(wishlistItemCreateDTO.getProduct());

        return wishlistItem;
    }


    public List<WishlistItem> convertToWishlistItemEntityList(List<WishlistItemReadOnlyDTO> wishlistItemDTOList) {
        if (wishlistItemDTOList == null) {
            return null;
        }

        return wishlistItemDTOList.stream()
                .map(this::convertToWishlistItemEntity)
                .collect(Collectors.toList());
    }

    public WishlistItemReadOnlyDTO convertToWishlistItemReadOnlyDTO(WishlistItem wishlistItem) {
        return new WishlistItemReadOnlyDTO(
                wishlistItem.getId(),
                wishlistItem.getQuantity(),
                wishlistItem.getSize(),
                productMapper.convertToProductReadOnlyDTO(wishlistItem.getProduct()),
                wishlistItem.getWishlist()
        );
    }
}

