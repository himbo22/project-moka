package hoangvacban.demo.projectmoka.repository;

import hoangvacban.demo.projectmoka.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
    boolean existsByUsername(String username);
}
