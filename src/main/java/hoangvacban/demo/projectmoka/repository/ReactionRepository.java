package hoangvacban.demo.projectmoka.repository;

import hoangvacban.demo.projectmoka.entity.Reaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReactionRepository extends JpaRepository<Reaction, Long> {
    boolean existsByPostIdAndAuthorId(Long postId, Long authorId);

    Reaction getByPostIdAndAuthorId(Long postId, Long authorId);
}
