package springboot.library.model.mapper.read;

import springboot.library.model.Author;
import springboot.library.model.BookAuthor;
import springboot.library.model.dto.read.AuthorReadDto;
import springboot.library.model.dto.BookDto;

import java.util.List;
import java.util.stream.Collectors;

public class AuthorReadDtoMapper {

    public static List<AuthorReadDto> mapAuthorToAuthorReadDtoList(List<Author> authors) {
        return authors.stream()
                .map(author -> AuthorReadDto.builder()
                        .firstName(author.getFirstName())
                        .lastName(author.getLastName())
                        .bookDtos(author.getBooks().stream()
                                .map(BookAuthor::getBook).toList()
                                .stream()
                                .map(book -> BookDto.builder()
                                        .title(book.getTitle())
                                        .publishHouse(book.getPublishHouse())
                                        .pages(book.getPages())
                                        .publicationDate(book.getPublicationDate())
                                        .coverPrintType(book.getCoverPrintType())
                                        .build())
                                .collect(Collectors.toList()))
                        .build())
                .collect(Collectors.toList());
    }

    public static AuthorReadDto mapAuthorToAuthorReadDto(Author author) {
       return AuthorReadDto.builder()
                .firstName(author.getFirstName())
                .lastName(author.getLastName())
               .bookDtos(author.getBooks().stream()
                       .map(BookAuthor::getBook).toList()
                       .stream()
                       .map(book -> BookDto.builder()
                               .title(book.getTitle())
                               .publishHouse(book.getPublishHouse())
                               .pages(book.getPages())
                               .publicationDate(book.getPublicationDate())
                               .coverPrintType(book.getCoverPrintType())
                               .build())
                       .collect(Collectors.toList()))
               .build();
    }
}
