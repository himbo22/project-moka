package hoangvacban.demo.projectmoka.repository;

import hoangvacban.demo.projectmoka.entity.User;
import hoangvacban.demo.projectmoka.model.response.UserResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByUsername(String username);

    boolean existsByUsernameAndId(String username, Long id);

    Optional<User> findByEmail(String email);

    @Query("select new hoangvacban.demo.projectmoka.model.response.UserResponse" +
            "(u.id, u.username, u.email, u.avatar,u.bio, count(distinct p.id), " +
            "(select count(f.id) from u.followedUsers f)," +
            "(select count(f.id) from u.followingUsers f)) " +
            "from User u left join Post p on u.id=p.author.id where u.id = :userId group by u.id")
    Optional<UserResponse> findUserById(@Param("userId") Long id);

    @Query("select u from User u where u.username like concat(:username, '%') ")
    Page<User> findByUsernameLike(@Param("username") String username, Pageable pageable);

    Optional<User> findByUsername(String username);
}
