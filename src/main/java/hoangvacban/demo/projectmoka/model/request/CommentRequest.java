package hoangvacban.demo.projectmoka.model.request;

import lombok.Getter;

import java.util.Date;

@Getter
public class CommentRequest {
    private String author;
    private String content;
    private Date createdAt;
    private long post_id;
}
