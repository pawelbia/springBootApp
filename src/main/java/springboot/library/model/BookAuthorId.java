package springboot.library.model;

import lombok.*;

import jakarta.persistence.*;
import java.io.Serializable;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BookAuthorId implements Serializable {

    @Column(name="book_id")
    private Long bookId;

    @Column(name="author_id")
    private Long authorId;
}
