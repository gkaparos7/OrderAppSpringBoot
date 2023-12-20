package gr.aueb.cf4.orderapp.repository;


import gr.aueb.cf4.orderapp.model.WishlistItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WishlistItemRepository extends JpaRepository<WishlistItem, Long> {
    List<WishlistItem> findAllByWishlistId(Long wishlistId);

    @Modifying
    @Query("DELETE FROM WishlistItem w WHERE w.id = :wishlistItemId")
    void deleteById(@Param("wishlistItemId") Long wishlistItemId);
}
