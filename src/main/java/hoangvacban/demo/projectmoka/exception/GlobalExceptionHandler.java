package hoangvacban.demo.projectmoka.exception;

import hoangvacban.demo.projectmoka.model.response.ResponseObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = RuntimeException.class)
    ResponseEntity<ResponseObject> handleRuntimeException(RuntimeException exception) {
        return ResponseEntity.badRequest().body(
                new ResponseObject("400", exception.getMessage(), null)
        );
    }

}
