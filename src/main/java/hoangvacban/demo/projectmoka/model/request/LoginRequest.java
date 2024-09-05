package hoangvacban.demo.projectmoka.model.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {
    @NotBlank(message = "username must not blank")
    private String username;
    @NotBlank(message = "password must not blank")
    private String password;
}
