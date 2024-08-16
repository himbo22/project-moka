package hoangvacban.demo.projectmoka.service;

import hoangvacban.demo.projectmoka.entity.User;
import hoangvacban.demo.projectmoka.exception.AppException;
import hoangvacban.demo.projectmoka.exception.ErrorCode;
import hoangvacban.demo.projectmoka.mapper.UserMapper;
import hoangvacban.demo.projectmoka.model.request.UserRequest;
import hoangvacban.demo.projectmoka.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {

    UserRepository userRepository;
    ImageStorageService imageStorageService;
    UserMapper userMapper;

    public User getUser(String id) {
        return userRepository.findById(id).orElseThrow(
                () -> new AppException(ErrorCode.NOT_FOUND)
        );
    }

    public User createUser(UserRequest user, MultipartFile image) {
        try {
            if (user == null || image.isEmpty() || image.getSize() == 0) {
                throw new RuntimeException("Invalid image file");
            }
            String imageUrl = imageStorageService.storeImage(image);
            User createdUser = userMapper.toUser(user);
            createdUser.setAvatar(imageUrl);
            return userRepository.save(createdUser);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
