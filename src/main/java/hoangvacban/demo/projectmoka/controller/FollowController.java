package hoangvacban.demo.projectmoka.controller;

import hoangvacban.demo.projectmoka.model.request.FollowRequest;
import hoangvacban.demo.projectmoka.model.response.ResponseObject;
import hoangvacban.demo.projectmoka.service.FollowerService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/follows/")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FollowController {

    FollowerService followerService;

    @PostMapping("follow")
    public ResponseObject follow(@RequestBody FollowRequest followRequest) {
        return followerService.followUser(followRequest);
    }

}
