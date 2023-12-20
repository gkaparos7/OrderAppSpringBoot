package gr.aueb.cf4.orderapp.dto;

import gr.aueb.cf4.orderapp.model.OrderItem;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper=false)
public class OrderUpdateDTO extends BaseDTO {
    private List<OrderItem> orderItems; // Updated list of items in the order

    public OrderUpdateDTO(Long id, List<OrderItem> orderItems) {
        this.setId(id);
        this.orderItems = orderItems;
    }
}
