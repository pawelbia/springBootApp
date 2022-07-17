package springboot.library.service.role_permisson_ser;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import springboot.library.model.Author;
import springboot.library.model.Book;
import springboot.library.model.BookAuthor;
import springboot.library.repository.role_permission_rep.BookAuthorRepository;

import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class BookAuthorService {

    private final BookAuthorRepository bookAuthorRepository;

    public void deleteBookAuthor(Book book, Author author) {
        BookAuthor bookAuthor = bookAuthorRepository.findBookAuthorByBookAndAuthor(book, author);
        bookAuthorRepository.delete(bookAuthor);
    }

    public void addAuthorToBook(Book book, Author author) {
        BookAuthor bookAuthor = new BookAuthor(book.getId(), author.getId(), book, author);
        bookAuthorRepository.save(bookAuthor);
    }

    public List<BookAuthor> addAuthorsToBook(Book book, List<Author> authors) {
        List<BookAuthor> collect = authors.stream().
                map(author -> new BookAuthor(book.getId(), author.getId(), book, author))
                .collect(Collectors.toList());
        bookAuthorRepository.saveAll(collect);
        return collect;
    }

    public void save(BookAuthor bookAuthor) {
        bookAuthorRepository.save(bookAuthor);
    }
}
