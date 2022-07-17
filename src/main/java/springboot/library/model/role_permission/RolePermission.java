package springboot.library.model.role_permission;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity(name="RolePermission")
@Table(name="role_permission")
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class RolePermission {

    @EmbeddedId
    private RolePermissionId id;

    @ManyToOne
    @MapsId("permissionId")
    @JoinColumn(
            name="permission_id",
            foreignKey = @ForeignKey(
                    name="rolePermission_permission_id_fk"
            )
    )
    private Permission permission;

    @ManyToOne
    @MapsId("roleId")
    @JoinColumn(
            name="role_id",
            foreignKey = @ForeignKey(
                    name="rolePermission_role_id_fk"
            )
    )
    private Role role;

    public RolePermission(Long roleId, Long permissionId, Role role, Permission permission) {
        this.id = new RolePermissionId(roleId, permissionId);
        this.role = role;
        this.permission = permission;
    }
}
