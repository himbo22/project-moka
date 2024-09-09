package hoangvacban.demo.projectmoka.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class FollowerResponse {
    private long follower_id;
    private String username;
    private String email;
    private String avatar;
}
