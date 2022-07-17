package springboot.library.registration.email;

import lombok.AllArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
@AllArgsConstructor
public class EmailService {


    private final JavaMailSender javaMailSender;
    private final TemplateEngine templateEngine;


    @Async
    public void send(String to, String email) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
            helper.setText(email, true);
            helper.setTo(to);
            helper.setSubject("email address verification");
            helper.setFrom("dev.pb.example@gmail.com");
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            throw new IllegalStateException("failed to send email");
        }

    }
    public String buildMailRegistration(String token, String firstName) {
        Context context = new Context();
        context.setVariable("title", "Verify your email address");
        context.setVariable("link", "http://localhost:8080/api/v1/registration/confirm?token=" + token);
        // TODO: setVariable and match it with html -> firstName
        return templateEngine.process("registration-verification", context);
    }


    public String buildMailUpdate(String token) {
        Context context = new Context();
        context.setVariable("title", "Verify your new email address");
        context.setVariable("link", "http://localhost:8080/api/v1/email-update/confirm?token=" + token);
        return templateEngine.process("email-verification", context);
    }
}
