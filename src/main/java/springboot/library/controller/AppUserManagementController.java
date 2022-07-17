package springboot.library.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import springboot.library.model.Book;
import springboot.library.model.dto.read.BookReadDto;
import springboot.library.service.AppUserService;

import java.util.List;

import static springboot.library.model.mapper.read.BookReadDtoMapper.mapBookToBookReadDtoList;

@RestController
@RequestMapping("api/v1/user-management")
@AllArgsConstructor
public class AppUserManagementController {

    private AppUserService appUserService;

    @PutMapping("/users/{id}/account/non-expired")
    public void updateAccountNonExpired(@PathVariable(value = "id") Long id, @RequestParam boolean set) {
        appUserService.updateAccountNonExpired(id, set);
    }

    @PutMapping("/users/{id}/account/non-locked")
    public void updateAccountNonLocked(@PathVariable(value = "id") Long id, @RequestParam boolean set) {
        appUserService.updateAccountNonExpired(id, set);
    }

    @PutMapping("/users/{id}/credentials/non-expired/")
    public void updateCredentialsNonExpired(@PathVariable(value = "id") Long id, @RequestParam boolean set) {
        appUserService.updateCredentialsNonExpired(id, set);
    }

    @GetMapping("/users/{id}/books")
    public List<BookReadDto> showBorrowedBooks(@PathVariable(value = "id") Long id) {
        return mapBookToBookReadDtoList(appUserService.getBooks(id));
    }
}