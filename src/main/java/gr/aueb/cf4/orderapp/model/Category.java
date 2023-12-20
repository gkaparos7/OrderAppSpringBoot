package gr.aueb.cf4.orderapp.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categories")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "category_name", length = 50, nullable = false, unique = true)
    private String name;

    @Column(name = "photo_url", length = 500)
    private String photoUrl;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Subcategory> subcategories;

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\''+
                '}';
    }
}