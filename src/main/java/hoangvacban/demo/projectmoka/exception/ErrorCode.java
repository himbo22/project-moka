package hoangvacban.demo.projectmoka.exception;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    ALREADY_EXISTED("403", "Already exists"),
    NOT_FOUND("404", "Not found"),
    ;

    private final String statusCode;
    private final String message;
}
