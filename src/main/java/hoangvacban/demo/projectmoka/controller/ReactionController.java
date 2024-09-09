package hoangvacban.demo.projectmoka.controller;

import hoangvacban.demo.projectmoka.model.request.ReactionRequest;
import hoangvacban.demo.projectmoka.model.response.ResponseObject;
import hoangvacban.demo.projectmoka.service.ReactionService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/reactions/")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ReactionController {

    ReactionService reactionService;

    @PostMapping("add")
    public ResponseObject addReaction(
            @RequestBody ReactionRequest reaction
    ) {
        return new ResponseObject(
                "ok",
                "ok",
                reactionService.addReaction(reaction)
        );
    }

}
