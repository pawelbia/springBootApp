package springboot.library.model.dto.read;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PermissionReadDto {

    private String name;

    private String description;
}
