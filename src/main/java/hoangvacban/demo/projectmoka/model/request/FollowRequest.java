package hoangvacban.demo.projectmoka.model.request;

import lombok.Getter;

@Getter
public class FollowRequest {
    private long followingUser;
    private long followedUser;
}
