package springboot.library.repository.role_permission_rep;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import springboot.library.model.AppUser;
import springboot.library.model.role_permission.AppUserRole;
import springboot.library.model.role_permission.AppUserRoleId;
import springboot.library.model.role_permission.Role;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@Transactional
public interface AppUserRoleRepository extends CrudRepository<AppUserRole, AppUserRoleId> {

    @Transactional
    @Query("SELECT a " + "FROM AppUserRole a " + " WHERE a.appUser=?1 AND a.role=?2")
    Optional<AppUserRole> findAppUserRoleByAppUserAndRole(AppUser appUser, Role role);
}
