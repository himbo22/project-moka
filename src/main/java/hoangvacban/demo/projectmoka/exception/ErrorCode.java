package hoangvacban.demo.projectmoka.exception;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    NOT_FOUND("1004", "Not found"),
    USER_NOT_EXISTED("1005", "User not existed"),
    UNAUTHENTICATED("1006", "Wrong password"),
    INTERNAL_SERVER_ERROR("1007", "Server error"),
    EXPIRED_TOKEN("1008", "Expired token"),
    ;

    private final String statusCode;
    private final String message;
}
