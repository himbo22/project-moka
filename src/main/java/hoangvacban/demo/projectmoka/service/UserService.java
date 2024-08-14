package hoangvacban.demo.projectmoka.service;

import hoangvacban.demo.projectmoka.entity.User;
import hoangvacban.demo.projectmoka.model.request.UserRequest;
import hoangvacban.demo.projectmoka.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ImageStorageService imageStorageService;

    public User getUser(String id) {
        return userRepository.findById(id).orElseThrow(
                () -> new RuntimeException("User ID not found")
        );
    }

    public User createUser(UserRequest user, MultipartFile image) {
        try {
            if (user == null || image.isEmpty() || image.getSize() == 0) {
                throw new RuntimeException("Invalid image file");
            }
            String imageUrl = imageStorageService.storeImage(image);
            User createdUser = new User();
            createdUser.setUsername(user.getUsername());
            createdUser.setPassword(user.getPassword());
            createdUser.setEmail(user.getEmail());
            createdUser.setAvatar(imageUrl);
            return userRepository.save(createdUser);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
