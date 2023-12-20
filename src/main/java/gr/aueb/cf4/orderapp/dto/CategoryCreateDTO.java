package gr.aueb.cf4.orderapp.dto;

import lombok.Data;

import java.util.List;

@Data
public class CategoryCreateDTO {

        private String name;
        private String photoUrl;

        public CategoryCreateDTO(String name, String photoUrl) {
                this.name = name;
                this.photoUrl = photoUrl;
        }
}


