package hoangvacban.demo.projectmoka.repository;

import hoangvacban.demo.projectmoka.entity.Post;
import hoangvacban.demo.projectmoka.model.response.UserPosts;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    @Query("SELECT new " +
            "hoangvacban.demo.projectmoka.model.response.UserPosts" +
            "(p.id,p.caption,p.content,p.createdAt,p.author.id,p.author.avatar,p.author.username, count(distinct r.id), count(distinct c.id)) " +
            "FROM Post p left join Comment c on p.id=c.post.id left join Reaction r on p.id=r.post.id " +
            "where p.author.id=:userId " +
            "group by p.id,p.author.id")
    Page<UserPosts> findAllByAuthorId(@Param("userId") Long userId, Pageable pageable);

    @Query("SELECT new " +
            "hoangvacban.demo.projectmoka.model.response.UserPosts" +
            "(p.id,p.caption,p.content,p.createdAt,p.author.id,p.author.avatar,p.author.username, count(distinct r.id), count(distinct c.id)) " +
            "FROM Post p left join Comment c on p.id=c.post.id left join Reaction r on p.id=r.post.id " +
            "group by p.id,p.author.id")
    Page<UserPosts> findAllPosts(Pageable pageable);

}
