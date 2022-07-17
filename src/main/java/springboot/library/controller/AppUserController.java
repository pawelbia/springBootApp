package springboot.library.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import springboot.library.model.dto.read.BookReadDetailsDto;
import springboot.library.model.dto.write.AppUserPasswordDto;
import springboot.library.service.AppUserService;

import java.util.List;

import static springboot.library.model.mapper.read.BookReadDetailsDtoMapper.mapBookToBookReadDetailsDto;


@RestController
@RequestMapping("api/v1")
@AllArgsConstructor
public class AppUserController {

    private final AppUserService appUserService;

    @GetMapping("/users/{id}/books")
    public List<BookReadDetailsDto> getBorrowedBooks(@PathVariable(value = "id") Long id) {
        return mapBookToBookReadDetailsDto(appUserService.findById(id).getBooks());
    }

    @PutMapping("/users/{id}/user-details/password")
    public void updatePassword(@PathVariable(value="id") Long id, @RequestBody AppUserPasswordDto appUserPasswordDto) {
        appUserService.updatePassword(id, appUserPasswordDto);
    }


}
