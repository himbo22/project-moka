package hoangvacban.demo.projectmoka.controller;

import hoangvacban.demo.projectmoka.model.response.ResponseObject;
import hoangvacban.demo.projectmoka.model.response.UserPosts;
import hoangvacban.demo.projectmoka.service.PostService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PostController {

    PostService postService;


    @PostMapping(value = "/create")
    public ResponseObject create(
            @RequestPart(name = "userId") String userId,
            @RequestPart(name = "caption") String caption,
            @RequestPart(name = "createdAt") String createdAt,
            @RequestPart(name = "content") MultipartFile content) {
        return postService.createPost(userId, content, caption, createdAt);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseObject delete(@PathVariable("id") String id) {
        postService.deletePost(id);
        return new ResponseObject(
                "ok",
                "ok",
                "ok"
        );
    }

    @GetMapping("/user/{id}")
    public ResponseObject getPostById(@PathVariable("id") Long id, @RequestParam int page, @RequestParam int size,
                                      PagedResourcesAssembler<UserPosts> assembler) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<UserPosts> posts = postService.findAllByUserId(id, pageRequest);
        return new ResponseObject(
                "ok",
                "ok",
                assembler.toModel(posts)
        );
    }

    @GetMapping("/{id}")
    public ResponseObject getPostById(@PathVariable long id) {
        return postService.getPostById(id);
    }

    @GetMapping("/all")
    public ResponseObject getAllPosts(@RequestParam int page, @RequestParam int size,
                                      PagedResourcesAssembler<UserPosts> assembler) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<UserPosts> posts = postService.getAllPosts(pageRequest);
        return new ResponseObject(
                "ok",
                "ok",
                assembler.toModel(posts)
        );
    }

}
