package springboot.library.registration;

import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


@AllArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
public class RegistrationDto {

    // TODO: improve regexes
  /*  @Pattern(
            regexp = "/^[a-zA-Z0-9.!#$%&'*+\\/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$/",
            message = "Email is not  valid"
    )*/
    private final String email;

  /*  @Pattern(
            regexp = "/^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$/",
            message = "Minimum eight characters, at least one letter and one number:")*/
    private final String password;

  /*  @Pattern(
            regexp = "/^[A-Z][a-z0-9_-]{3,19}$/",
            message = "First letter must be a capital"
    )*/
    private final String firstName;

  /*  @Pattern(
            regexp = "/^[A-Z][a-z0-9_-]{3,19}$/",
            message = "First letter must be a capital"
    )*/
    private final String lastName;


}
