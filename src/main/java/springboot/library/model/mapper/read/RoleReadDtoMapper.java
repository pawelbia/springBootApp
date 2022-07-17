package springboot.library.model.mapper.read;

import springboot.library.model.dto.read.RoleReadDto;
import springboot.library.model.role_permission.Role;

import java.util.List;
import java.util.stream.Collectors;

import static springboot.library.model.mapper.read.PermissionReadDtoMapper.mapPermissionListToPermissionReadDtoList;

public class RoleReadDtoMapper {

    public static RoleReadDto mapRoleToRoleReadDto(Role role) {
        return RoleReadDto.builder()
                .name(role.getName())
                .permissions(mapPermissionListToPermissionReadDtoList(role.getRolePermissions().stream()
                        .map(rolePermission -> rolePermission.getPermission()).collect(Collectors.toList())))
                .build();
    }

    public static List<RoleReadDto> mapRoleListToRoleReadDtoList(List<Role> roles) {
        return roles.stream()
                .map(role -> RoleReadDto.builder()
                        .name(role.getName())
                        .permissions(mapPermissionListToPermissionReadDtoList(role.getRolePermissions().stream()
                                .map(rolePermission -> rolePermission.getPermission()).collect(Collectors.toList())))
                        .build())
                .collect(Collectors.toList());
    }
}
