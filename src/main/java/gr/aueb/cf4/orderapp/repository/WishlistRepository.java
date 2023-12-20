package gr.aueb.cf4.orderapp.repository;

import gr.aueb.cf4.orderapp.model.User;
import gr.aueb.cf4.orderapp.model.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WishlistRepository extends JpaRepository<Wishlist, Long> {
    Wishlist findByUser(User user);
}
