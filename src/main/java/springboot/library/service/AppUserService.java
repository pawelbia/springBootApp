package springboot.library.service;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import springboot.library.model.AppUser;
import springboot.library.model.Book;
import springboot.library.model.dto.write.AppUserPasswordDto;
import springboot.library.registration.token.ConfirmationEmailToken;
import springboot.library.model.role_permission.AppUserRole;
import springboot.library.registration.token.ConfirmationEmailTokenService;
import springboot.library.repository.AppUserRepository;
import springboot.library.service.role_permisson_ser.RoleService;
import javax.transaction.Transactional;
import java.util.List;


@Service
@AllArgsConstructor
public class AppUserService implements UserDetailsService {

    private final static String USER_NOT_FOUND_MSG = "User with email %s not found";
    private final AppUserRepository appUserRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationEmailTokenService confirmationEmailTokenService;
    private final RoleService roleService;




    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return appUserRepository.findAppUserByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, email)));
    }

    public boolean emailExists(String email) {
        return appUserRepository.existsAppUserByEmail(email);
    }

    public String signUpAppUser(AppUser appUser) {

        AppUserRole appUserRole = new AppUserRole(appUser.getId(), roleService.findRoleByName("ROLE_USER").getId(), appUser, roleService.findRoleByName("ROLE_USER"));
        appUser.addAppUserRole(appUserRole);
        appUserRepository.save(appUser);

        String token = confirmationEmailTokenService.createToken(appUser);
        ConfirmationEmailToken confirmationEmailToken = confirmationEmailTokenService.createConfirmationEmailToken(appUser, token);
        confirmationEmailTokenService.saveConfirmationEmailToken(confirmationEmailToken);

        return token;
    }

    @Transactional
    public void updatePassword(Long id, AppUserPasswordDto appUserPasswordDto) {
        String encodePassword = bCryptPasswordEncoder.encode(appUserPasswordDto.getPassword());
        appUserRepository.updatePassword(id, encodePassword);
    }
    @Transactional
    public void updateAccountNonExpired(Long id, boolean accountNonExpired) {
        appUserRepository.updateAccountNonExpired(id, accountNonExpired);
    }
    @Transactional
    public void UpdateAccountNonLocked(Long id, boolean accountNonLocked) {
        appUserRepository.updateAccountNonLocked(id, accountNonLocked);
    }
    @Transactional
    public void updateCredentialsNonExpired(Long id, boolean credentialsNonExpired) {
        appUserRepository.updateCredentialsNonExpired(id, credentialsNonExpired);
    }
    @Transactional
    public void updateEnabled(String email, boolean enabled) {
        appUserRepository.updateEnabled(email, enabled);
    }
    @Transactional
    public void updateEnabledById(Long id, boolean enabled) {
        appUserRepository.updateEnabledById(id, enabled);
    }

    public AppUser findById(Long id) {
        return appUserRepository.findById(id).orElseThrow(() -> new IllegalStateException());
    }

    @Transactional
    public void updateEmail(Long id, String email) {
        appUserRepository.updateEmail(id, email);
    }

    public List<Book> getBooks(Long id) {
        return appUserRepository.getBooks(id);
    }
}
