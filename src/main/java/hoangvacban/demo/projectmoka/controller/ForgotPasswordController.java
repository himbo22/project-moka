package hoangvacban.demo.projectmoka.controller;

import hoangvacban.demo.projectmoka.model.response.VerifyingResponse;
import hoangvacban.demo.projectmoka.service.EmailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/forgot-password")
public class ForgotPasswordController {

    private final EmailService emailService;

    public ForgotPasswordController(EmailService emailService) {
        this.emailService = emailService;
    }

    // send mail for email verification
    @PostMapping("/verify/{email}")
    public ResponseEntity<VerifyingResponse> verify(@PathVariable String email) {
        emailService.verifyEmailToResetPassword(email);
        return ResponseEntity.ok(new VerifyingResponse("send otp to email"));
    }

    @PostMapping("/verify/otp/{otp}/{email}")
    public ResponseEntity<VerifyingResponse> verifyOtp(@PathVariable Integer otp, @PathVariable String email) {
        emailService.verifyOtp(otp, email);
        return ResponseEntity.ok(new VerifyingResponse("verified"));
    }
}
