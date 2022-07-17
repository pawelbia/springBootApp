package springboot.library.service.role_permisson_ser;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import springboot.library.model.role_permission.Permission;
import springboot.library.model.role_permission.Role;
import springboot.library.model.role_permission.RolePermission;
import springboot.library.repository.role_permission_rep.RolePermissionRepository;

@Service
@AllArgsConstructor
public class RolePermissionService {

    private final RolePermissionRepository rolePermissionRepository;

    public RolePermission findRolePermissionByRoleAndPermission(Role role, Permission permission) {
        return rolePermissionRepository.findRolePermissionByRoleAndPermission(role, permission);
    }

    public void addPermissionToRole(Role role, Permission permission) {
        RolePermission rolePermission = new RolePermission(role.getId(), permission.getId(), role, permission);
        rolePermissionRepository.save(rolePermission);
    }
    public void removePermissionFromRole(Role role, Permission permission) {
        RolePermission rolePermission = rolePermissionRepository.findRolePermissionByRoleAndPermission(role, permission);
        rolePermissionRepository.delete(rolePermission);
    }
}

