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
    public ResponseObject getAllComments(@RequestParam int page, @RequestParam int size) {
        return commentService.getComments(page, size);
    }

    @GetMapping("{id}")
    public ResponseObject getCommentById(@PathVariable long id) {
        return commentService.getCommentById(id);
    }


    @PutMapping("add")
    public ResponseObject addComment(@RequestBody CommentRequest comment) {
        return commentService.addComment(comment);
    }

    @GetMapping("post/{id}")
    public ResponseObject getAllComments(
            @PathVariable long id,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return commentService.getCommentsByPostId(id, page, size);
    }
}
