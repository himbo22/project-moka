package hoangvacban.demo.projectmoka.model.request;

import lombok.Getter;

import java.util.Date;

@Getter
public class PostRequest {
    private String author;
    private String content;
    private Date createAt;
}
