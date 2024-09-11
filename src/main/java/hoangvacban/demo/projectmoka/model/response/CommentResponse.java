package hoangvacban.demo.projectmoka.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CommentResponse {
    private long id;
    private String avatar;
    private String username;
    private String content;
}
