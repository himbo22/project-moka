package hoangvacban.demo.projectmoka.model.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
public class UserRequest {
    public String username;
    @Size(min = 8, message = "Password must be greater than 8 characters")
    public String password;
    @Email
    public String email;
    public String avatar;
}
