package springboot.library.registration.token;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import springboot.library.model.AppUser;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ConfirmationEmailTokenService {

    private final ConfirmationEmailTokenRepository confirmationEmailTokenRepository;

    public void saveConfirmationEmailToken(ConfirmationEmailToken confirmationEmailToken) {
        confirmationEmailTokenRepository.save(confirmationEmailToken);
    }
   public String createToken(AppUser appUser) {
       String token = UUID.randomUUID().toString();
       return token;
   }
   public ConfirmationEmailToken createConfirmationEmailToken(AppUser appUser, String token) {
       ConfirmationEmailToken confirmationEmailToken = new ConfirmationEmailToken(
               token, LocalDateTime.now().plusMinutes(15), LocalDateTime.now().plusMinutes(15), appUser);
       return confirmationEmailToken;
   }
    @Transactional
    public Optional<ConfirmationEmailToken> getToken(String token) {
        return confirmationEmailTokenRepository.findByToken(token);
    }

    @Transactional
    public void setConfirmedAt(String token) {
         confirmationEmailTokenRepository.updateConfirmedAt(
                token, LocalDateTime.now());
    }


    @Transactional
    public void setEnabled(String token) {

    }


}
