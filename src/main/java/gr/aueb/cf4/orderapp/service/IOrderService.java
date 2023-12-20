package gr.aueb.cf4.orderapp.service;

import gr.aueb.cf4.orderapp.dto.OrderCreateDTO;
import gr.aueb.cf4.orderapp.dto.OrderReadOnlyDTO;
import gr.aueb.cf4.orderapp.dto.OrderUpdateDTO;
import gr.aueb.cf4.orderapp.dto.WishlistItemCreateDTO;
import gr.aueb.cf4.orderapp.model.User;

import java.util.List;

public interface IOrderService {
    OrderReadOnlyDTO createOrder(OrderCreateDTO orderCreateDTO);

    List<OrderReadOnlyDTO> getAllOrders();
    List<OrderReadOnlyDTO> getAllOrdersForLoggedInUser();

    OrderReadOnlyDTO getOrderById(Long orderId);

    OrderReadOnlyDTO deleteOrder(Long orderId);

    OrderReadOnlyDTO createOrderFromWishlist(List<WishlistItemCreateDTO> wishlistItems);

    OrderReadOnlyDTO updateOrder(Long orderId, OrderUpdateDTO orderUpdateDTO);
    OrderReadOnlyDTO getCurrentOrderForUser(User user);
    OrderReadOnlyDTO createOrderForUser(User user);
}

