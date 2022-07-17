package springboot.library.service.role_permisson_ser;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import springboot.library.model.AppUser;
import springboot.library.model.role_permission.AppUserRole;

import springboot.library.model.role_permission.Role;
import springboot.library.repository.role_permission_rep.AppUserRoleRepository;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
public class AppUserRoleService {

    private final AppUserRoleRepository appUserRoleRepository;


    @Transactional
    public void removeRoleFromAppUser(AppUser appUser, Role role) {
        AppUserRole appUserRole = appUserRoleRepository.findAppUserRoleByAppUserAndRole(appUser, role)
                        .orElseThrow(() -> new IllegalStateException("user not found"));
        appUserRoleRepository.delete(appUserRole);
    }

    @Transactional
    public void addRoleToAppUser(AppUser appUser, Role role) {
        AppUserRole appUserRole = new AppUserRole(appUser.getId(), role.getId(), appUser, role);
        appUserRoleRepository.save(appUserRole);
    }

}
