package springboot.library.model.role_permission;

import lombok.*;
import springboot.library.model.AppUser;

import javax.persistence.*;

@Entity(name="AppUserRole")
@Table(name="app_user_Role")
@NoArgsConstructor
@Getter
@Setter
public class AppUserRole {

    @EmbeddedId
    private AppUserRoleId id;

    @ManyToOne
    @MapsId("appUserId")
    @JoinColumn(
            name="app_user_id",
            foreignKey = @ForeignKey(
                    name="appUserRole_appUser_id_fk"
            )
    )
    private AppUser appUser;

    @ManyToOne
    @MapsId("roleId")
    @JoinColumn(
            name="role_id",
            foreignKey = @ForeignKey(
                    name="appUserRole_role_id_fk"
            )
    )
    private Role role;

    public AppUserRole(Long appUserId, Long roleId, AppUser appUser, Role role) {
        this.id = new AppUserRoleId(appUserId, roleId);
        this.appUser = appUser;
        this.role = role;
    }


}
