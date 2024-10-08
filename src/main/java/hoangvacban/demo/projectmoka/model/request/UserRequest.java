package hoangvacban.demo.projectmoka.model.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserRequest {
    @NotBlank(message = "username must not blank")
    public String username;
    @Size(min = 8, message = "Password must be greater than 8 characters")
    public String password;
    @Email
    @NotBlank(message = "email must not blank")
    public String email;
    public String avatar;
    public String bio;
}
