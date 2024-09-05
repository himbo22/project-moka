package hoangvacban.demo.projectmoka.service;

import hoangvacban.demo.projectmoka.entity.Follower;
import hoangvacban.demo.projectmoka.entity.User;
import hoangvacban.demo.projectmoka.exception.AppException;
import hoangvacban.demo.projectmoka.exception.ErrorCode;
import hoangvacban.demo.projectmoka.model.request.FollowRequest;
import hoangvacban.demo.projectmoka.model.response.ResponseObject;
import hoangvacban.demo.projectmoka.repository.FollowerRepository;
import hoangvacban.demo.projectmoka.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FollowerService {

    FollowerRepository followerRepository;
    UserRepository userRepository;

    public ResponseObject followUser(FollowRequest followRequest) {
        Optional<User> followed = userRepository.findById(followRequest.getFollowedUser());
        Optional<User> following = userRepository.findById(followRequest.getFollowingUser());
        if (followed.isEmpty() || following.isEmpty()) {
            throw new AppException(ErrorCode.NOT_FOUND);
        }
        Follower follower = new Follower(followed.get(), following.get());
        followerRepository.save(follower);
        return new ResponseObject(
                "ok",
                "ok",
                follower
        );
    }

}
