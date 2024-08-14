package hoangvacban.demo.projectmoka.controller;

import hoangvacban.demo.projectmoka.service.ImageStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/images")
public class ImageController {

    @Autowired
    private ImageStorageService imageStorageService;

    @GetMapping("/{fileName:.+}")
    public ResponseEntity<byte[]> loadImage(@PathVariable String fileName) {
        byte[] bytes = imageStorageService.readImage(fileName);
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(bytes);
    }
}
