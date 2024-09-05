package hoangvacban.demo.projectmoka.controller;

import hoangvacban.demo.projectmoka.model.request.PostRequest;
import hoangvacban.demo.projectmoka.model.response.ResponseObject;
import hoangvacban.demo.projectmoka.service.PostService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PostController {

    PostService postService;

    @PostMapping("/create")
    public ResponseObject create(@RequestBody PostRequest post) {
        return postService.createPost(post);
    }

    @GetMapping("/{id}")
    public ResponseObject getPostById(@PathVariable long id) {
        return postService.getPostById(id);
    }

    @GetMapping("/all")
    public ResponseObject getAllPosts(@RequestParam int page, @RequestParam int size) {
        return postService.getAllPosts(page, size);
    }

}
