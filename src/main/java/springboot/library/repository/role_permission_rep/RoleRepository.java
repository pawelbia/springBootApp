package springboot.library.repository.role_permission_rep;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import springboot.library.model.role_permission.Role;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@Transactional
public interface RoleRepository extends JpaRepository<Role, Long> {

    @Transactional
    @Query("SELECT r " + "FROM Role r " + "WHERE r.name=?1")
    Optional<Role> findRoleByName(String name);
}
