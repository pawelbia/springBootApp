package springboot.library.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class AuthorDto {

    private String firstName;

    private String lastName;
}
