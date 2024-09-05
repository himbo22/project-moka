package hoangvacban.demo.projectmoka.service;


import hoangvacban.demo.projectmoka.entity.Comment;
import hoangvacban.demo.projectmoka.entity.Post;
import hoangvacban.demo.projectmoka.exception.AppException;
import hoangvacban.demo.projectmoka.exception.ErrorCode;
import hoangvacban.demo.projectmoka.mapper.CommentMapper;
import hoangvacban.demo.projectmoka.model.request.CommentRequest;
import hoangvacban.demo.projectmoka.model.response.ResponseObject;
import hoangvacban.demo.projectmoka.repository.CommentRepository;
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
public class CommentService {

    CommentRepository commentRepository;
    PostRepository postRepository;
    CommentMapper commentMapper;


    public ResponseObject addComment(CommentRequest comment) {
        // fetch Post with id
        Post post = postRepository.findById(comment.getPost_id())
                .orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND));
        // create Comment
        Comment newComment = commentMapper.toComment(comment);
        newComment.setPost(post);
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

    public ResponseObject getComments(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return new ResponseObject(
                "ok",
                "ok",
                commentRepository.findAll(pageRequest)
        );
    }

    public ResponseObject getCommentsByPostId(long post_id, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return new ResponseObject(
                "ok",
                "ok",
                commentRepository.findAllCommentsByPostId(post_id, pageRequest)
        );
    }
}
