package springboot.library.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import springboot.library.model.AppUser;
import springboot.library.model.StudentCard;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class StudentCardRepositoryTest {

    @Autowired
    private StudentCardRepository underTest;

    @Autowired
    private AppUserRepository underTestA;



    @Test
    void itShouldFindStudentCardByAppUserId() {
        // Given
        AppUser appUser = new AppUser("email@gmail.com", "password", "FirstName", "LastName",
                true, true ,true, true);
        StudentCard studentCard1 = new StudentCard(11111, "213", appUser);
        // When
        underTestA.save(appUser);
        underTest.save(studentCard1);


        // Then
        Optional<StudentCard> optionalStudentCard = underTest.findStudentCardByAppUserId(appUser.getId());
        Assertions.assertThat(optionalStudentCard)
                .isPresent()
                .hasValueSatisfying(a -> {
                    Assertions.assertThat(a).isEqualTo(studentCard1);
                });
    }
}