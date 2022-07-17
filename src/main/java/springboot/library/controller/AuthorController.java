package springboot.library.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import springboot.library.model.Author;
import springboot.library.model.dto.AuthorDto;
import springboot.library.model.dto.read.AuthorReadDto;
import springboot.library.service.AuthorService;


import java.util.List;

import static springboot.library.model.mapper.AuthorDtoMapper.*;
import static springboot.library.model.mapper.read.AuthorReadDtoMapper.mapAuthorToAuthorReadDto;
import static springboot.library.model.mapper.read.AuthorReadDtoMapper.mapAuthorToAuthorReadDtoList;


@RestController
@RequestMapping("api/v1")
@AllArgsConstructor
public class AuthorController {

    private final AuthorService authorService;


    @GetMapping("/authors")
    public List<AuthorReadDto> getAuthorsAndBooks() {
        return mapAuthorToAuthorReadDtoList(authorService.getAuthors());
    }

    @GetMapping("/authors/{id}")
    public AuthorDto getAuthor(@PathVariable(value = "id") Long id) {
        return mapAuthorToAuthorDto(authorService.findById(id));
    }

    @GetMapping("/authors/details/{lastName}")
    public List<AuthorDto> getAuthors(@PathVariable String lastName) {
        return mapAuthorListToAuthorDtoList(authorService.findByLastName(lastName));
    }


    @GetMapping("/authors/{id}/books")
    public AuthorReadDto getAuthorAndHisBooks(@PathVariable(value = "id") Long id) {
        return mapAuthorToAuthorReadDto(authorService.findById(id));
    }

    @PostMapping("authors/create-author")
    public Author createAuthor(@RequestBody AuthorDto authorDto) {
        return authorService.save(mapAuthorDtoToAuthor(authorDto));
    }


    @PostMapping("/authors/full-name")
    public String authorExists(@RequestParam String firstName, @RequestParam String lastName) {
        if(authorService.existsAuthor(firstName, lastName)) {
            return "Author already exists";
        } else {
            Author author = new Author(firstName, lastName);
            authorService.save(author);
            return "Author has beed created";
        }
    }

}
