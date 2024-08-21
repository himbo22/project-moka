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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
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
        if (user == null || image.isEmpty()) {
            throw new RuntimeException("Invalid input");
        }
        String imageUrl = imageStorageService.storeImage(image);
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        User createdUser = userMapper.toUser(user);
        createdUser.setAvatar(imageUrl);
        createdUser.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(createdUser);
    }
}
