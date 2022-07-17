package springboot.library.controller;

import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.*;
import springboot.library.model.Book;
import springboot.library.model.dto.BookDto;
import springboot.library.service.AuthorService;
import springboot.library.service.BookService;
import springboot.library.service.role_permisson_ser.BookAuthorService;

import static springboot.library.model.mapper.write.BookDtoMapper.mapBookDtoToBook;


@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class BookAuthorController {

    private final AuthorService authorService;
    private final BookAuthorService bookAuthorService;

    private final BookService bookService;



    @PostMapping("/create-book/authors/{authorId}/add")
    public void createBookAndAddAuthor(@PathVariable(value = "authorId") Long id, @RequestBody BookDto bookDto) {
        Book book = bookService.save(mapBookDtoToBook(bookDto));
        bookAuthorService.addAuthorToBook(book, authorService.findById(id));
    }

    @PostMapping("/create-book/authors/{authorId}/add/{bookId}")
    public void addAuthorToBook(@PathVariable(value = "authorId") Long authorId, @PathVariable(value = "bookId") Long bookId) {
        bookAuthorService.addAuthorToBook(bookService.findById(bookId), authorService.findById(authorId));
    }


}


