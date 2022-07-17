package springboot.library.model.role_permission;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;

@Entity(name="Role")
@Table(name="role")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Role {

    @Id
    @SequenceGenerator(
            name="role_sequence",
            sequenceName="role_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "role_sequence"
    )
    @Column(
            name="role_id",
            updatable = false
    )
    private Long id;

    @Pattern(regexp = "\\bROLE_\\S+", message = "Must be with prefix ROLE_")
    @Column(
            name="name",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String name;

    @OneToMany(
            cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.REFRESH, CascadeType.REMOVE},
            mappedBy = "role"
    )
    List<AppUserRole> appUserRoles = new ArrayList<>();

    @OneToMany(
            mappedBy = "role",
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.REFRESH, CascadeType.DETACH},
            fetch = FetchType.LAZY
    )
    private List<RolePermission> rolePermissions = new ArrayList<>();

    public Role(String name) {
        this.name = name;
    }

    public void addRolePermission(RolePermission rolePermission) {
        rolePermissions.add(rolePermission);
    }

}
