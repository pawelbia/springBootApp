package springboot.library.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity(name="Book")
@Table(name="book")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Book {

    @Id
    @SequenceGenerator(
            name="book_sequence",
            sequenceName="book_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "book_sequence"
    )
    @Column(
            name="book_id",
            updatable = false
    )
    private Long id;

    @Column(
            name="title",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String title;

    @Column(
            name="publish_house",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String publishHouse;

    @Column(
            name="pages",
            nullable = false,
            columnDefinition = "BIGINT"
    )
    private int pages;

    @Pattern(
            regexp = "^\\d{4}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])$",
            message = "yyyy-mm-dd"
    )
    @Column(
            name="publication_date",
            columnDefinition = "TEXT"
    )
    private String publicationDate;

    @Column(
            name="print_type",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String coverPrintType;

    @ManyToOne(
            cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH, CascadeType.REMOVE}
    )
    @JoinColumn(
            name="app_user_id",
            referencedColumnName = "app_user_id",
            foreignKey = @ForeignKey(
                    name="app_user_book_fk"
            )
    )
    private AppUser appUser;

    @Column(
            name="borrowed_at",
            columnDefinition = "TIMESTAMP WITHOUT TIME ZONE"
    )
    private LocalDateTime borrowedAt;

    @Column(
            name="borrowed_end_at",
            columnDefinition = "TIMESTAMP WITHOUT TIME ZONE"
    )
    private LocalDateTime borrowedEndAt;

    @OneToMany(
            mappedBy = "book",
            cascade = {CascadeType.MERGE, CascadeType.REMOVE, CascadeType.REFRESH, CascadeType.DETACH},
            fetch = FetchType.LAZY
    )
    private List<BookAuthor> authors = new ArrayList<>();

    public Book(String title, String publishHouse, int pages, String coverPrintType) {
        this.title = title;
        this.publishHouse = publishHouse;
        this.pages = pages;
        this.coverPrintType = coverPrintType;
    }
}
