package springboot.library.service.role_permisson_ser;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import springboot.library.model.role_permission.Role;
import springboot.library.repository.role_permission_rep.RoleRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;

    public Role findRoleByName(String name) {
        return roleRepository.findRoleByName(name).orElseThrow(() -> new IllegalStateException("role not found"));
    }

    public List<Role> getRoles() {
       return roleRepository.findAll();
    }

    public Role getRole(Long id) {
        return roleRepository.findById(id).orElseThrow(() -> new IllegalStateException("role not found"));
    }
}
