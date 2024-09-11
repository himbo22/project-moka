package hoangvacban.demo.projectmoka.controller;

import hoangvacban.demo.projectmoka.entity.Comment;
import hoangvacban.demo.projectmoka.model.request.CommentRequest;
import hoangvacban.demo.projectmoka.model.response.CommentResponse;
import hoangvacban.demo.projectmoka.model.response.ResponseObject;
import hoangvacban.demo.projectmoka.service.CommentService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comments/")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CommentController {

    CommentService commentService;

    @GetMapping("all")
    public ResponseObject getAllComments(@RequestParam int page, @RequestParam int size,
                                         PagedResourcesAssembler<Comment> assembler) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Comment> comments = commentService.getComments(pageRequest);
        return new ResponseObject(
                "ok",
                "ok",
                assembler.toModel(comments)
        );
    }

    @PutMapping("add")
    public ResponseObject addComment(@RequestBody CommentRequest commentRequest) {
        return commentService.addComment(commentRequest);
    }

    @GetMapping("{id}")
    public ResponseObject getCommentById(@PathVariable long id) {
        return commentService.getCommentById(id);
    }


    @GetMapping("post/{id}")
    public ResponseObject getAllComments(
            @PathVariable long id,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            PagedResourcesAssembler<CommentResponse> assembler) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<CommentResponse> comments = commentService.getCommentsByPostId(id, pageRequest);
        return new ResponseObject(
                "ok",
                "ok",
                assembler.toModel(comments)
        );
    }
}
