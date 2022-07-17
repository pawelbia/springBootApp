package springboot.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import springboot.library.model.AppUser;
import springboot.library.model.StudentCard;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface StudentCardRepository extends JpaRepository<StudentCard, Long> {

    @Transactional
    @Modifying
    @Query("UPDATE StudentCard s " + "SET s.albumNumber = ?1 ")
    void updateStudentCardAlbumNumber(int albumNumber);

    @Transactional
    @Modifying
    @Query("UPDATE StudentCard s " + "SET s.groupNumber = ?1 ")
    void updateStudentCardGroupNumber(String groupNumber);

    @Transactional
    @Modifying
    @Query("UPDATE StudentCard s " + "SET s.groupNumber = ?1, " + "s.albumNumber = ?2")
    void updateStudentCard(String groupNumber, int albumNumber);
    @Transactional
    @Modifying
    @Query("DELETE FROM  StudentCard s " + "WHERE s.appUser = ?1")
    void deleteStudentCard(AppUser appUser);

    @Transactional
    @Query("SELECT s " + "FROM StudentCard s " + "WHERE s.groupNumber=?1")
    List<StudentCard> getStudentCardsByGroupNumber(String groupNumber);

    @Transactional
    @Query("SELECT s " + "FROM StudentCard s " + "WHERE s.id=?1")
    Optional<StudentCard> findStudentCardByAppUserId(Long appUserId);
}
