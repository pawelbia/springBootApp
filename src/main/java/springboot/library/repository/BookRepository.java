package springboot.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import springboot.library.model.AppUser;
import springboot.library.model.Book;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;

@Repository
@Transactional
public interface BookRepository extends JpaRepository<Book, Long> {


    @Transactional
    @Query("SELECT b " + "FROM Book b " + "WHERE b.title=?1")
    Optional<Book> findBookByTitle(String title);


    @Transactional
    @Modifying
    @Query("UPDATE Book b " + "SET b.title = ?2 " + "WHERE b.title = ?1")
    void updateTitle(String titleOld, String titleNew);

    @Transactional
    @Modifying
    @Query("UPDATE Book b " + "SET b.publishHouse = ?2 " + "WHERE b.title = ?1")
    void updatePublishHouse(String title, String publishHouse);

    @Transactional
    @Modifying
    @Query("UPDATE Book b " + "SET b.pages = ?2 " + "WHERE b.title = ?1")
    void updatePages(String title, int pages);

    @Transactional
    @Modifying
    @Query("UPDATE Book b " + "SET b.publicationDate = ?2 " + "WHERE b.title = ?1")
    void updateYearOfPublish(String title, String yearOfPublish);

    @Transactional
    @Modifying
    @Query("UPDATE Book b " + "SET b.coverPrintType = ?2 " + "WHERE b.title = ?1")
    void updateCoverPrintType(String title, String coverPrintType);

    @Transactional
    @Modifying
    @Query(value="UPDATE book SET app_user_id = NULL, borrowed_at = NULL, borrowed_end_at = NULL WHERE book_id = ?1", nativeQuery = true)
    void removeBookFromAppUserNative(Long bookId);

    @Transactional
    @Modifying
    @Query(value="UPDATE book SET app_user_id = ?2, borrowed_at = ?3, borrowed_end_at = ?4 WHERE book_id = ?1", nativeQuery = true)
    void addBookToAppUserNative(Long bookId, Long appUserId, LocalDateTime borrowedAt, LocalDateTime borrowedEndAt);
    @Transactional
    // TODO: @Query
    int countBooksByTitle(String title);

    @Transactional
    // TODO: @Query
    int countBooksByTitleAndBorrowedAtIsNull(String title);


}
