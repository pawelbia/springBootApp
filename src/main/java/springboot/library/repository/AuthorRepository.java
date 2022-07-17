package springboot.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import springboot.library.model.Author;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface AuthorRepository extends JpaRepository<Author, Long> {


    @Transactional
    @Query("SELECT a" + " FROM Author a" + " WHERE a.lastName = ?1")
    List<Author> findAuthorsByLastName(String lastName);

    @Transactional
    // TODO: @Query
    boolean existsAuthorByFirstNameAndLastName(String firstName, String lastName);

}
