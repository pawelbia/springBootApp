package springboot.library.repository.role_permission_rep;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import springboot.library.model.role_permission.Permission;
import springboot.library.model.role_permission.Role;
import springboot.library.model.role_permission.RolePermission;
import springboot.library.model.role_permission.RolePermissionId;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface RolePermissionRepository extends CrudRepository<RolePermission, RolePermissionId> {

    @Transactional
    @Query("SELECT r " + "FROM RolePermission r " + "WHERE r.role=?1 AND r.permission=?2")
    RolePermission findRolePermissionByRoleAndPermission(Role role, Permission permission);

}
