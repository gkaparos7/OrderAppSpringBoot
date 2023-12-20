package gr.aueb.cf4.orderapp.service.mappers;

import gr.aueb.cf4.orderapp.dto.OrderItemCreateDTO;
import gr.aueb.cf4.orderapp.dto.OrderItemReadOnlyDTO;
import gr.aueb.cf4.orderapp.model.Order;
import gr.aueb.cf4.orderapp.model.OrderItem;
import gr.aueb.cf4.orderapp.model.Product;
import org.springframework.stereotype.Component;

@Component
public class OrderItemMapper {

    public OrderItem convertToOrderItemEntity(OrderItemCreateDTO orderItemCreateDTO, Order order) {
        OrderItem orderItem = new OrderItem();
        orderItem.setQuantity(orderItemCreateDTO.getQuantity());
        orderItem.setSize(orderItemCreateDTO.getSize());

        Product product = orderItemCreateDTO.getProduct();
        orderItem.setProduct(product);

        // Set the order for the order item
        orderItem.setOrder(order);

        return orderItem;
    }


    public OrderItemReadOnlyDTO convertToOrderItemReadOnlyDTO(OrderItem orderItem) {
        return new OrderItemReadOnlyDTO(
                orderItem.getId(),
                orderItem.getQuantity(),
                orderItem.getSize(),
                orderItem.getProduct(),
                orderItem.getOrder()
        );
    }
}

