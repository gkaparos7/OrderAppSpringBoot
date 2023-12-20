package gr.aueb.cf4.orderapp.repository;

import gr.aueb.cf4.orderapp.model.Order;
import gr.aueb.cf4.orderapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByUser(User user);

    Optional<Order> findFirstByUserOrderByIdDesc(User user);
}