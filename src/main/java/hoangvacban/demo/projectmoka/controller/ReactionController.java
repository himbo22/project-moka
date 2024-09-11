package hoangvacban.demo.projectmoka.controller;

import hoangvacban.demo.projectmoka.model.response.ResponseObject;
import hoangvacban.demo.projectmoka.service.ReactionService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reactions/")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ReactionController {

    ReactionService reactionService;

    @PostMapping("add/user/{user_id}/post/{post_id}")
    public ResponseObject addReaction(
            @PathVariable(name = "user_id") long user_id,
            @PathVariable(name = "post_id") long post_id
    ) {
        return new ResponseObject(
                "ok",
                "ok",
                reactionService.addReaction(post_id, user_id)
        );
    }

    @DeleteMapping("delete/user/{user_id}/post/{post_id}")
    public ResponseObject deleteReaction(
            @PathVariable(name = "user_id") long user_id,
            @PathVariable(name = "post_id") long post_id
    ) {
        reactionService.deleteReaction(user_id, post_id);
        return new ResponseObject(
                "ok",
                "ok",
                "ok"
        );
    }

    @GetMapping("user/{user_id}/post/{post_id}")
    public boolean likedReactions(
            @PathVariable(name = "user_id") long user_id,
            @PathVariable(name = "post_id") long post_id) {
        return reactionService.existReaction(post_id, user_id);
    }

}
