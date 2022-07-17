package springboot.library.model.mapper.read;

import springboot.library.model.StudentCard;
import springboot.library.model.dto.read.StudentCardReadDto;

import java.util.List;
import java.util.stream.Collectors;

import static springboot.library.model.mapper.read.AppUserReadDtoMapper.mapAppUserToAppUserReadDto;

public class StudentCardReadDtoMapper {

    public static List<StudentCardReadDto> mapStudentCardListToStudentCardReadDtoList(List<StudentCard> studentCards) {
        return studentCards.stream()
                .map(studentCard -> StudentCardReadDto.builder()
                        .albumNumber(studentCard.getAlbumNumber())
                        .groupNumber(studentCard.getGroupNumber())
                        .appUserReadDto(mapAppUserToAppUserReadDto(studentCard.getAppUser()))
                        .build()).collect(Collectors.toList());
    }

    public static StudentCardReadDto mapStudentCardToStudentCardReadDto(StudentCard studentCard) {
        return StudentCardReadDto.builder()
                .albumNumber(studentCard.getAlbumNumber())
                .groupNumber(studentCard.getGroupNumber())
                .appUserReadDto(mapAppUserToAppUserReadDto(studentCard.getAppUser()))
                .build();
    }
}
