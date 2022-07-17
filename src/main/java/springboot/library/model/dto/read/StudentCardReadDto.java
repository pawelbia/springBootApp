package springboot.library.model.dto.read;

import lombok.Builder;
import lombok.Getter;
import springboot.library.model.dto.read.AppUserReadDto;

@Getter
@Builder
public class StudentCardReadDto {

    private int albumNumber;

    private String groupNumber;

    private AppUserReadDto appUserReadDto;
}
