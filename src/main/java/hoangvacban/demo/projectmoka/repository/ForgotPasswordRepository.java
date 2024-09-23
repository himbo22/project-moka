package hoangvacban.demo.projectmoka.repository;

import hoangvacban.demo.projectmoka.entity.ForgotPassword;
import hoangvacban.demo.projectmoka.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ForgotPasswordRepository extends JpaRepository<ForgotPassword, Long> {
    Optional<ForgotPassword> findByUser(User user);
}
