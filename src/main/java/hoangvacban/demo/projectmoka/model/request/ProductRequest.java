package hoangvacban.demo.projectmoka.model.request;

import lombok.Getter;

@Getter
public class ProductRequest {
    private String name;
    private double oldPrice;
    private double newPrice;
    private Integer stock;
    private String description;
    private Integer rated;
    private String image;
    private Integer sold;
    private String location;
}
