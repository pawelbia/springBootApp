package springboot.library.model.mapper.read;

import springboot.library.model.dto.read.PermissionReadDto;
import springboot.library.model.role_permission.Permission;

import java.util.List;
import java.util.stream.Collectors;

public class PermissionReadDtoMapper {

    public static PermissionReadDto mapPermissionToPermissionReadDto(Permission permission) {
        return PermissionReadDto.builder()
                .name(permission.getName())
                .description(permission.getDescription())
                .build();
    }

    public static List<PermissionReadDto> mapPermissionListToPermissionReadDtoList(List<Permission> permissions) {
        return permissions.stream()
                .map(permission -> PermissionReadDto.builder()
                        .name(permission.getName())
                        .description(permission.getDescription())
                        .build())
                .collect(Collectors.toList());
    }
}
