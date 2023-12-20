package gr.aueb.cf4.orderapp.service;

import gr.aueb.cf4.orderapp.dto.*;
import gr.aueb.cf4.orderapp.model.Wishlist;
import gr.aueb.cf4.orderapp.model.WishlistItem;
import gr.aueb.cf4.orderapp.repository.WishlistItemRepository;
import gr.aueb.cf4.orderapp.service.exceptions.WishlistItemNotFoundException;
import gr.aueb.cf4.orderapp.service.mappers.WishlistItemMapper;
import gr.aueb.cf4.orderapp.service.mappers.WishlistMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class WishlistItemServiceImpl implements IWishlistItemService {
    private final WishlistItemRepository wishlistItemRepository;
    private final WishlistItemMapper wishlistItemMapper;
    private final IWishlistService wishlistService;
    private final WishlistMapper wishlistMapper;

    @Autowired
    public WishlistItemServiceImpl(WishlistItemRepository wishlistItemRepository, WishlistItemMapper wishlistItemMapper, IWishlistService wishlistService, WishlistMapper wishlistMapper) {
        this.wishlistItemRepository = wishlistItemRepository;
        this.wishlistItemMapper = wishlistItemMapper;
        this.wishlistService = wishlistService;
        this.wishlistMapper = wishlistMapper;
    }

    @Override
    @Transactional
    public WishlistItemReadOnlyDTO createWishlistItem(WishlistItemCreateDTO wishlistItemCreateDTO) {
        // Retrieve the user's wishlist
        WishlistReadOnlyDTO userWishlist = wishlistService.getWishlistForCurrentUser();

        // Convert WishlistReadOnlyDTO to Wishlist entity
        Wishlist wishlistEntity = wishlistMapper.convertToWishlistEntity(userWishlist);

        // Set the converted Wishlist entity
        WishlistItem wishlistItem = new WishlistItem();
        wishlistItem.setQuantity(wishlistItemCreateDTO.getQuantity());
        wishlistItem.setSize(wishlistItemCreateDTO.getSize());
        wishlistItem.setWishlist(wishlistEntity);
        wishlistItem.setProduct(wishlistItemCreateDTO.getProduct());

        WishlistItem savedWishlistItem = wishlistItemRepository.save(wishlistItem);
        return wishlistItemMapper.convertToWishlistItemReadOnlyDTO(savedWishlistItem);
    }

    @Override
    @Transactional
    public WishlistItemReadOnlyDTO updateWishlistItem(Long wishlistItemId, WishlistItemUpdateDTO wishlistItemUpdateDTO) {
        Optional<WishlistItem> optionalWishlistItem = wishlistItemRepository.findById(wishlistItemId);
        if (optionalWishlistItem.isPresent()) {
            WishlistItem wishlistItem = optionalWishlistItem.get();
            wishlistItem.setQuantity(wishlistItemUpdateDTO.getQuantity());
            wishlistItem.setSize(wishlistItemUpdateDTO.getSize());

            WishlistItem updatedWishlistItem = wishlistItemRepository.save(wishlistItem);
            return wishlistItemMapper.convertToWishlistItemReadOnlyDTO(updatedWishlistItem);
        } else {
            throw new WishlistItemNotFoundException("Wishlist item not found with ID: " + wishlistItemId);
        }
    }

    @Override
    public WishlistItemReadOnlyDTO getWishlistItemById(Long wishlistItemId) {
        Optional<WishlistItem> optionalWishlistItem = wishlistItemRepository.findById(wishlistItemId);
        return optionalWishlistItem.map(wishlistItemMapper::convertToWishlistItemReadOnlyDTO).orElse(null);
    }

    @Override
    @Transactional
    public WishlistItemReadOnlyDTO deleteWishlistItem(Long wishlistItemId) {
        Optional<WishlistItem> optionalWishlistItem = wishlistItemRepository.findById(wishlistItemId);
        if (optionalWishlistItem.isPresent()) {
            WishlistItem wishlistItem = optionalWishlistItem.get();

            wishlistItemRepository.deleteById(wishlistItemId);

            WishlistItemReadOnlyDTO deletedItemDTO = wishlistItemMapper.convertToWishlistItemReadOnlyDTO(wishlistItem);

            return deletedItemDTO;
        } else {
            return null;
        }
    }


    @Override
    public List<WishlistItemReadOnlyDTO> getAllWishlistItemsByWishlistId(Long wishlistId) {
        List<WishlistItem> wishlistItems = wishlistItemRepository.findAllByWishlistId(wishlistId);
        return wishlistItems.stream()
                .map(wishlistItemMapper::convertToWishlistItemReadOnlyDTO)
                .collect(Collectors.toList());
    }
}
