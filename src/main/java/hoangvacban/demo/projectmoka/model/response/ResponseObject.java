package hoangvacban.demo.projectmoka.model.response;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class ResponseObject {
    public String status;
    public String message;
    public Object data;
}
