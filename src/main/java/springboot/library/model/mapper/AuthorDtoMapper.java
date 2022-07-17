package springboot.library.model.mapper;

import springboot.library.model.Author;
import springboot.library.model.dto.AuthorDto;

import java.util.List;
import java.util.stream.Collectors;


public class AuthorDtoMapper {

    //Used to take data from client and creates Author object
    public static Author mapAuthorDtoToAuthor(AuthorDto authorDto) {
        return Author.builder()
                .firstName(authorDto.getFirstName())
                .lastName(authorDto.getLastName())
                .build();
    }

    //Used to take data from Author entity and map to Read data object
    public static AuthorDto mapAuthorToAuthorDto(Author author) {
        return AuthorDto.builder()
                .firstName(author.getFirstName())
                .lastName(author.getLastName())
                .build();
    }
    //Used to take data from Author entity as a List and map to Read data object as a List
    public static List<AuthorDto> mapAuthorListToAuthorDtoList(List<Author> authors) {
        return authors.stream()
                .map(AuthorDtoMapper::mapAuthorToAuthorDto).collect(Collectors.toList());
    }
}
