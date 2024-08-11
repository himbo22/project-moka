package hoangvacban.demo.projectmoka.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("products/item")
    public String productDetail() {
        return "product";
    }

}
