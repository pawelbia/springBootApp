package springboot.library.model.role_permission;

import lombok.*;
import jakarta.persistence.*;
import java.io.Serializable;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AppUserRoleId implements Serializable {

    @Column(name="app_user_id")
    private Long appUserId;

    @Column(name="role_id")
    private Long roleId;

}
