package springboot.library.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import springboot.library.model.dto.read.PermissionReadDto;
import springboot.library.model.dto.read.RoleReadDto;
import springboot.library.service.role_permisson_ser.PermissionService;
import springboot.library.service.role_permisson_ser.RolePermissionService;
import springboot.library.service.role_permisson_ser.RoleService;

import java.util.List;

import static springboot.library.model.mapper.read.PermissionReadDtoMapper.mapPermissionListToPermissionReadDtoList;
import static springboot.library.model.mapper.read.PermissionReadDtoMapper.mapPermissionToPermissionReadDto;
import static springboot.library.model.mapper.read.RoleReadDtoMapper.mapRoleListToRoleReadDtoList;
import static springboot.library.model.mapper.read.RoleReadDtoMapper.mapRoleToRoleReadDto;

@RestController
@RequestMapping("api/v1/app-management")
@AllArgsConstructor
public class AdminController {

    private final RoleService roleService;
    private final PermissionService permissionService;

    private final RolePermissionService rolePermissionService;

    @GetMapping("/roles")
    public List<RoleReadDto> getRoles() {
        return mapRoleListToRoleReadDtoList(roleService.getRoles());
    }

    @GetMapping("/roles/{id}")
    public RoleReadDto getRole(@PathVariable(value = "id") Long id) {
        return mapRoleToRoleReadDto(roleService.getRole(id));
    }

    @GetMapping("/permissions")
    public List<PermissionReadDto> getPermissions() {
        return mapPermissionListToPermissionReadDtoList(permissionService.getPermissions());
    }

    @GetMapping("/permissions/{id}")
    public PermissionReadDto getPermission(@PathVariable(value = "id") Long id) {
        return mapPermissionToPermissionReadDto(permissionService.getPermission(id));
    }

    @PutMapping("/roles/{roleId}/permissions/{permissionId}/add")
    public void addPermissionToRole(@PathVariable(value = "roleId") Long roleId,
                                    @PathVariable(value = "permissionId") Long permissionId) {
        rolePermissionService.addPermissionToRole(roleService.getRole(roleId), permissionService.getPermission(permissionId));
    }

    @DeleteMapping("/roles/{roleId}/permissions/{permissionId}/remove")
    public void removePermissionFromRole(@PathVariable(value = "roleId") Long roleId,
                                         @PathVariable(value = "permissionId") Long permissionId) {
        rolePermissionService.removePermissionFromRole(roleService.getRole(roleId), permissionService.getPermission(permissionId));
    }

    // TODO: CreatePermission, CreateRole, UpdatePermission, UpdateRole, DeletePermission, DeleteRole

}
