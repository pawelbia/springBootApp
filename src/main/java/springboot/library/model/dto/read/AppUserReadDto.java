package springboot.library.model.dto.read;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AppUserReadDto {

    private String firstName;

    private String lastName;

}
