package springboot.library.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import springboot.library.model.AppUser;

import java.util.Optional;




@DataJpaTest
class AppUserRepositoryTest {

    @Autowired
    private AppUserRepository underTest;



    @Test
    void itShouldSaveAppUser() {
        // Given
        AppUser appUser = new AppUser("email@gmail.com", "password", "FirstName", "LastName",
                true, true ,true, true);

        // When
        underTest.save(appUser);

        // Then
        Optional<AppUser> optionalAppUser = underTest.findById(appUser.getId());
        Assertions.assertThat(optionalAppUser)
                .isPresent()
                .hasValueSatisfying(a -> {
                    Assertions.assertThat(a).isEqualTo(appUser);
                });
    }

    @Test
    void itShouldNotPutDataInAppUserWhenEmailIsNull() {
        // Given
        // When
        // Then
        Assertions.catchNullPointerException(() -> new AppUser(null, "password", "firstName", "LastName",
                true, true ,true, true));
    }
    @Test
    void itShouldNotPutDataInAppUserWhenPasswordIsNull() {
        // Given
        // When
        // Then
        Assertions.catchNullPointerException(() -> new AppUser("email@gmail.com", null, "firstName", "LastName",
                true, true ,true, true));
    }
    @Test
    void itShouldNotPutDataInAppUserWhenFirstNameIsNull() {
        // Given
        // When
        // Then
        Assertions.catchNullPointerException(() -> new AppUser("email@gmail.com", "password", null, "LastName",
                true, true ,true, true));
    }
    @Test
    void itShouldNotPutDataInAppUserWhenLastNameIsNull() {
        // Given
        // When
        // Then
        Assertions.catchNullPointerException(() -> new AppUser("email@gmail.com", "password", "firstName", null,
                true, true ,true, true));
    }

    @Test
    void itShouldFindAppUserByEmail() {
        // Given
        String email = "email@gmail.com";
        AppUser appUser = new AppUser("email@gmail.com", "password", "FirstName", "LastName",
                true, true ,true, true);

        // When
        underTest.save(appUser);

        // Then
        Optional<AppUser> optionalAppUser = underTest.findAppUserByEmail(email);
        Assertions.assertThat(optionalAppUser)
                .isPresent()
                .hasValueSatisfying(a -> {
                    Assertions.assertThat(a).isEqualTo(a);
                });
    }


    @Test
    void itShouldExistsAppUserByEmailTrue() {
        // Given
        String email = "email@gmail.com";
        AppUser appUser = new AppUser("email@gmail.com", "password", "FirstName", "LastName",
                true, true ,true, true);
        // When
        underTest.save(appUser);

        // Then
        boolean isPresent = underTest.existsAppUserByEmail(email);
        Assertions.assertThat(isPresent)
                .isTrue();
    }

    @Test
    void itShouldExistsAppUserByEmailFalse() {
        // Given
        String email = "email.email@gmail.com";
        AppUser appUser = new AppUser("email@gmail.com", "password", "FirstName", "LastName",
                true, true ,true, true);
        // When
        underTest.save(appUser);

        // Then
        boolean isPresent = underTest.existsAppUserByEmail(email);
        Assertions.assertThat(isPresent)
                .isFalse();
    }

}


























