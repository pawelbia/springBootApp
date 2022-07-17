package springboot.library.model.dto.read;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class RoleReadDto {

    private String name;

    private List<PermissionReadDto> permissions;
}
