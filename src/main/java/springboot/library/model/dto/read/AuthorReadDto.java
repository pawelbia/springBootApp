package springboot.library.model.dto.read;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import springboot.library.model.dto.BookDto;


import java.util.List;

@AllArgsConstructor
@Getter
@Builder
public class AuthorReadDto {

    private String firstName;

    private String lastName;

    private List<BookDto> bookDtos;
}
