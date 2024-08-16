package hoangvacban.demo.projectmoka.mapper;

import hoangvacban.demo.projectmoka.entity.User;
import hoangvacban.demo.projectmoka.model.request.UserRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserRequest userRequest);
}
