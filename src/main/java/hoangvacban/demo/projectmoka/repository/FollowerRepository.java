package hoangvacban.demo.projectmoka.repository;

import hoangvacban.demo.projectmoka.entity.Follower;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FollowerRepository extends JpaRepository<Follower, Long> {
}
