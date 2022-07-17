package springboot.library.repository;

import static org.junit.jupiter.api.Assertions.*;
import org.assertj.core.api.Assertions;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import springboot.library.model.AppUser;
import springboot.library.model.Book;

import java.util.Optional;

@DataJpaTest
class BookRepositoryTest {

    @Autowired
    private BookRepository underTest;

    @Test
    void itShouldFindBookByTitle() {
        // Given
        String title = "title";
        Book book = new Book("title", "publishHouse", 350, "cover");
        // When
        underTest.save(book);

        // Then
        Optional<Book> optionalBook = underTest.findBookByTitle(title);
        Assertions.assertThat(optionalBook)
                .isPresent()
                .hasValueSatisfying(a -> {
                    Assertions.assertThat(a).isEqualTo(book);
                });
    }

    @Test
    void itShouldCountBooksByTitle() {
        // Given
        Book book1 = new Book("title", "publishHouse", 350, "cover");
        Book book2 = new Book("title", "publishHouse1", 370, "cover");

        // When
        underTest.save(book1);
        underTest.save(book2);

        // Then
        int bookCount = underTest.countBooksByTitle("title");
        Assertions.assertThat(bookCount).isEqualTo(2);
    }

    @Test
    void itShouldCountBooksByTitleAndBorrowedAtIsNull() {
        // Given
        Book book1 = new Book("title", "publishHouse", 350, "cover");
        Book book2 = new Book("title", "publishHouse1", 370, "cover");

        // When
        underTest.save(book1);
        underTest.save(book2);

        // Then
        int bookCount = underTest.countBooksByTitleAndBorrowedAtIsNull("title");
        Assertions.assertThat(bookCount).isEqualTo(2);
    }

    @Test
    void itShouldNotPutDataInBookWhenTitleIsNull() {
        // Given
        Book book1 = new Book(null, "publishHouse", 350, "cover");
        // When
        // Then
        Assertions.catchNullPointerException(() -> new Book(null, "publishHouse", 350, "cover"));
    }

}
























