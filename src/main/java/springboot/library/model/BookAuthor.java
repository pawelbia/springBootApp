package springboot.library.model;

import lombok.*;


import javax.persistence.*;

@Entity(name="BookAuthor")
@Table(name="book_author")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class BookAuthor {

    @EmbeddedId
    private BookAuthorId id;

    @ManyToOne
    @MapsId("bookId")
    @JoinColumn(
            name="book_id",
            foreignKey = @ForeignKey(
                    name="book_author_book_id_fk"
            )
    )
    private Book book;

    @ManyToOne
    @MapsId("authorId")
    @JoinColumn(
            name="author_id",
            foreignKey = @ForeignKey(
                    name="book_author_author_id_fk"
            )
    )
    private Author author;

    public BookAuthor(Long bookId, Long authorId, Book book, Author author) {
        this.id = new BookAuthorId(bookId, authorId);
        this.book = book;
        this.author = author;
    }

}
