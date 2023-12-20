package gr.aueb.cf4.orderapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", length = 50, nullable = false, unique = true)
    private String username;

    @Column(name = "password", length = 255, nullable = false)
    private String password;

    @OneToMany(mappedBy = "user",  fetch = FetchType.LAZY)
    private List<Order> orders; // One user can have multiple orders

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Wishlist wishlist; // One user has one wishlist

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                '}';
    }

    // Convenient method to add an order to the user
    public void addOrder(Order order) {
        if (orders == null) {
            orders = new ArrayList<>();
        }
        orders.add(order);
        order.setUser(this);
    }

    // Convenient method to set the wishlist for the user
    public void setWishlist(Wishlist wishlist) {
        this.wishlist = wishlist;
        wishlist.setUser(this);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Implement as needed based on your application's roles and permissions
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    // Implement the following methods

    @Override
    public boolean isAccountNonExpired() {
        return true; // Change the logic as needed
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Change the logic as needed
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Change the logic as needed
    }

    @Override
    public boolean isEnabled() {
        return true; // Change the logic as needed
    }
}

