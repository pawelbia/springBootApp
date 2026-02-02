package springboot.library.model.dto.write;

import lombok.Getter;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotBlank;

@NoArgsConstructor
@Getter
public class AppUserPasswordDto {

    @NotBlank
    private String password;

}
