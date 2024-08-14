package hoangvacban.demo.projectmoka.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
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
    @NotNull
    private String name;
    @NotNull
    private double oldPrice;
    @NotNull
    private double newPrice;
    @NotNull
    private Integer stock;
    @NotNull
    private String description;
    @NotNull
    private Integer rated;
    @NotNull
    private String image;
    @NotNull
    private Integer sold;
    @NotNull
    private String location;
}
