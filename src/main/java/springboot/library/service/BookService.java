package springboot.library.service;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import springboot.library.model.Book;
import springboot.library.repository.BookRepository;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.List;
import java.util.Locale;

@Service
@ConfigurationProperties(prefix="book.properties")
public class BookService {

    private int borrowedAtSize = 10;
    private int borrowedEndSize = 10;

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void deleteBook(Book book) {
        bookRepository.delete(book);
    }

    public Book findById(Long id) {
        return bookRepository.findById(id).orElseThrow(() -> new IllegalStateException("book not found"));
    }

    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    public Book save(Book book) {
        return bookRepository.save(book);
    }

    public int numberOfCopies(String title) {
        return bookRepository.countBooksByTitle(title);
    }

    public int availableNumberOfCopies(String title) {
        return bookRepository.countBooksByTitleAndBorrowedAtIsNull(title);
    }

    public void addBookToAppUser(Long bookId, Long appUserId) {
        bookRepository.addBookToAppUserNative(bookId, appUserId, LocalDateTime.now(), LocalDateTime.now().plusDays(30));
    }

    @Transactional
    public BigDecimal calculateTimeDifferenceInDays(Long bookId) {

        Book book = bookRepository.findById(bookId).orElseThrow(() -> new IllegalStateException("user not found"));
        LocalDateTime borrowedTime = book.getBorrowedAt();
        LocalDateTime borrowedEndTime = book.getBorrowedEndAt();
        Period period = Period.between(borrowedTime.toLocalDate(), borrowedEndTime.toLocalDate());

        bookRepository.removeBookFromAppUserNative(bookId);

        boolean isNegative = period.isNegative();
        if(isNegative){
            int days = period.getDays();
            int scale = 2;
            BigDecimal money = new BigDecimal("2");
            return money.multiply(new BigDecimal(days)).setScale(scale, RoundingMode.HALF_UP);
        } else {
            return BigDecimal.ZERO;
        }
    }
    public String calculateCurrentTimeDifference(Long bookId) {
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new IllegalStateException("user not found"));
        LocalDateTime borrowedTime = book.getBorrowedAt();;
        Duration timeChange = Duration.between(borrowedTime, LocalDateTime.now());
        return String.format("Days: %b Hours: %b Mintues: %b", timeChange.toDays(), timeChange.toHours(), timeChange.toMinutes());
    }
    public LocalDateTime getBorroweEndAt(Long bookId) {
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new IllegalStateException("user not found"));
        return book.getBorrowedEndAt();
    }

    // TODO: create mappings for this methods

    public Iterable<Book> sortBooksByBorrowedAtLimitDesc() {
        Sort sort = Sort.by("borrowedAt").descending();
        PageRequest pageRequest = PageRequest.of(0,borrowedAtSize,sort);
        return bookRepository.findAll(pageRequest);
    }

    public Iterable<Book> sortBooksByBorrowedEndAtLimitDesc() {
        Sort sort = Sort.by("borrowedEndAt").descending();
        PageRequest pageRequest = PageRequest.of(0,borrowedEndSize,sort);
        return bookRepository.findAll(pageRequest);
    }

    public List<Book> sortBooksByBorrowedAtAllDesc() {
        Sort sort = Sort.by("borrowedEndAt").descending();
        return bookRepository.findAll(sort);
    }

    public List<Book> sortBooksByBorrowedEndAtAllDesc() {
        Sort sort = Sort.by("borrowedAt").descending();
        return bookRepository.findAll(sort);
    }
}
