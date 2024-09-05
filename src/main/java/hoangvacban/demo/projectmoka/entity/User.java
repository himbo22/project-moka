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
    @NotNull
    private String avatar;

    @JsonIgnore
    @OneToMany(mappedBy = "followedUser", cascade = CascadeType.ALL)
    private List<Follower> followedUsers;

    @JsonIgnore
    @OneToMany(mappedBy = "followingUser", cascade = CascadeType.ALL)
    private List<Follower> followingUsers;

//    @OneToMany(mappedBy = "users")
//    private List<Like> like;
}
