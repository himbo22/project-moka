package hoangvacban.demo.projectmoka.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotNull
    private String username;
    @NotNull
    private String password;
    @NotNull
    private String email;
    private String avatar;
    private String bio;

    @JsonIgnore
    @OneToMany(mappedBy = "followedUser", cascade = CascadeType.ALL)
    private List<Follower> followedUsers;

    @JsonIgnore
    @OneToMany(mappedBy = "followingUser", cascade = CascadeType.ALL)
    private List<Follower> followingUsers;

    @JsonIgnore
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private List<Post> postList;

    @JsonIgnore
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private List<Comment> commentList;

//    @OneToMany(mappedBy = "users")
//    private List<Like> like;
}
