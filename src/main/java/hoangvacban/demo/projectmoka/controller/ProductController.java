package hoangvacban.demo.projectmoka.controller;

import hoangvacban.demo.projectmoka.entity.Product;
import hoangvacban.demo.projectmoka.model.response.ResponseObject;
import hoangvacban.demo.projectmoka.service.ImageStorageService;
import hoangvacban.demo.projectmoka.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ImageStorageService imageStorageService;


    @GetMapping("")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseObject> getProductById(@PathVariable long id) {
        try {
            Product product = productService.getProductById(id);
            return ResponseEntity.ok().body(new ResponseObject("ok", "add product successfully", product));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/images/{fileName:.+}")
    public ResponseEntity<byte[]> getProductImage(@PathVariable String fileName) {
        try {
            byte[] bytes = imageStorageService.readImage(fileName);
            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(bytes);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping(value = "/add")
    public ResponseEntity<ResponseObject> addProduct(
            @RequestPart Product product,
            @RequestPart(value = "image") MultipartFile file
    ) {
        try {
            Product insertedProduct = productService.createProduct(product, file);
            return ResponseEntity.ok().body(
                    new ResponseObject("ok", "add product successfully", insertedProduct)
            );
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

}
