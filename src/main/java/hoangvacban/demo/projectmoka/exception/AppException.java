package hoangvacban.demo.projectmoka.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AppException extends RuntimeException{

    private ErrorCode errorCode;

}
