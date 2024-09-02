package hoangvacban.demo.projectmoka.mapper;

import hoangvacban.demo.projectmoka.entity.Post;
import hoangvacban.demo.projectmoka.model.request.PostRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PostMapper {
    Post toPost(PostRequest postRequest);
}
