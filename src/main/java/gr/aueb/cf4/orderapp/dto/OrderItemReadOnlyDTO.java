package gr.aueb.cf4.orderapp.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import gr.aueb.cf4.orderapp.model.Order;

import gr.aueb.cf4.orderapp.model.Product;
import gr.aueb.cf4.orderapp.model.Size;
import lombok.Data;

@Data
@JsonIgnoreProperties("order")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderItemReadOnlyDTO extends BaseDTO {
    private int quantity;
    private Size size;
    private Product product;
    private Order order;

    public OrderItemReadOnlyDTO() {
    }

    public OrderItemReadOnlyDTO(Long id, int quantity, Size size, Product product, Order order) {
        this.setId(id);
        this.quantity = quantity;
        this.size = size;
        this.product = product;
        this.order = order;
    }

}
