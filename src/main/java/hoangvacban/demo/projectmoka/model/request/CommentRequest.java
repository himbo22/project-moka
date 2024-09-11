package hoangvacban.demo.projectmoka.model.request;

import lombok.Getter;

@Getter
public class CommentRequest {
    private String content;
    private String createdAt;
    private Long author;
    private Long post_id;
}
