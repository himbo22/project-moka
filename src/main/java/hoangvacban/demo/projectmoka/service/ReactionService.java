package hoangvacban.demo.projectmoka.service;

import hoangvacban.demo.projectmoka.entity.Post;
import hoangvacban.demo.projectmoka.entity.Reaction;
import hoangvacban.demo.projectmoka.entity.User;
import hoangvacban.demo.projectmoka.model.request.ReactionRequest;
import hoangvacban.demo.projectmoka.repository.PostRepository;
import hoangvacban.demo.projectmoka.repository.ReactionRepository;
import hoangvacban.demo.projectmoka.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ReactionService {

    ReactionRepository reactionRepository;
    PostRepository postRepository;
    UserRepository userRepository;

    public Reaction addReaction(ReactionRequest reactionRequest) {
        Post post = postRepository.findById(reactionRequest.getPost_id()).orElseThrow(
                () -> new RuntimeException("Post not found")
        );
        User user = userRepository.findById(reactionRequest.getUser_id()).orElseThrow(
                () -> new RuntimeException("User not found")
        );

        Reaction reaction = new Reaction();
        reaction.setPost(post);
        reaction.setAuthor(user);
        reactionRepository.save(reaction);
        return reaction;
    }
}
