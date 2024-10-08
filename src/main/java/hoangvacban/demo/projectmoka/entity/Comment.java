package hoangvacban.demo.projectmoka.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String content;
    private String createdAt;

    @ManyToOne
    @JoinColumn(name = "post_id", referencedColumnName = "id") // referencedColumnName must be an ID name in Post
    private Post post;

    @ManyToOne
    @JoinColumn(name = "author", referencedColumnName = "id")
    private User author;
}
