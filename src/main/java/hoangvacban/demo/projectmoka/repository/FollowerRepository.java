package hoangvacban.demo.projectmoka.repository;

import hoangvacban.demo.projectmoka.entity.Follower;
import hoangvacban.demo.projectmoka.model.response.FollowerResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FollowerRepository extends JpaRepository<Follower, Long> {
    //    @Query(value = "select u.id,u.username,u.email,u.avatar from followers f, users u where followed_id = 1 and following_id = u.id", nativeQuery = true)
    @Query("SELECT new hoangvacban.demo.projectmoka.model.response.FollowerResponse(u.id, u.username, u.email, u.avatar) FROM Follower f, User u WHERE f.followedUser.id = :userId and f.followingUser.id = u.id")
    Page<FollowerResponse> findFollowersByUserId(@Param("userId") long userId, Pageable pageable);

}
