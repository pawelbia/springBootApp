package springboot.library.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import springboot.library.model.Author;

import java.util.Optional;


@DataJpaTest
class AuthorRepositoryTest {

    @Autowired
    private AuthorRepository underTest;

    @Test
    void itShouldSaveAuthor() {
        // Given
        Author author = new Author("firstName", "lastName");
        // When
        underTest.save(author);

        // Then
        Optional<Author> optionalAuthor = underTest.findById(author.getId());
        Assertions.assertThat(optionalAuthor)
                .isPresent()
                .hasValueSatisfying(a ->
                {
                    Assertions.assertThat(a).isEqualTo(author);
                });
    }

    // TODO: 2 more tests with null values in firstName and lastName

    @Test
    void itShouldExistsAuthorByFirstNameAndLastName() {
        // Given
        Author author = new Author("firstName", "lastName");

        // When
        underTest.save(author);

        // Then
        boolean b = underTest.existsAuthorByFirstNameAndLastName("firstName", "lastName");

        Assertions.assertThat(b)
                .isTrue();
    }
}