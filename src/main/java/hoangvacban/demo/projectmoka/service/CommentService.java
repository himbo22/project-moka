package hoangvacban.demo.projectmoka.service;


import hoangvacban.demo.projectmoka.entity.Comment;
import hoangvacban.demo.projectmoka.entity.Post;
import hoangvacban.demo.projectmoka.entity.User;
import hoangvacban.demo.projectmoka.exception.AppException;
import hoangvacban.demo.projectmoka.exception.ErrorCode;
import hoangvacban.demo.projectmoka.mapper.CommentMapper;
import hoangvacban.demo.projectmoka.model.request.CommentRequest;
import hoangvacban.demo.projectmoka.model.response.CommentResponse;
import hoangvacban.demo.projectmoka.model.response.ResponseObject;
import hoangvacban.demo.projectmoka.repository.CommentRepository;
import hoangvacban.demo.projectmoka.repository.PostRepository;
import hoangvacban.demo.projectmoka.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CommentService {

    CommentRepository commentRepository;
    PostRepository postRepository;
    UserRepository userRepository;
    CommentMapper commentMapper;


    public ResponseObject addComment(CommentRequest commentRequest) {
        // fetch Post with id
        Post post = postRepository.findById(commentRequest.getPost_id())
                .orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND));
        User author = userRepository.findById(commentRequest.getAuthor())
                .orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND));
        // create Comment`
        Comment newComment = new Comment();
        newComment.setContent(commentRequest.getContent());
        newComment.setCreatedAt(commentRequest.getCreatedAt());
        newComment.setPost(post);
        newComment.setAuthor(author);
        commentRepository.save(newComment);
        return new ResponseObject(
                "ok",
                "ok",
                newComment
        );
    }

    public ResponseObject getCommentById(long id) {
        Optional<Comment> comment = commentRepository.findById(id);
        if (comment.isEmpty()) {
            throw new AppException(ErrorCode.NOT_FOUND);
        }
        return new ResponseObject(
                "ok",
                "ok",
                comment.get()
        );
    }

    public Page<CommentResponse> getCommentsByPostId(Long id, Pageable pageable) {
        postRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND));
        return commentRepository.getCommentsByPostId(id, pageable);
    }

    public Page<Comment> getComments(Pageable pageable) {
        return commentRepository.findAll(pageable);
    }

}
