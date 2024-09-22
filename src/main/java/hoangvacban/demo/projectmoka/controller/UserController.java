package hoangvacban.demo.projectmoka.controller;

import hoangvacban.demo.projectmoka.entity.User;
import hoangvacban.demo.projectmoka.model.request.LoginRequest;
import hoangvacban.demo.projectmoka.model.response.ResponseObject;
import hoangvacban.demo.projectmoka.model.response.UserResponse;
import hoangvacban.demo.projectmoka.service.UserService;
import jakarta.annotation.Nullable;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/users/")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {

    UserService userService;

    @GetMapping("{id}")
    public ResponseEntity<ResponseObject> getUser(@PathVariable long id) {
        UserResponse user = userService.getUser(id);
        return ResponseEntity.ok().body(
                new ResponseObject("ok", "ok", user)
        );
    }

    @PutMapping("/update")
    public ResponseObject updateUser(
            @RequestPart("userId") String userId,
            @RequestPart("username") String username,
            @Nullable @RequestPart("bio") String bio,
            @Nullable @RequestPart("image") MultipartFile image) {
        User user = userService.updateUser(userId, username, bio, image);
        return new ResponseObject("ok", "ok", user);
    }

    @GetMapping("search/{username}")
    public ResponseObject getUsers(@PathVariable String username,
                                   @RequestParam("page") int page,
                                   @RequestParam("size") int size,
                                   PagedResourcesAssembler<User> assembler) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<User> users = userService.getUserByName(username, pageRequest);
        return new ResponseObject("ok", "ok", assembler.toModel(users));
    }


    @PostMapping("/create")
    public ResponseEntity<ResponseObject> createUser(
            @NotBlank(message = "username must not blank") @RequestPart("username") String username,
            @Size(min = 8, message = "Password must be greater than 8 characters") @RequestPart("password") String password,
            @Email @NotBlank(message = "email must not blank") @RequestPart("email") String email,
            @RequestPart("bio") String bio,
            @RequestPart("image") MultipartFile image
    ) {
        return ResponseEntity.ok().body(
                userService.createUser(username, password, email, bio, image)
        );
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseObject> loginUser(@RequestBody @Valid LoginRequest user) {
        return ResponseEntity.ok().body(
                userService.loginUser(user)
        );
    }

}
