package hoangvacban.demo.projectmoka.model.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class ProductDTO {
    private String name;
    private double oldPrice;
    private double newPrice;
    private Integer stock;
    private String description;
    private Integer rated;
    private MultipartFile image;
    private Integer sold;
    private String location;
}
