package hoangvacban.demo.projectmoka.mapper;

import hoangvacban.demo.projectmoka.entity.Product;
import hoangvacban.demo.projectmoka.model.request.ProductRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    Product toProduct(ProductRequest product);
}
