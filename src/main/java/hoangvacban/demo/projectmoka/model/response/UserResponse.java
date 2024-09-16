package hoangvacban.demo.projectmoka.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class UserResponse {
    private long id;
    private String username;
    private String email;
    private String avatar;
    private String bio;
    private long post;
    private long followers;
    private long following;
}
