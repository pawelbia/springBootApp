package springboot.library.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import springboot.library.model.dto.read.AppUserReadDto;
import springboot.library.model.dto.write.StudentCardDto;
import springboot.library.model.dto.read.StudentCardReadDto;
import springboot.library.service.AppUserService;
import springboot.library.service.StudentCardService;



import java.util.List;

import static springboot.library.model.mapper.write.StudentCardDtoMapper.*;
import static springboot.library.model.mapper.read.StudentCardReadDtoMapper.mapStudentCardListToStudentCardReadDtoList;
import static springboot.library.model.mapper.read.StudentCardReadDtoMapper.mapStudentCardToStudentCardReadDto;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class StudentCardController {

    private final StudentCardService studentCardService;
    private final AppUserService appUserService;


    @GetMapping("/users/student-cards/{groupNumber}")
    public List<AppUserReadDto> getUsersByStudentCardGroup(@PathVariable(value = "groupNumber") String groupNumber) {
       return studentCardService.getAppUsersByStudentCardsGroupNumber(groupNumber);
    }

    @GetMapping("/users/student-cards")
    public List<StudentCardReadDto> getStudentsDetails() {
        return mapStudentCardListToStudentCardReadDtoList(studentCardService.getStudentCards());
    }

    @GetMapping("users/student-cards/{id}")
    public StudentCardReadDto getStudentDetails(@PathVariable(value = "id") Long id) {
        return mapStudentCardToStudentCardReadDto(studentCardService.findById(id));
    }

    @PostMapping("users/{id}/create-student-card")
    public void createStudentCard(@PathVariable(value = "id") Long id, @RequestBody StudentCardDto studentCardDto) {
        studentCardService.save(mapStudentCardDtoToStudentCardCreate(studentCardDto, appUserService.findById(id)));
    }
}