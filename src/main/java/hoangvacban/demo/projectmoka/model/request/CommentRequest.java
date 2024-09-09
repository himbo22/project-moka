package hoangvacban.demo.projectmoka.model.request;

import lombok.Getter;

@Getter
public class CommentRequest {
    private String content;
    private String createdAt;
    private String author;
    private String post_id;
}
