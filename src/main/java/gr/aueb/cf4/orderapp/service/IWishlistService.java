package gr.aueb.cf4.orderapp.service;


import gr.aueb.cf4.orderapp.dto.WishlistReadOnlyDTO;


import gr.aueb.cf4.orderapp.model.User;
import gr.aueb.cf4.orderapp.model.Wishlist;



public interface IWishlistService {
    WishlistReadOnlyDTO clearWishlist();
    WishlistReadOnlyDTO getWishlistForCurrentUser();
}

