package springboot.library.registration.token;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;
import springboot.library.model.AppUser;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name="ConfirmationEmailToken")
@Table(name="confirmation_email_token")
@Getter
@Setter
@Service
@NoArgsConstructor
public class ConfirmationEmailToken {

    @Id
    @SequenceGenerator(
            name="confirmation_email_token_sequence",
            sequenceName="confirmation_email_token_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "confirmation_email_token_sequence"
    )
    @Column(
            name="confirmation_email_token",
            updatable = false
    )
    private Long id;

    @Column(
            name="token",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String token;

    @Column(
            name="expires_at",
            nullable = false,
            columnDefinition = "TIMESTAMP WITHOUT TIME ZONE"
    )
    private LocalDateTime expiresAt;

    @Column(
            name="confirmed_at",
            nullable = false,
            columnDefinition = "TIMESTAMP WITHOUT TIME ZONE"
    )
    private LocalDateTime confirmedAt;

    @ManyToOne(
            cascade = CascadeType.ALL
    )
    @JoinColumn(
            name="app_user_id",
            nullable = false,
            referencedColumnName = "app_user_id",
            foreignKey = @ForeignKey(
                    name="app_user_confirmation_email_token_fk"

            )
    )
    private AppUser appUser;

    public ConfirmationEmailToken(String token, LocalDateTime expiresAt, LocalDateTime confirmedAt, AppUser appUser) {
        this.token = token;
        this.expiresAt = expiresAt;
        this.confirmedAt = confirmedAt;
        this.appUser = appUser;
    }
}
