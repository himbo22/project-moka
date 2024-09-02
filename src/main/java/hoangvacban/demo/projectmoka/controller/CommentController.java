package hoangvacban.demo.projectmoka.controller;

import hoangvacban.demo.projectmoka.model.request.CommentRequest;
import hoangvacban.demo.projectmoka.model.response.ResponseObject;
import hoangvacban.demo.projectmoka.service.CommentService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comments/")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CommentController {

    CommentService commentService;

    @GetMapping("all")
    public ResponseObject getAllComments() {
        return commentService.getComments();
    }

    @GetMapping("/{id}")
    public ResponseObject getCommentById(@PathVariable long id) {
        return commentService.getCommentById(id);
    }


    @PutMapping("add")
    public ResponseObject addComment(@RequestBody CommentRequest comment) {
        return commentService.addComment(comment);
    }
}
