package gr.aueb.cf4.orderapp.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import gr.aueb.cf4.orderapp.model.OrderItem;
import gr.aueb.cf4.orderapp.model.User;
import lombok.Data;

import java.util.Date;
import java.util.List;
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderReadOnlyDTO extends BaseDTO {
    private UserReadOnlyDTO user;
    private List<OrderItem> orderItems;
    private Date orderDate;

    public OrderReadOnlyDTO(Long id, User user, List<OrderItem> orderItems, Date orderDate) {
        this.setId(id);
        this.user = new UserReadOnlyDTO(user.getId(), user.getUsername());
        this.orderItems = orderItems;
        this.orderDate = orderDate;
    }
}
