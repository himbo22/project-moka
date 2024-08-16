package hoangvacban.demo.projectmoka.controller;

import hoangvacban.demo.projectmoka.entity.User;
import hoangvacban.demo.projectmoka.model.request.UserRequest;
import hoangvacban.demo.projectmoka.model.response.ResponseObject;
import hoangvacban.demo.projectmoka.service.UserService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
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
    public ResponseEntity<ResponseObject> getUser(@PathVariable String id) {
        User user = userService.getUser(id);
        return ResponseEntity.ok().body(
                new ResponseObject("ok", "ok", user)
        );
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseObject> createUser(
            @RequestPart("user") @Valid UserRequest user,
            @RequestPart("image") MultipartFile image
    ) {
        User createdUser = userService.createUser(user, image);
        return ResponseEntity.ok().body(
                new ResponseObject("ok", "ok", createdUser)
        );
    }

}
