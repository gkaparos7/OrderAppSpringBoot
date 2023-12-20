package gr.aueb.cf4.orderapp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_name", length = 50, nullable = false)
    private String name;

    @Column(name = "photo_url", length = 200)
    private String photoUrl;

    @ElementCollection(targetClass = Size.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "product_sizes", joinColumns = @JoinColumn(name = "product_id"))
    @Column(name = "size")
    private List<Size> sizes;

    @ManyToOne
    @JoinColumn(name = "subcategory_id", nullable = false)
    @JsonBackReference
    private Subcategory subcategory;

}
