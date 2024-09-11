package hoangvacban.demo.projectmoka.service;

import hoangvacban.demo.projectmoka.entity.Post;
import hoangvacban.demo.projectmoka.entity.Reaction;
import hoangvacban.demo.projectmoka.entity.User;
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

    public Reaction addReaction(Long post_id, Long user_id) {
        Post post = postRepository.findById(post_id).orElseThrow(
                () -> new RuntimeException("Post not found")
        );
        User user = userRepository.findById(user_id).orElseThrow(
                () -> new RuntimeException("User not found")
        );
        boolean exists = reactionRepository.existsByPostIdAndAuthorId(post_id, user_id);
        if (exists) {
            throw new RuntimeException("Reaction already exists");
        }
        Reaction reaction = new Reaction();
        reaction.setPost(post);
        reaction.setAuthor(user);
        reactionRepository.save(reaction);
        return reaction;
    }

    public void deleteReaction(Long user_id, Long post_id) {
        Reaction existingReaction = reactionRepository.getByPostIdAndAuthorId(post_id, user_id);
        reactionRepository.delete(existingReaction);
    }

    public boolean existReaction(Long post_id, Long user_id) {
        return reactionRepository.existsByPostIdAndAuthorId(post_id, user_id);
    }
}
