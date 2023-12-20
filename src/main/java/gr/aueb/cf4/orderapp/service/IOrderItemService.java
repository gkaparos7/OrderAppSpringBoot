package gr.aueb.cf4.orderapp.service;

import gr.aueb.cf4.orderapp.dto.OrderItemCreateDTO;
import gr.aueb.cf4.orderapp.dto.OrderItemReadOnlyDTO;
import gr.aueb.cf4.orderapp.dto.OrderItemUpdateDTO;
import gr.aueb.cf4.orderapp.model.Order;
import gr.aueb.cf4.orderapp.model.OrderItem;

import java.util.List;

public interface IOrderItemService {
    OrderItemReadOnlyDTO createOrderItem(OrderItemCreateDTO orderItemCreateDTO);

    OrderItemReadOnlyDTO updateOrderItem(Long orderItemId, OrderItemUpdateDTO orderItemUpdateDTO);

    OrderItemReadOnlyDTO getOrderItemById(Long orderItemId);

    OrderItemReadOnlyDTO deleteOrderItem(Long orderItemId);

    List<OrderItemReadOnlyDTO> getAllOrderItemsByOrderId(Long orderId);

    List<OrderItem> updateOrderItems(Order order, List<OrderItem> orderItemList);
}

