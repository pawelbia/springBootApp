package springboot.library.model.dto.write;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@Getter
public class AppUserEmailDto {

    //Validation
    @NotBlank
    private String email;
}
