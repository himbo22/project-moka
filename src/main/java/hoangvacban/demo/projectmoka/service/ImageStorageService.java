package hoangvacban.demo.projectmoka.service;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.UUID;

import static hoangvacban.demo.projectmoka.util.Const.BASE_IMAGE_URL;

@Service
public class ImageStorageService {
    private final Path storageFolder = Paths.get("uploads");

    public ImageStorageService() {
        if (!storageFolder.toFile().exists()) {
            try {
                Files.createDirectory(storageFolder);
            } catch (IOException ioException) {
                throw new RuntimeException("Unable to create directory", ioException);
            }
        }
    }

    private boolean isImageFile(MultipartFile file) {
        String fileExtension = FilenameUtils.getExtension(file.getOriginalFilename());
        assert fileExtension != null;
        return Arrays.asList(new String[]{"jpg", "jpeg", "png", "gif"})
                .contains(fileExtension.trim().toLowerCase());
    }

    public String storeImage(MultipartFile file) {
        try {
            if (file.isEmpty()) {
                throw new RuntimeException("File cannot be empty");
            }
            if (!isImageFile(file)) {
                throw new RuntimeException("File is not an image");
            }
            float fileSize = file.getSize() / 1_000_000.0f;
            if (fileSize > 5.0f) {
                throw new RuntimeException("File is too large");
            }

            // rename file
            String fileExtension = FilenameUtils.getExtension(file.getOriginalFilename());
            String newFileName = UUID.randomUUID().toString().replace("-", "") + "." + fileExtension;
            Path destinationPath = this.storageFolder.resolve(Paths.get(newFileName))
                    .normalize().toAbsolutePath();
            if (!destinationPath.getParent().equals(this.storageFolder.toAbsolutePath())) {
                throw new RuntimeException("Destination path does not match storage folder");
            }
            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, destinationPath, StandardCopyOption.REPLACE_EXISTING);
            }
            return BASE_IMAGE_URL + newFileName;
        } catch (IOException ioException) {
            throw new RuntimeException("Unable to store image", ioException);
        }
    }

    public byte[] readImage(String fileName) {
        try {
            Path filePath = this.storageFolder.resolve(fileName);
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists() || resource.isReadable()) {
                return StreamUtils.copyToByteArray(resource.getInputStream());
            }
            throw new IOException("File not found");
        } catch (IOException ioException) {
            throw new RuntimeException("Unable to read image", ioException);
        }
    }

    public void cleanAllImages() {
        try {
            FileUtils.cleanDirectory(this.storageFolder.toFile());
        } catch (IOException e) {
            throw new RuntimeException("Cannot clean Directory", e);
        }
    }
}
