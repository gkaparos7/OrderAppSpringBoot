package gr.aueb.cf4.orderapp.dto;


import gr.aueb.cf4.orderapp.model.Size;
import lombok.Data;

@Data
public class OrderItemUpdateDTO extends BaseDTO {
    private int quantity;
    private Size size;

    public OrderItemUpdateDTO(Long id, int quantity, Size size) {
        this.setId(id);
        this.quantity = quantity;
        this.size = size;
    }
}
