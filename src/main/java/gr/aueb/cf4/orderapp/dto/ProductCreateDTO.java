package gr.aueb.cf4.orderapp.dto;

import gr.aueb.cf4.orderapp.model.Product;
import gr.aueb.cf4.orderapp.model.Size;
import gr.aueb.cf4.orderapp.model.Subcategory;
import lombok.Data;

import java.util.List;

@Data
public class ProductCreateDTO {
        private String name;
        private String photoUrl;
        private List<Size> sizes;
        private Long subcategoryId;


        public ProductCreateDTO(String name, String photoUrl, List<Size> sizes, Long subcategoryId) {
                this.name = name;
                this.photoUrl = photoUrl;
                this.sizes = sizes;
                this.subcategoryId = subcategoryId;
        }
}

