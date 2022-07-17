package springboot.library.model.role_permission;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;

@Entity(name="Permission")
@Table(name="permission")
@NoArgsConstructor
@Getter
@Setter
public class Permission {

    @Id
    @SequenceGenerator(
            name="permission_sequence",
            sequenceName="permission_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "permission_sequence"
    )
    @Column(
            name="permission_id",
            updatable = false
    )
    private Long id;

    @Column(
            name="name",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String name;

    @Column(
            name="description",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String description;

    @OneToMany(
            cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.REFRESH, CascadeType.REMOVE},
            mappedBy = "permission"
    )
    List<RolePermission> rolePermissions = new ArrayList<>();
    public Permission(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
