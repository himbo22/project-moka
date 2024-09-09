package hoangvacban.demo.projectmoka.model.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class UserPosts {
    private Long id;
    private String caption;
    private String content;
    private String createdAt;
    private Long author;
    private String authorAvatar;
    private Long liked;
    private Long commented;
}
