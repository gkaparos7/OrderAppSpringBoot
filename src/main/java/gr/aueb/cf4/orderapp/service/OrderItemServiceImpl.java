package gr.aueb.cf4.orderapp.service;

import gr.aueb.cf4.orderapp.dto.*;
import gr.aueb.cf4.orderapp.model.Order;
import gr.aueb.cf4.orderapp.model.OrderItem;

import gr.aueb.cf4.orderapp.model.User;
import gr.aueb.cf4.orderapp.repository.OrderItemRepository;
import gr.aueb.cf4.orderapp.service.exceptions.OrderItemNotFoundException;

import gr.aueb.cf4.orderapp.service.mappers.OrderItemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderItemServiceImpl implements IOrderItemService {

    private final OrderItemRepository orderItemRepository;
    private final OrderItemMapper orderItemMapper;
    private final IUserService userService;
    private final IOrderService orderService;

    @Autowired
    public OrderItemServiceImpl(OrderItemRepository orderItemRepository, OrderItemMapper orderItemMapper, IUserService userService,@Lazy IOrderService orderService) {
        this.orderItemRepository = orderItemRepository;
        this.orderItemMapper = orderItemMapper;
        this.userService = userService;
        this.orderService = orderService;
    }

    @Override
    @Transactional
    public OrderItemReadOnlyDTO createOrderItem(OrderItemCreateDTO orderItemCreateDTO) {
        // Get the current user
        User currentUser = userService.getCurrentUser();

        // Retrieve the user's order
        OrderReadOnlyDTO userOrder = orderService.getCurrentOrderForUser(currentUser);

        if (userOrder == null) {
            // Create a new order for the user if needed
            userOrder = orderService.createOrderForUser(currentUser);
        }

        // Now, proceed to create the OrderItem
        OrderItem orderItem = new OrderItem();
        orderItem.setQuantity(orderItemCreateDTO.getQuantity());
        orderItem.setSize(orderItemCreateDTO.getSize());
        // Set the product directly from the DTO
        orderItem.setProduct(orderItemCreateDTO.getProduct());

        OrderItem savedOrderItem = orderItemRepository.save(orderItem);
        return orderItemMapper.convertToOrderItemReadOnlyDTO(savedOrderItem);
    }



    @Override
    @Transactional
    public OrderItemReadOnlyDTO updateOrderItem(Long orderItemId, OrderItemUpdateDTO orderItemUpdateDTO) {
        Optional<OrderItem> optionalOrderItem = orderItemRepository.findById(orderItemId);
        if (optionalOrderItem.isPresent()) {
            OrderItem orderItem = optionalOrderItem.get();
            orderItem.setQuantity(orderItemUpdateDTO.getQuantity());
            orderItem.setSize(orderItemUpdateDTO.getSize());

            OrderItem updatedOrderItem = orderItemRepository.save(orderItem);
            return orderItemMapper.convertToOrderItemReadOnlyDTO(updatedOrderItem);
        } else {
            throw new OrderItemNotFoundException("Order item not found with ID: " + orderItemId);
        }
    }

    @Override
    public OrderItemReadOnlyDTO getOrderItemById(Long orderItemId) {
        Optional<OrderItem> optionalOrderItem = orderItemRepository.findById(orderItemId);
        return optionalOrderItem.map(orderItemMapper::convertToOrderItemReadOnlyDTO).orElse(null);
    }

    @Override
    @Transactional
    public OrderItemReadOnlyDTO deleteOrderItem(Long orderItemId) {
        Optional<OrderItem> optionalOrderItem = orderItemRepository.findById(orderItemId);
        if (optionalOrderItem.isPresent()) {
            OrderItem orderItem = optionalOrderItem.get();
            orderItemRepository.delete(orderItem);
            return orderItemMapper.convertToOrderItemReadOnlyDTO(orderItem);
        } else {
            return null;
        }
    }

    @Override
    public List<OrderItemReadOnlyDTO> getAllOrderItemsByOrderId(Long orderId) {
        List<OrderItem> orderItems = orderItemRepository.findAllByOrderId(orderId);
        return orderItems.stream()
                .map(orderItemMapper::convertToOrderItemReadOnlyDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<OrderItem> updateOrderItems(Order order, List<OrderItem> orderItemList) {
        List<OrderItem> existingOrderItems = orderItemRepository.findAllByOrderId(order.getId());

        for (OrderItem updatedOrderItem : orderItemList) {
            Long orderItemId = updatedOrderItem.getId();

            // Find the corresponding existing order item
            Optional<OrderItem> optionalOrderItem = existingOrderItems.stream()
                    .filter(orderItem -> orderItem.getId().equals(orderItemId))
                    .findFirst();

            if (optionalOrderItem.isPresent()) {
                OrderItem existingOrderItem = optionalOrderItem.get();

                // Update order item properties based on the provided OrderItem
                existingOrderItem.setQuantity(updatedOrderItem.getQuantity());
                existingOrderItem.setSize(updatedOrderItem.getSize());

                // Save the updated order item
                orderItemRepository.save(existingOrderItem);
            } else {
                throw new OrderItemNotFoundException("Order item not found with ID: " + orderItemId);
            }
        }

        // Return the list of updated order items
        return orderItemRepository.findAllByOrderId(order.getId());
    }
}
