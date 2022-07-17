package springboot.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import springboot.library.model.AppUser;
import springboot.library.model.Book;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface AppUserRepository extends JpaRepository<AppUser, Long> {


    @Transactional
    @Query("SELECT a" + " FROM AppUser a" + " WHERE a.email = ?1")
    Optional<AppUser> findAppUserByEmail(String email);

    @Transactional
    boolean existsAppUserByEmail(String email);
    @Transactional
    @Modifying
    @Query("UPDATE AppUser a " + "SET a.password = ?2 " + "WHERE a.id = ?1")
    void updatePassword(Long id, String password);

    @Transactional
    @Modifying
    @Query("UPDATE AppUser a " + "SET a.accountNonExpired = ?2 " + "WHERE a.id = ?1")
    void updateAccountNonExpired(Long id, boolean accountNonExpired);

    @Transactional
    @Modifying
    @Query("UPDATE AppUser a " + "SET a.accountNonLocked = ?2 " + "WHERE a.id = ?1")
    void updateAccountNonLocked(Long id, boolean accountNonLocked);
    @Transactional
    @Modifying
    @Query("UPDATE AppUser a " + "SET a.credentialsNonExpired = ?2 " + "WHERE a.id = ?1")
    void updateCredentialsNonExpired(Long id, boolean credentialsNonExpired);
    @Transactional
    @Modifying
    @Query("UPDATE AppUser a " + "SET a.enabled = ?2 " + "WHERE a.email = ?1")
    void updateEnabled(String email, boolean enabled);

    @Transactional
    @Modifying
    @Query("UPDATE AppUser a " + "SET a.enabled = ?2 " + "WHERE a.id = ?1")
    void updateEnabledById(Long id, boolean enabled);

    @Transactional
    @Modifying
    @Query("UPDATE AppUser a " + "SET a.email =?2 " + "WHERE a.id=?1")
    void updateEmail(Long id, String email);

    @Query("SELECT b" + " FROM Book b" + " WHERE b.id = ?1")
    List<Book> getBooks(Long id);

}
