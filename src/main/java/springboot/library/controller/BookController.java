package springboot.library.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import springboot.library.model.dto.read.BookReadDto;
import springboot.library.model.dto.read.BookTitleDto;
import springboot.library.service.BookService;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static springboot.library.model.mapper.read.BookReadDtoMapper.mapBookToBookReadDto;
import static springboot.library.model.mapper.read.BookReadDtoMapper.mapBookToBookReadDtoList;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1")
public class BookController {

    private final BookService bookService;

    @GetMapping("/books")
    public List<BookReadDto> getBooks() {
        return mapBookToBookReadDtoList(bookService.getBooks());
    }

    @GetMapping("/books/{id}")
    public BookReadDto getBookDetails(@PathVariable(value = "id") Long id) {
        return mapBookToBookReadDto(bookService.findById(id));
    }

    @GetMapping("/books/book-quantity")
    public int numberOfCopies(@RequestBody BookTitleDto bookTitleDto) {
        return bookService.numberOfCopies(bookTitleDto.getTitle());

    }

    @GetMapping("/books/available-copies")
    public int availableNumberOfCopies(@RequestBody BookTitleDto bookTitleDto) {
        return bookService.availableNumberOfCopies(bookTitleDto.getTitle());
    }

    @GetMapping("/books/{bookId}/real-time")
    public String getRealTimeDifference(@PathVariable(value = "bookId") Long bookId) {
        return bookService.calculateCurrentTimeDifference(bookId);
    }

    @GetMapping("/books/{bookId}/borrowed-end")
    public String getBorrowedEntTime(@PathVariable(value = "bookId") Long bookId) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return bookService.getBorroweEndAt(bookId).format(formatter);
    }

    @PutMapping("/users/{appUserId}/books/{bookId}/add")
    public void addBookToAppUser(@PathVariable(value = "bookId") Long bookId,
                                 @PathVariable(value = "appUserId") Long appUserId) {
        bookService.addBookToAppUser(bookId, appUserId);
    }

    @PutMapping("books/{bookId}/remove")
    public BigDecimal removeBookFromAppUserAndCalculate(@PathVariable(value = "bookId") Long bookId) {
        return bookService.calculateTimeDifferenceInDays(bookId);
    }

}
