package springboot.library.registration.email;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import springboot.library.model.AppUser;
import springboot.library.model.dto.write.AppUserEmailDto;
import springboot.library.registration.token.ConfirmationEmailToken;
import springboot.library.registration.token.ConfirmationEmailTokenService;
import springboot.library.service.AppUserService;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class EmailDtoService {

    private final EmailValidator emailValidator;
    private final AppUserService appUserService;

    private final EmailService emailService;

    private final ConfirmationEmailTokenService confirmationEmailTokenService;


    public String emailUpdate(AppUserEmailDto appUserEmailDto, Long id) {

        //Email verification
        boolean isValidEmail = emailValidator.test(appUserEmailDto.getEmail());
        if (!isValidEmail) {
            throw new IllegalStateException("email is not valid");
        }

        //Email is present
        boolean isPresentEmail = appUserService.emailExists(appUserEmailDto.getEmail());
        if (isPresentEmail) {
            throw new IllegalStateException("email already taken");
        }

        //find id
        AppUser appUser = appUserService.findById(id);

        //Creates and saves new token
        String token = confirmationEmailTokenService.createToken(appUser);
        ConfirmationEmailToken confirmationEmailToken = confirmationEmailTokenService.createConfirmationEmailToken(appUser, token);
        confirmationEmailTokenService.saveConfirmationEmailToken(confirmationEmailToken);

        //Send mail
        emailService.send(appUserEmailDto.getEmail(), emailService.buildMailUpdate(token));

        //Set new email & Set enabled to false, after token confirmation account will be set to true
        appUserService.updateEmail(id, appUserEmailDto.getEmail());
        appUserService.updateEnabledById(id, false);

        return token;
    }
    public String confirmToken(String token) {
        ConfirmationEmailToken confirmationEmailToken = confirmationEmailTokenService
                .getToken(token)
                .orElseThrow(() -> new IllegalStateException("token not found"));

        if(confirmationEmailToken.getConfirmedAt() == null) {
            throw new IllegalStateException("email already confirmed");
        }

        LocalDateTime expiredAt = confirmationEmailToken.getExpiresAt();

        if(expiredAt.isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("token expired");
        }

        confirmationEmailTokenService.setConfirmedAt(token);
        appUserService.updateEnabled(confirmationEmailToken.getAppUser().getEmail(), true);

        return "confirmed";
    }

}
