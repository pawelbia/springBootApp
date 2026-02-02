package springboot.library.model.dto.write;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotBlank;

@NoArgsConstructor
@Getter
public class AppUserEmailDto {

    //Validation
    @NotBlank
    private String email;
}
