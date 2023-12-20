package gr.aueb.cf4.orderapp.dto;

import gr.aueb.cf4.orderapp.model.OrderItem;
import gr.aueb.cf4.orderapp.model.User;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class OrderCreateDTO {

    private User user;
    private List<OrderItem> orderItems;

    public OrderCreateDTO() {
    }

    public OrderCreateDTO(User user, List<OrderItem> orderItems) {
        this.user = user;
        this.orderItems = orderItems;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }
}
