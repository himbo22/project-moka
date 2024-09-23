package hoangvacban.demo.projectmoka.service;

import hoangvacban.demo.projectmoka.entity.ForgotPassword;
import hoangvacban.demo.projectmoka.entity.User;
import hoangvacban.demo.projectmoka.exception.AppException;
import hoangvacban.demo.projectmoka.exception.ErrorCode;
import hoangvacban.demo.projectmoka.model.request.MailBody;
import hoangvacban.demo.projectmoka.repository.ForgotPasswordRepository;
import hoangvacban.demo.projectmoka.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.Random;

@Service
public class EmailService {

    private final JavaMailSender mailSender;
    private final UserRepository userRepository;
    private final ForgotPasswordRepository forgotPasswordRepository;

    @Value("${spring.mail.username")
    private String email;


    public EmailService(JavaMailSender mailSender, UserRepository userRepository, ForgotPasswordRepository forgotPasswordRepository) {
        this.mailSender = mailSender;
        this.userRepository = userRepository;
        this.forgotPasswordRepository = forgotPasswordRepository;
    }

    public void verifyOtp(Integer otp, String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
        ForgotPassword forgotPassword = forgotPasswordRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Forgot Password not found"));
        if (forgotPassword.getExpirationTime().before(Date.from(Instant.now()))) {
            throw new AppException(ErrorCode.EXPIRE_OTP);
        }
        if (!forgotPassword.getOtp().equals(otp)) {
            throw new AppException(ErrorCode.OTP_DOES_NOT_MATCH);
        }
        forgotPasswordRepository.delete(forgotPassword);
    }

    public void verifyEmailToResetPassword(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));

        int otp = otpGenerator();
        MailBody mailBody = MailBody.builder()
                .to(email)
                .subject("Reset your Project-BTS password")
                .text("OTP: " + otp)
                .build();

        ForgotPassword firstTimeForgotPassword = ForgotPassword.builder()
                .otp(otp)
                .expirationTime(new Date(System.currentTimeMillis() + 600 * 1000)) // 600s
                .user(user)
                .build();

        sendMail(mailBody);
        forgotPasswordRepository.save(firstTimeForgotPassword);
    }

    public void sendMail(MailBody mailBody) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(mailBody.to());
        message.setFrom(email);
        message.setSubject(mailBody.subject());
        message.setText(mailBody.text());

        mailSender.send(message);
    }

    private Integer otpGenerator() {
        Random rand = new Random();
        return rand.nextInt(10_000, 99_999);
    }
}
