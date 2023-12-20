package gr.aueb.cf4.orderapp.service;


import gr.aueb.cf4.orderapp.dto.WishlistItemCreateDTO;
import gr.aueb.cf4.orderapp.dto.WishlistItemReadOnlyDTO;
import gr.aueb.cf4.orderapp.dto.WishlistItemUpdateDTO;

import gr.aueb.cf4.orderapp.model.Wishlist;
import gr.aueb.cf4.orderapp.model.WishlistItem;

import java.util.List;

public interface IWishlistItemService {
    WishlistItemReadOnlyDTO createWishlistItem(WishlistItemCreateDTO wishlistItemCreateDTO);

    WishlistItemReadOnlyDTO updateWishlistItem(Long wishlistItemId, WishlistItemUpdateDTO wishlistItemUpdateDTO);

    WishlistItemReadOnlyDTO getWishlistItemById(Long wishlistItemId);

    WishlistItemReadOnlyDTO deleteWishlistItem(Long wishlistItemId);

    List<WishlistItemReadOnlyDTO> getAllWishlistItemsByWishlistId(Long wishlistId);

}
