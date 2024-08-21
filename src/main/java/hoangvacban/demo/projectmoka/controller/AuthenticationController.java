package hoangvacban.demo.projectmoka.controller;

import hoangvacban.demo.projectmoka.model.request.AuthenticationRequest;
import hoangvacban.demo.projectmoka.model.request.IntrospectRequest;
import hoangvacban.demo.projectmoka.model.response.AuthenticationResponse;
import hoangvacban.demo.projectmoka.model.response.ResponseObject;
import hoangvacban.demo.projectmoka.service.AuthenticationService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationController {

    AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<ResponseObject> login(@RequestBody AuthenticationRequest authenticationRequest) {
        AuthenticationResponse result = authenticationService.authenticate(authenticationRequest);
        return ResponseEntity.ok().body(
                new ResponseObject("ok", "ok", result)
        );
    }

    @PostMapping("/introspect")
    public ResponseEntity<ResponseObject> introspect(@RequestBody IntrospectRequest introspectRequest) {
        var response = authenticationService.introspect(introspectRequest);
        return ResponseEntity.ok().body(
                new ResponseObject("ok", "ok", response)
        );
    }
}
