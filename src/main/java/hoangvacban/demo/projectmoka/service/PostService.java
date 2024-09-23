package hoangvacban.demo.projectmoka.service;

import hoangvacban.demo.projectmoka.entity.Post;
import hoangvacban.demo.projectmoka.entity.User;
import hoangvacban.demo.projectmoka.exception.AppException;
import hoangvacban.demo.projectmoka.exception.ErrorCode;
import hoangvacban.demo.projectmoka.model.response.ResponseObject;
import hoangvacban.demo.projectmoka.model.response.UserPosts;
import hoangvacban.demo.projectmoka.repository.PostRepository;
import hoangvacban.demo.projectmoka.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PostService {

    PostRepository postRepository;
    ImageStorageService imageStorageService;
    UserRepository userRepository;


    public ResponseObject createPost(String userId, MultipartFile imageFile, String caption, String createdAt) {
        User user = userRepository.findById(Long.valueOf(userId)).orElseThrow(
                () -> new AppException(ErrorCode.NOT_FOUND)
        );
        String imageUrl = imageStorageService.storeImage(imageFile);
        Post newPost = new Post();
        newPost.setContent(imageUrl);
        newPost.setAuthor(user);
        newPost.setCaption(caption);
        newPost.setCreatedAt(createdAt);
        postRepository.save(newPost);
        return new ResponseObject(
                "ok",
                "ok",
                newPost
        );
    }

    public void deletePost(String postId) {
        postRepository.deleteById(Long.valueOf(postId));
    }

    public Page<UserPosts> findAllByUserId(Long userId, Pageable pageable) {
        userRepository.findById(userId).orElseThrow(
                () -> new AppException(ErrorCode.NOT_FOUND)
        );
        return postRepository.findAllByAuthorId(userId, pageable);
    }

    public ResponseObject updatePost(long id) {
        Optional<Post> post = postRepository.findById(id);
        if (post.isEmpty()) {
            throw new AppException(ErrorCode.NOT_FOUND);
        }

        postRepository.save(post.get());
        return new ResponseObject(
                "ok",
                "ok",
                post.get()
        );
    }

    public ResponseObject getPostById(long id) {
        Optional<Post> post = postRepository.findById(id);
        if (post.isEmpty()) {
            throw new AppException(ErrorCode.NOT_FOUND);
        }
        return new ResponseObject(
                "ok",
                "ok",
                post.get()
        );
    }

    public Page<UserPosts> getAllPosts(Pageable pageable) {
        return postRepository.findAllPosts(pageable);
    }

}
