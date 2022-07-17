package springboot.library.registration;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import springboot.library.model.AppUser;
import springboot.library.registration.email.EmailService;
import springboot.library.registration.email.EmailValidator;
import springboot.library.registration.token.ConfirmationEmailToken;
import springboot.library.registration.token.ConfirmationEmailTokenService;
import springboot.library.service.AppUserService;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class RegistrationDtoService {

    private final EmailValidator emailValidator;
    private final AppUserService appUserService;

    private final ConfirmationEmailTokenService confirmationEmailTokenService;

    private final EmailService emailService;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public String register(RegistrationDto registrationDto) {

        //Email validation
        boolean isValidEmail = emailValidator.test(registrationDto.getEmail());
        if (!isValidEmail) {
            throw new IllegalStateException("email is not valid");
        }

        //Email is present
        boolean isPresentEmail = appUserService.emailExists(registrationDto.getEmail());
        if (isPresentEmail) {
            throw new IllegalStateException("email already taken");
        }

        AppUser appUser = new AppUser(registrationDto.getEmail(),
                                      (registrationDto.getPassword()),
                                      (registrationDto.getFirstName()),
                                      (registrationDto.getLastName()),
                                       true,
                                       true,
                                       true,
                                       false);


        appUser.setPassword(bCryptPasswordEncoder.encode(appUser.getPassword()));

        String token = appUserService.signUpAppUser(appUser);
        emailService.send(registrationDto.getEmail(), emailService.buildMailRegistration(token, registrationDto.getFirstName()));

        return token;
    }

    @Transactional
    public String confirmToken(String token) {

        ConfirmationEmailToken confirmationEmailToken = confirmationEmailTokenService
                .getToken(token)
                .orElseThrow(() -> new IllegalStateException("token not found"));

        if(confirmationEmailToken.getConfirmedAt() == null) {
            throw new IllegalStateException("email already confirmed");
        }

        //save token

        LocalDateTime expiredAt = confirmationEmailToken.getExpiresAt();

        if(expiredAt.isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("token expired");
        }

        confirmationEmailTokenService.setConfirmedAt(token);
        appUserService.updateEnabled(confirmationEmailToken.getAppUser().getEmail(), true);

        return "confirmed";
    }

}
