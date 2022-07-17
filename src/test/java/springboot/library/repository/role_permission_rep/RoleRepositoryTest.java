package springboot.library.repository.role_permission_rep;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import springboot.library.model.role_permission.Role;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class RoleRepositoryTest {

    @Autowired
    private RoleRepository underTest;

    @Test
    void itShouldFindRoleByName() {
        // Given
        String roleName = "ROLE_USER";
        Role role = new Role(roleName);
        // When
        underTest.save(role);

        // Then
        Optional<Role> optionalRole = underTest.findRoleByName(roleName);
        Assertions.assertThat(optionalRole)
                .isPresent();
    }
}