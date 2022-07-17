package springboot.library.model.mapper.write;

import springboot.library.model.AppUser;
import springboot.library.model.StudentCard;
import springboot.library.model.dto.write.StudentCardDto;

public class StudentCardDtoMapper {

    public static StudentCard mapStudentCardDtoToStudentCardCreate(StudentCardDto studentCardDto, AppUser appUser) {
        return StudentCard.builder()
                .albumNumber(studentCardDto.getAlbumNumber())
                .groupNumber(studentCardDto.getGroupNumber())
                .appUser(appUser)
                .build();
    }
}
