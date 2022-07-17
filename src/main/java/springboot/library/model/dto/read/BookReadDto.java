package springboot.library.model.dto.read;

import lombok.Builder;
import lombok.Getter;
import springboot.library.model.dto.AuthorDto;
import java.util.List;

@Getter
@Builder
public class BookReadDto {

    private String title;

    private String publishHouse;

    private int pages;

    private String publicationDate;

    private String coverPrintType;

    private List<AuthorDto> authorDtos;

}
