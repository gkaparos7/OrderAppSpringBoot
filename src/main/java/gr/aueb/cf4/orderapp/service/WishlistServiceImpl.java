package gr.aueb.cf4.orderapp.service;

import gr.aueb.cf4.orderapp.dto.*;

import gr.aueb.cf4.orderapp.model.User;
import gr.aueb.cf4.orderapp.model.Wishlist;
import gr.aueb.cf4.orderapp.repository.WishlistRepository;
import gr.aueb.cf4.orderapp.service.exceptions.UserNotFoundException;
import gr.aueb.cf4.orderapp.service.exceptions.WishlistNotFoundException;
import gr.aueb.cf4.orderapp.service.mappers.WishlistMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Service
public class WishlistServiceImpl implements IWishlistService {

    private final WishlistRepository wishlistRepository;
    private final IUserService userService;
    private final WishlistMapper wishlistMapper;

    @Autowired
    public WishlistServiceImpl(WishlistRepository wishlistRepository, IUserService userService, WishlistMapper wishlistMapper) {
        this.wishlistRepository = wishlistRepository;
        this.userService = userService;
        this.wishlistMapper = wishlistMapper;
    }

    @Override
    @Transactional
    public WishlistReadOnlyDTO clearWishlist() {
        // Get the currently logged-in user
        User user = userService.getCurrentUser();

        Wishlist wishlist = wishlistRepository.findByUser(user);

        if (wishlist != null) {
            wishlist.getWishlistItems().clear();
            wishlistRepository.save(wishlist);
        }

        // Convert the updated Wishlist entity to WishlistReadOnlyDTO
        return wishlistMapper.convertToWishlistReadOnlyDTO(wishlist);
    }

    @Override
    public WishlistReadOnlyDTO getWishlistForCurrentUser() {
        User user = userService.getCurrentUser();
        if (user != null) {
            Wishlist wishlist = user.getWishlist();
            if (wishlist != null) {
                WishlistReadOnlyDTO wishlistDTO = wishlistMapper.convertToWishlistReadOnlyDTO(wishlist);
                return wishlistDTO;
            } else {
                throw new WishlistNotFoundException("Wishlist not found...");
            }
        } else {
            throw new UserNotFoundException("No authenticated user found.");
        }
    }
}


