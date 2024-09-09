package hoangvacban.demo.projectmoka.controller;

import hoangvacban.demo.projectmoka.model.request.FollowRequest;
import hoangvacban.demo.projectmoka.model.response.FollowerResponse;
import hoangvacban.demo.projectmoka.model.response.ResponseObject;
import hoangvacban.demo.projectmoka.service.FollowerService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PagedResourcesAssembler;
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

    @GetMapping("follower/{user_id}")
    public ResponseObject getFollower(@PathVariable long user_id, @RequestParam int page, @RequestParam int size,
                                      PagedResourcesAssembler<FollowerResponse> assembler) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<FollowerResponse> follower = followerService.getFollowers(user_id, pageRequest);
        return new ResponseObject(
                "ok",
                "ok",
                assembler.toModel(follower)
        );
    }

}
