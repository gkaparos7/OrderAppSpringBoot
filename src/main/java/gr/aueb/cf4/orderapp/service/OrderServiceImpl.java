package gr.aueb.cf4.orderapp.service;

import gr.aueb.cf4.orderapp.dto.*;
import gr.aueb.cf4.orderapp.model.Order;
import gr.aueb.cf4.orderapp.model.OrderItem;
import gr.aueb.cf4.orderapp.model.User;
import gr.aueb.cf4.orderapp.repository.OrderRepository;
import gr.aueb.cf4.orderapp.service.exceptions.NoCurrentOrderException;
import gr.aueb.cf4.orderapp.service.exceptions.OrderNotFoundException;
import gr.aueb.cf4.orderapp.service.exceptions.UserNotFoundException;
import gr.aueb.cf4.orderapp.service.mappers.OrderItemMapper;
import gr.aueb.cf4.orderapp.service.mappers.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements IOrderService {

    private final OrderItemServiceImpl orderItemService;
    private final OrderMapper orderMapper;
    private final IUserService userService;
    private final OrderRepository orderRepository;
    private final OrderItemMapper orderItemMapper;

    @Autowired
    public OrderServiceImpl(OrderItemServiceImpl orderItemService, OrderMapper orderMapper, IUserService userService, OrderRepository orderRepository, OrderItemMapper orderItemMapper) {
        this.orderItemService = orderItemService;
        this.orderMapper = orderMapper;
        this.userService = userService;
        this.orderRepository = orderRepository;
        this.orderItemMapper = orderItemMapper;
    }

    @Override
    @Transactional
    public OrderReadOnlyDTO createOrder(OrderCreateDTO orderCreateDTO) {
        // Get the current logged-in user
        User currentUser = orderCreateDTO.getUser();
        if (currentUser != null) {
            // Create a new order
            Order newOrder = new Order();
            newOrder.setUser(currentUser);
            newOrder.setOrderDate(new Date()); // Set the order date to the current date
            newOrder.setOrderItems(new ArrayList<>()); // Initialize an empty list of order items

            // Map OrderCreateDTO to Order and add it to the user's orders
            currentUser.addOrder(newOrder);

            // Map OrderItems from OrderCreateDTO to OrderItem entities
            List<OrderItem> orderItems = new ArrayList<>();
            for (OrderItem item : orderCreateDTO.getOrderItems()) {
                OrderItem orderItem = new OrderItem();
                orderItem.setQuantity(item.getQuantity());
                orderItem.setSize(item.getSize());
                // Set the product and order for the OrderItem (assuming these are available in OrderItemCreateDTO)
                orderItem.setProduct(item.getProduct());
                orderItem.setOrder(newOrder);
                orderItems.add(orderItem);
            }

            // Set the order items for the new order
            newOrder.setOrderItems(orderItems);

            // Save the new order
            Order savedOrder = orderRepository.save(newOrder);

            // Convert and return the created order
            return orderMapper.convertToOrderReadOnlyDTO(savedOrder);
        } else {
            throw new UserNotFoundException("User not found");
        }
    }

    @Override
    public List<OrderReadOnlyDTO> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream()
                .map(order -> orderMapper.convertToOrderReadOnlyDTO(order))
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderReadOnlyDTO> getAllOrdersForLoggedInUser() {
        User loggedInUser = userService.getCurrentUser();

        List<Order> userOrders = orderRepository.findAllByUser(loggedInUser);
        return userOrders.stream()
                .map(order -> orderMapper.convertToOrderReadOnlyDTO(order))
                .collect(Collectors.toList());
    }

    @Override
    public OrderReadOnlyDTO getOrderById(Long orderId) {
        Optional<Order> optionalOrder = orderRepository.findById(orderId);

        if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get();
            return orderMapper.convertToOrderReadOnlyDTO(order);
        } else {
            throw new OrderNotFoundException("Order not found with ID: " + orderId);
        }
    }

    @Override
    @Transactional
    public OrderReadOnlyDTO deleteOrder(Long orderId) {
        Optional<Order> optionalOrder = orderRepository.findById(orderId);

        if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get();
            orderRepository.delete(order);
            return orderMapper.convertToOrderReadOnlyDTO(order);
        } else {
            throw new OrderNotFoundException("Order not found with ID: " + orderId);
        }
    }


    @Override
    @Transactional
    public OrderReadOnlyDTO createOrderFromWishlist(List<WishlistItemCreateDTO> wishlistItems) {
        // Step 1: Retrieve the current user
        User currentUser = userService.getCurrentUser();

        // Step 2: Transform the List<WishlistItemCreateDTO> into a List<OrderItemCreateDTO>
        List<OrderItemCreateDTO> orderItemDTOs = new ArrayList<>();
        for (WishlistItemCreateDTO wishlistItem : wishlistItems) {
            OrderItemCreateDTO orderItemDTO = convertToOrderItemCreateDTO(wishlistItem);
            orderItemDTOs.add(orderItemDTO);
        }

        // Step 3: Create a new order and set the user
        Order newOrder = new Order();
        newOrder.setUser(currentUser);
        newOrder.setOrderDate(new Date()); // Set the order date to the current date

        // Step 4: Convert OrderItemCreateDTO objects to OrderItem entities
        List<OrderItem> orderItems = orderItemDTOs.stream()
                .map(orderItemDTO -> orderItemMapper.convertToOrderItemEntity(orderItemDTO, newOrder))
                .collect(Collectors.toList());

        // Step 5: Set the order items for the new order
        newOrder.setOrderItems(orderItems);

        // Step 6: Save the new order
        Order savedOrder = orderRepository.save(newOrder);

        // Step 7: Convert and return the created order
        return orderMapper.convertToOrderReadOnlyDTO(savedOrder);
    }

    // Method to convert WishlistItemCreateDTO to OrderItemCreateDTO
    private OrderItemCreateDTO convertToOrderItemCreateDTO(WishlistItemCreateDTO wishlistItem) {
        // Create and return OrderItemCreateDTO
        return new OrderItemCreateDTO(wishlistItem.getQuantity(), wishlistItem.getSize(), wishlistItem.getProduct());
    }

    @Override
    @Transactional
    public OrderReadOnlyDTO updateOrder(Long orderId, OrderUpdateDTO orderUpdateDTO) {
        Optional<Order> optionalOrder = orderRepository.findById(orderId);

        if (optionalOrder.isPresent()) {
            Order existingOrder = optionalOrder.get();

            // Update orderItems if provided in the OrderUpdateDTO
            if (orderUpdateDTO.getOrderItems() != null) {
                List<OrderItem> updatedOrderItems = orderItemService.updateOrderItems(existingOrder, orderUpdateDTO.getOrderItems());
                existingOrder.setOrderItems(updatedOrderItems);
            }
            // Save the updated order
            Order updatedOrder = orderRepository.save(existingOrder);

            // Convert and return the updated order
            return orderMapper.convertToOrderReadOnlyDTO(updatedOrder);
        } else {
            // Handle the case when the order is not found
            throw new OrderNotFoundException("Order not found with ID: " + orderId);
        }
    }

    @Override
    public OrderReadOnlyDTO getCurrentOrderForUser(User user) {
        Optional<Order> optionalCurrentOrder = orderRepository.findFirstByUserOrderByIdDesc(user);

        // Check if the optional has a value, and then get the Order
        Order currentOrder = optionalCurrentOrder.orElse(null);

        if (currentOrder != null) {
            // Convert to OrderReadOnlyDTO using your OrderMapper
            OrderReadOnlyDTO orderReadOnlyDTO = OrderMapper.convertToOrderReadOnlyDTO(currentOrder);
            return orderReadOnlyDTO;
        } else {
            Optional<OrderReadOnlyDTO> optionalOrder = optionalCurrentOrder.map(OrderMapper::convertToOrderReadOnlyDTO);
            return optionalOrder.orElseThrow(() -> new NoCurrentOrderException("No current order found for the user"));
        }
    }

    @Override
    @Transactional
    public OrderReadOnlyDTO createOrderForUser(User user) {
        // Create an OrderCreateDTO
        OrderCreateDTO orderCreateDTO = new OrderCreateDTO();
        orderCreateDTO.setUser(user);
        orderCreateDTO.setOrderItems(new ArrayList<>()); // Empty list for now

        // Call the existing createOrder method
        return createOrder(orderCreateDTO);
    }

}
