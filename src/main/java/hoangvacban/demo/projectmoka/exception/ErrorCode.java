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
    EMPTY_INFORMATION("1009", "Empty information"),
    USER_ALREADY_EXISTED("1010", "User already existed"),
    USERNAME_MUST_NOT_CONTAINS_SPACE("1011", "Username must not contain space"),
    USERNAME_MUST_NOT_BLANK("1012", "Username must not be blank"),
    NOT_TIME_TO_RESEND_OTP("1013", "Not time to resend OTP code yet"),
    PASSWORD_NOT_EQUALS("1014", "Password does not match"),
    OTP_DOES_NOT_MATCH("1015", "OTP does not match"),
    EXPIRE_OTP("1016", "Expired OTP"),;


    private final String statusCode;
    private final String message;
}
