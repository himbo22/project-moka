package hoangvacban.demo.projectmoka.service;

import hoangvacban.demo.projectmoka.entity.Product;
import hoangvacban.demo.projectmoka.model.request.ProductRequest;
import hoangvacban.demo.projectmoka.repository.ProductRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

import java.util.List;

import static hoangvacban.demo.projectmoka.util.Const.BASE_IMAGE_URL;

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
            String imageFileName = BASE_IMAGE_URL + imageStorageService.storeImage(image);
            Product addedProduct = getProduct(product, imageFileName);
            return productRepository.save(addedProduct);
        } catch (MissingServletRequestPartException missingServletRequestPartException) {
            throw new RuntimeException(missingServletRequestPartException.getMessage());
        }
    }

    private static @NotNull Product getProduct(ProductRequest product, String imageFileName) {
        Product addedProduct = new Product();
        addedProduct.setImage(imageFileName);
        addedProduct.setName(product.getName());
        addedProduct.setDescription(product.getDescription());
        addedProduct.setOldPrice(product.getOldPrice());
        addedProduct.setNewPrice(product.getNewPrice());
        addedProduct.setStock(product.getStock());
        addedProduct.setRated(product.getRated());
        addedProduct.setSold(product.getSold());
        addedProduct.setStock(product.getStock());
        addedProduct.setLocation(product.getLocation());
        return addedProduct;
    }

}
