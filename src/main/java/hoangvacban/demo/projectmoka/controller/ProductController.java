package hoangvacban.demo.projectmoka.controller;

import hoangvacban.demo.projectmoka.entity.Product;
import hoangvacban.demo.projectmoka.model.request.ProductRequest;
import hoangvacban.demo.projectmoka.model.response.ResponseObject;
import hoangvacban.demo.projectmoka.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseObject> getProductById(@PathVariable long id) {
        Product product = productService.getProductById(id);
        return ResponseEntity.ok().body(new ResponseObject("ok", "add product successfully", product));
    }

    @PostMapping(value = "/add")
    public ResponseEntity<ResponseObject> addProduct(
            @RequestPart("product") ProductRequest product,
            @RequestPart("image") MultipartFile image
    ) {
        Product addedProduct = productService.addProduct(product, image);
        return ResponseEntity.ok().body(
                new ResponseObject("ok", "add product successfully", addedProduct)
        );
    }

}
