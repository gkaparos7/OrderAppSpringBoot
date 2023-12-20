package gr.aueb.cf4.orderapp.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.List;

@Entity
@Table(name = "subcategories")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Subcategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "subcategory_name", length = 50, nullable = false)
    private String name;

    @Column(name = "photo_url", length = 500, nullable = false)
    private String photoUrl;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @OneToMany(mappedBy = "subcategory", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Product> products;

    public Category getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return "Subcategory{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public List<Product> getProducts() {
        return this.products;
    }

    public Subcategory(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
