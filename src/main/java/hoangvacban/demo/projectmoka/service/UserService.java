package hoangvacban.demo.projectmoka.service;

import hoangvacban.demo.projectmoka.entity.User;
import hoangvacban.demo.projectmoka.exception.AppException;
import hoangvacban.demo.projectmoka.exception.ErrorCode;
import hoangvacban.demo.projectmoka.mapper.UserMapper;
import hoangvacban.demo.projectmoka.model.request.LoginRequest;
import hoangvacban.demo.projectmoka.model.request.UserRequest;
import hoangvacban.demo.projectmoka.model.response.ResponseObject;
import hoangvacban.demo.projectmoka.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {

    UserRepository userRepository;
    ImageStorageService imageStorageService;
    UserMapper userMapper;

    public User getUser(long id) {
        return userRepository.findById(id).orElseThrow(
                () -> new AppException(ErrorCode.NOT_FOUND)
        );
    }

    public ResponseObject createUser(UserRequest user, MultipartFile image) {
        Optional<User> checkUserExisted = userRepository.findByUsername(user.getUsername());
        if (checkUserExisted.isPresent()) {
            throw new AppException(ErrorCode.USER_ALREADY_EXISTED);
        }
        String imageUrl = imageStorageService.storeImage(image);
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        User createdUser = userMapper.toUser(user);
        createdUser.setAvatar(imageUrl);
        createdUser.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(createdUser);
        return new ResponseObject(
                "ok",
                "ok",
                createdUser
        );
    }

    public ResponseObject loginUser(LoginRequest user) {
        var checkUserExisted = userRepository.findByUsername(user.getUsername()).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        boolean authenticated = passwordEncoder.matches(user.getPassword(), checkUserExisted.getPassword());

        if (!authenticated) {
            throw new AppException(ErrorCode.UNAUTHENTICATED);
        }

        return new ResponseObject(
                "ok",
                "ok",
                checkUserExisted
        );
    }

}
