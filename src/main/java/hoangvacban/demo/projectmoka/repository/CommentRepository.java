package hoangvacban.demo.projectmoka.repository;

import hoangvacban.demo.projectmoka.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
}
