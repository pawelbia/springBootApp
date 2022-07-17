package springboot.library.model;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import springboot.library.model.role_permission.AppUserRole;
import springboot.library.registration.token.ConfirmationEmailToken;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Entity(name="AppUser")
@Table(name="app_user",
        uniqueConstraints = {
        @UniqueConstraint(name = "app_user_email_unique", columnNames = "email")
        }
)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class AppUser implements UserDetails {

    @Id
    @SequenceGenerator(
            name="app_user_sequence",
            sequenceName="app_user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "app_user_sequence"
    )
    @Column(
            name="app_user_id",
            updatable = false
    )
    private Long id;

    @Column(
            name="email",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String email;

    @Column(
            name="password",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String password;

    @Column(
            name="first_name",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String firstName;

    @Column(
            name="last_name",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String lastName;

    @Column(
            name="account_non_Expired",
            nullable = false,
            columnDefinition = "BOOLEAN"
    )
    private boolean accountNonExpired;

    @Column(
            name="account_non_Locked",
            nullable = false,
            columnDefinition = "BOOLEAN"
    )
    private boolean accountNonLocked;

    @Column(
            name="credentials_non_Expired",
            nullable = false,
            columnDefinition = "BOOLEAN"
    )
    private boolean credentialsNonExpired;

    @Column(
            name="enabled",
            nullable = false,
            columnDefinition = "BOOLEAN"
    )
    private boolean enabled;

    @OneToOne(
            mappedBy = "appUser",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            orphanRemoval = true
    )
    private StudentCard studentCard;


    @OneToMany(
            mappedBy = "appUser",
            cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.REFRESH, CascadeType.REMOVE},
            fetch = FetchType.LAZY
    )
    private List<Book> books = new ArrayList<>();

    @OneToMany(
            mappedBy = "appUser",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<AppUserRole> appUserRoles = new ArrayList<>();

    @OneToMany(
            mappedBy = "appUser",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<ConfirmationEmailToken> confirmationEmailTokens = new ArrayList<>();



    public AppUser(String email, String password, String firstName, String lastName,
                   boolean accountNonExpired, boolean accountNonLocked, boolean credentialsNonExpired, boolean enabled) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.accountNonExpired = accountNonExpired;
        this.accountNonLocked = accountNonLocked;
        this.credentialsNonExpired = credentialsNonExpired;
        this.enabled = enabled;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return appUserRoles.stream().
                map(appUserRole -> new SimpleGrantedAuthority(appUserRole.getRole().getName())).collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public void addAppUserRole(AppUserRole appUserRole) {
        appUserRoles.add(appUserRole);
    }

}
