package springboot.library.model.role_permission;

import lombok.*;

import jakarta.persistence.*;
import java.io.Serializable;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RolePermissionId implements Serializable {

    @Column(name="permission_id")
    private Long permissionId;

    @Column(name="role_id")
    private Long roleId;

}
