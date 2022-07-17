package springboot.library.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import springboot.library.model.AppUser;
import springboot.library.model.StudentCard;
import springboot.library.model.dto.read.AppUserReadDto;
import springboot.library.repository.StudentCardRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class StudentCardService {

    private final StudentCardRepository studentCardRepository;

    @Transactional
    public void updateStudentCardAlbumNumber(int albumNumber) {
        studentCardRepository.updateStudentCardAlbumNumber(albumNumber);
    }

    @Transactional
    public void updateStudentCardGroupNumber(String groupNumber) {
        studentCardRepository.updateStudentCardGroupNumber(groupNumber);
    }

    //Need to test this method
    @Transactional
    public void updateStudentCard(StudentCard studentCard) {
        studentCardRepository.updateStudentCard(studentCard.getGroupNumber(), studentCard.getAlbumNumber());
    }

    @Transactional
    public void deleteStudentCard(AppUser appUser) {
        studentCardRepository.deleteStudentCard(appUser);
    }

    public void save(StudentCard studentCard){
        studentCardRepository.save(studentCard);
    }

    public List<AppUserReadDto> getAppUsersByStudentCardsGroupNumber(String groupNumber) {
        List<StudentCard> studentCards = studentCardRepository.getStudentCardsByGroupNumber(groupNumber);
        return studentCards.stream()
                .map(StudentCard::getAppUser)
                .collect(Collectors.toList())
                .stream()
                .map(AppUser -> AppUserReadDto.builder()
                        .firstName(AppUser.getFirstName())
                        .lastName(AppUser.getLastName())
                        .build()).collect(Collectors.toList());
    }

    public StudentCard findStudentCardByAppUserId(Long appUserId) {
        return studentCardRepository.findStudentCardByAppUserId(appUserId)
                .orElseThrow(() -> new IllegalStateException("user not found"));
    }

    public List<StudentCard> getStudentCards() {
        return studentCardRepository.findAll();
    }

    public StudentCard findById(Long id) {
        return studentCardRepository.findById(id).orElseThrow(() -> new IllegalStateException("student card not found"));
    }
}

