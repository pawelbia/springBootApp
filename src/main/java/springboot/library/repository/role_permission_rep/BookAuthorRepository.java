package springboot.library.repository.role_permission_rep;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import springboot.library.model.Author;
import springboot.library.model.Book;
import springboot.library.model.BookAuthor;
import springboot.library.model.BookAuthorId;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface BookAuthorRepository extends CrudRepository<BookAuthor, BookAuthorId> {

    @Transactional
    @Query("SELECT b " + "FROM BookAuthor b " + "WHERE b.book=?1 AND b.author=?2")
    BookAuthor findBookAuthorByBookAndAuthor(Book book, Author author);
}
