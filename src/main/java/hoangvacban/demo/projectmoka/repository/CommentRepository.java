package hoangvacban.demo.projectmoka.repository;

import hoangvacban.demo.projectmoka.entity.Comment;
import hoangvacban.demo.projectmoka.model.response.CommentResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Query("SELECT c FROM Comment c WHERE c.post.id = :postId")
    Page<Comment> findAllCommentsByPostId(@Param("postId") Long postId, Pageable pageable);

    @Query("SELECT new hoangvacban.demo.projectmoka.model.response." +
            "CommentResponse(c.id,c.author.avatar,c.author.username,c.content) " +
            "FROM Comment c WHERE c.post.id = :postId")
    Page<CommentResponse> getCommentsByPostId(@Param("postId") Long postId, Pageable pageable);
}
