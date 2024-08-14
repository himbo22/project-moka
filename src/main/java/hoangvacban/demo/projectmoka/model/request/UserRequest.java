package hoangvacban.demo.projectmoka.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequest {
    public String username;
    public String password;
    public String email;
    public String avatar;
}
