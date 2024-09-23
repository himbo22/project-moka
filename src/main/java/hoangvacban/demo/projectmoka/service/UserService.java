package hoangvacban.demo.projectmoka.service;

import hoangvacban.demo.projectmoka.entity.User;
import hoangvacban.demo.projectmoka.exception.AppException;
import hoangvacban.demo.projectmoka.exception.ErrorCode;
import hoangvacban.demo.projectmoka.mapper.UserMapper;
import hoangvacban.demo.projectmoka.model.request.LoginRequest;
import hoangvacban.demo.projectmoka.model.request.ResetPasswordRequest;
import hoangvacban.demo.projectmoka.model.request.UserRequest;
import hoangvacban.demo.projectmoka.model.response.ResponseObject;
import hoangvacban.demo.projectmoka.model.response.UserResponse;
import hoangvacban.demo.projectmoka.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public UserResponse getUser(long id) {
        return userRepository.findUserById(id).orElseThrow(
                () -> new AppException(ErrorCode.NOT_FOUND)
        );
    }

    public Page<User> getUserByName(String username, Pageable pageable) {
        return userRepository.findByUsernameLike(username, pageable);
    }

    public User resetPassword(String email, ResetPasswordRequest resetPasswordRequest) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND));
        if (!resetPasswordRequest.getPassword().equals(resetPasswordRequest.getConfirmPassword())) {
            throw new AppException(ErrorCode.PASSWORD_NOT_EQUALS);
        }
        user.setPassword(new BCryptPasswordEncoder(10).encode(resetPasswordRequest.getPassword()));
        return userRepository.save(user);
    }

    public ResponseObject createUser(
            String username,
            String password,
            String email,
            String bio,
            MultipartFile image
    ) {
        boolean existedUser = userRepository.existsByUsername(username);
        if (existedUser) {
            throw new AppException(ErrorCode.USER_ALREADY_EXISTED);
        }
        if (username.contains(" ")) {
            throw new AppException(ErrorCode.USERNAME_MUST_NOT_CONTAINS_SPACE);
        }
        String imageUrl = imageStorageService.storeImage(image);
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        UserRequest user = new UserRequest(username, "", email, imageUrl, bio);
        User createdUser = userMapper.toUser(user);
        createdUser.setPassword(passwordEncoder.encode(password));
        userRepository.save(createdUser);
        return new ResponseObject(
                "ok",
                "ok",
                createdUser
        );
    }

    public User updateUser(String userId, String username, String bio, MultipartFile image) {
        User user = userRepository.findById(Long.valueOf(userId)).orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND));
        if (userRepository.existsByUsernameAndId(username, Long.valueOf(userId))) {
            throw new AppException(ErrorCode.USER_ALREADY_EXISTED);
        }
        if (username.contains(" ")) {
            throw new AppException(ErrorCode.USERNAME_MUST_NOT_CONTAINS_SPACE);
        }
        if (username.isBlank()) {
            throw new AppException(ErrorCode.USERNAME_MUST_NOT_BLANK);
        }
        if (image != null) {
            String imageUrl = imageStorageService.storeImage(image);
            user.setAvatar(imageUrl);
        } else {
            System.out.println("CAC");
        }
        user.setUsername(username);
        user.setBio(bio);

        return userRepository.save(user);
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
