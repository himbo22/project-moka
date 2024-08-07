package hoangvacban.demo.projectmoka.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private double oldPrice;
    private double newPrice;
    private Integer stock;
    private String description;
    private Integer rated;
    private String image;
    private Integer sold;
    private String location;

    public Product(String name, double oldPrice, double newPrice, Integer stock, String description, Integer rated, String image, Integer sold, String location) {
        this.name = name;
        this.oldPrice = oldPrice;
        this.newPrice = newPrice;
        this.stock = stock;
        this.description = description;
        this.rated = rated;
        this.image = image;
        this.sold = sold;
        this.location = location;
    }
}
