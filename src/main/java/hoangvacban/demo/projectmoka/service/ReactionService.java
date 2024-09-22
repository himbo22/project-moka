package hoangvacban.demo.projectmoka.service;

import hoangvacban.demo.projectmoka.entity.Post;
import hoangvacban.demo.projectmoka.entity.Reaction;
import hoangvacban.demo.projectmoka.entity.User;
import hoangvacban.demo.projectmoka.model.response.UserPosts;
import hoangvacban.demo.projectmoka.repository.PostRepository;
import hoangvacban.demo.projectmoka.repository.ReactionRepository;
import hoangvacban.demo.projectmoka.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ReactionService {

    ReactionRepository reactionRepository;
    PostRepository postRepository;
    UserRepository userRepository;
    private final PostService postService;

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

    public List<Boolean> getLikedPost(Long userIdLiked, Long userIdPostsOwner, Pageable pageable) {
        Page<UserPosts> userPosts = postService.findAllByUserId(userIdPostsOwner, pageable);
        List<Boolean> likedPosts = new ArrayList<>();
        for (UserPosts userPost : userPosts) {
            if (existReaction(userPost.getId(), userIdLiked)) {
                likedPosts.add(true);
            } else {
                likedPosts.add(false);
            }
        }
        return likedPosts;
    }

    public void deleteReaction(Long user_id, Long post_id) {
        Reaction existingReaction = reactionRepository.getByPostIdAndAuthorId(post_id, user_id);
        reactionRepository.delete(existingReaction);
    }

    public boolean existReaction(Long post_id, Long user_id) {
        return reactionRepository.existsByPostIdAndAuthorId(post_id, user_id);
    }
}
