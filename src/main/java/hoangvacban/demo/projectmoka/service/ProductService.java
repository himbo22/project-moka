package hoangvacban.demo.projectmoka.service;

import hoangvacban.demo.projectmoka.entity.Product;
import hoangvacban.demo.projectmoka.mapper.ProductMapper;
import hoangvacban.demo.projectmoka.model.request.ProductRequest;
import hoangvacban.demo.projectmoka.repository.ProductRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductService {

    ProductRepository productRepository;
    ImageStorageService imageStorageService;
    ProductMapper productMapper;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElseThrow(
                () -> new RuntimeException("product id not found")
        );
    }

    public Product addProduct(ProductRequest product, MultipartFile image) {
        try {
            if (image.isEmpty()) {
                throw new MissingServletRequestPartException("image is empty");
            }
            if (product == null) {
                throw new MissingServletRequestPartException("product is empty");
            }
            String imageFileName = imageStorageService.storeImage(image);
            Product addedProduct = productMapper.toProduct(product);
            addedProduct.setImage(imageFileName);
            return productRepository.save(addedProduct);
        } catch (MissingServletRequestPartException missingServletRequestPartException) {
            throw new RuntimeException(missingServletRequestPartException.getMessage());
        }
    }

}
