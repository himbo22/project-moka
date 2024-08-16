package hoangvacban.demo.projectmoka.exception;

import hoangvacban.demo.projectmoka.model.response.ResponseObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Objects;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = RuntimeException.class)
    ResponseEntity<ResponseObject> handleRuntimeException(RuntimeException exception) {
        return ResponseEntity.badRequest().body(
                new ResponseObject("400", exception.getMessage(), null)
        );
    }

    @ExceptionHandler(value = AppException.class)
    ResponseEntity<ResponseObject> handleAppException(AppException exception) {
        return ResponseEntity.badRequest().body(
                new ResponseObject(exception.getErrorCode().getStatusCode(), exception.getErrorCode().getMessage(), null)
        );
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    ResponseEntity<ResponseObject> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        return ResponseEntity.badRequest().body(
                new ResponseObject("400", Objects.requireNonNull(exception.getFieldError()).getDefaultMessage(), null)
        );
    }
}
