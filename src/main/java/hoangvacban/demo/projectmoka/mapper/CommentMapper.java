package hoangvacban.demo.projectmoka.mapper;

import hoangvacban.demo.projectmoka.entity.Comment;
import hoangvacban.demo.projectmoka.model.request.CommentRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    Comment toComment(CommentRequest commentRequest);
}
