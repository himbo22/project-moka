package hoangvacban.demo.projectmoka.service;

import hoangvacban.demo.projectmoka.entity.Post;
import hoangvacban.demo.projectmoka.exception.AppException;
import hoangvacban.demo.projectmoka.exception.ErrorCode;
import hoangvacban.demo.projectmoka.mapper.PostMapper;
import hoangvacban.demo.projectmoka.model.request.PostRequest;
import hoangvacban.demo.projectmoka.model.response.ResponseObject;
import hoangvacban.demo.projectmoka.repository.PostRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PostService {

    PostRepository postRepository;
    PostMapper postMapper;

    public ResponseObject createPost(PostRequest post) {
        Post newPost = postMapper.toPost(post);
        postRepository.save(newPost);
        return new ResponseObject(
                "ok",
                "ok",
                newPost
        );
    }

    public ResponseObject updatePost(long id, PostRequest postRequest) {
        Optional<Post> post = postRepository.findById(id);
        if (post.isEmpty()) {
            throw new AppException(ErrorCode.NOT_FOUND);
        }
        post.get().setAuthor(postRequest.getAuthor());
        post.get().setContent(postRequest.getContent());
        post.get().setCreatedAt(postRequest.getCreateAt());
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

    public ResponseObject getAllPosts(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return new ResponseObject(
                "ok",
                "ok",
                postRepository.findAll(pageRequest)
        );
    }
}
