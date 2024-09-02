package hoangvacban.demo.projectmoka.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "comments")
@ToString
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String author;
    private String content;
    private Date createdAt = new Date();

    @ManyToOne
    @JoinColumn(name = "post_id", referencedColumnName = "id") // referencedColumnName must be an ID name in Post
    private Post post;
}
