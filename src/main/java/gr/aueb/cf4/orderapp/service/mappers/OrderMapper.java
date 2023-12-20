package gr.aueb.cf4.orderapp.service.mappers;

import gr.aueb.cf4.orderapp.dto.*;
import gr.aueb.cf4.orderapp.model.Order;
import org.springframework.stereotype.Component;


@Component
public class OrderMapper {
    public static OrderReadOnlyDTO convertToOrderReadOnlyDTO(Order order) {
        return new OrderReadOnlyDTO(
                order.getId(),
                order.getUser(),
                order.getOrderItems(),
                order.getOrderDate()
        );
    }
}