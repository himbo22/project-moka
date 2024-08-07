package hoangvacban.demo.projectmoka.service;

import hoangvacban.demo.projectmoka.entity.Product;
import hoangvacban.demo.projectmoka.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ImageStorageService imageStorageService;


    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        try {
            Optional<Product> foundProduct = productRepository.findById(id);
            if (foundProduct.isEmpty()) {
                throw new RuntimeException("Product is empty");
            }
            return foundProduct.get();
        } catch (Exception e) {
            throw new RuntimeException("Cannot find product");
        }
    }

    public Product createProduct(Product product, MultipartFile file) {
        try {
            String fileName = "http://localhost:8080/api/products/images/" + imageStorageService.storeImage(file);
            product.setImage(fileName);
            return productRepository.save(product);
        } catch (Exception e) {
            throw new RuntimeException("Cannot store image", e);
        }
    }

}
