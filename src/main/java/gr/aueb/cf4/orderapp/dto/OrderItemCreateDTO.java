package gr.aueb.cf4.orderapp.dto;

import gr.aueb.cf4.orderapp.model.Order;
import gr.aueb.cf4.orderapp.model.Product;
import gr.aueb.cf4.orderapp.model.Size;
import lombok.Data;

@Data
public class OrderItemCreateDTO {
    private int quantity;
    private Size size;
    private Product product;


    public OrderItemCreateDTO(int quantity, Size size, Product product) {
        this.quantity = quantity;
        this.size = size;
        this.product = product;

    }
}

