package springboot.library.model.dto.write;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@Getter
@Builder
public class StudentCardDto {

    // TODO: regex
    @NotBlank
    private int albumNumber;

    @NotBlank
    private String groupNumber;

}
