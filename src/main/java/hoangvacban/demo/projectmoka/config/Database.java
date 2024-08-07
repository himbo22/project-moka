package hoangvacban.demo.projectmoka.config;

import hoangvacban.demo.projectmoka.entity.Product;
import hoangvacban.demo.projectmoka.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Database {

    @Bean
    CommandLineRunner initDatabase(ProductRepository productRepository) {
        return _ -> {
            Product productA = new Product("cai gi do", 123.0f, 100.0f, 2, "no description", 3, "http://localhost:8080/api/products/images/a0ed418d66284d119b7c554844a2aa34.jpg", 1, "o cho");
            Product productB = new Product("cai gi do 1", 123.0f, 100.0f, 2, "no description", 3, "http://localhost:8080/api/products/images/a0ed418d66284d119b7c554844a2aa34.jpg", 1, "o cho");
            productRepository.save(productA);
            productRepository.save(productB);
        };
    }
}
