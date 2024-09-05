package hoangvacban.demo.projectmoka.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "followers")
public class Follower {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "followed_id", referencedColumnName = "id")
    private User followedUser;

    @ManyToOne
    @JoinColumn(name = "following_id", referencedColumnName = "id")
    private User followingUser;

    public Follower(User followed, User following) {
        this.followedUser = followed;
        this.followingUser = following;
    }
}
