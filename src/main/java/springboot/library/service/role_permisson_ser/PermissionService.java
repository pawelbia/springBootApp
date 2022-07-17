package springboot.library.service.role_permisson_ser;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import springboot.library.model.role_permission.Permission;
import springboot.library.repository.role_permission_rep.PermissionRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class PermissionService {

    private final PermissionRepository permissionRepository;

    public List<Permission> getPermissions() {
        return permissionRepository.findAll();
    }

    public Permission getPermission(Long id) {
        return permissionRepository.findById(id).orElseThrow(() -> new IllegalStateException("permission not found"));
    }
}
