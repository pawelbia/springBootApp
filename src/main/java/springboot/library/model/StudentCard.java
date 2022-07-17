package springboot.library.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Digits;


@Entity(name="StudentCard")
@Table(name="student_card",
        uniqueConstraints = {
        @UniqueConstraint(
                name="student_id_card_album_number_unique",
                columnNames = "album_number"
        )
        })
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class StudentCard {

    @Id
    @SequenceGenerator(
            name="student_id_card_sequence",
            sequenceName="student_id_card_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "student_id_card_sequence"
    )
    @Column(name="id",
            updatable = false
    )
    private Long id;

    @Digits(integer=5, fraction=0, message="Invalid Album Number")
    @Column(name="album_number",
            nullable = false,
            columnDefinition = "BIGINT"
    )
    private int albumNumber;

    @Column(name="group_number",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String groupNumber;

    @OneToOne(
            cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH, CascadeType.REMOVE},
            fetch = FetchType.LAZY
    )
    @JoinColumn(
            name = "app_user_id",
            nullable = false,
            referencedColumnName = "app_user_id",
            foreignKey = @ForeignKey(
                    name="app_user_student_id_card_fk"
            )


    )
    private AppUser appUser;

    public StudentCard(int albumNumber, String groupNumber, AppUser appUser) {
        this.albumNumber = albumNumber;
        this.groupNumber = groupNumber;
        this.appUser = appUser;
    }

    public StudentCard(int albumNumber, String groupNumber) {
        this.albumNumber = albumNumber;
        this.groupNumber = groupNumber;
    }
}
