package springboot.library.model.mapper.read;

import springboot.library.model.Book;
import springboot.library.model.BookAuthor;
import springboot.library.model.dto.AuthorDto;
import springboot.library.model.dto.read.BookReadDto;

import java.util.List;
import java.util.stream.Collectors;

public class BookReadDtoMapper {

    public static List<BookReadDto> mapBookToBookReadDtoList(List<Book> books){
        return books.stream()
                .map(book -> BookReadDto.builder()
                        .title(book.getTitle())
                        .publishHouse(book.getPublishHouse())
                        .pages(book.getPages())
                        .publicationDate(book.getPublicationDate())
                        .coverPrintType(book.getCoverPrintType())
                        .authorDtos(book.getAuthors().stream()
                                .map(BookAuthor::getAuthor).toList()
                                .stream()
                                .map(author -> AuthorDto.builder()
                                        .firstName(author.getFirstName())
                                        .lastName(author.getLastName())
                                        .build())
                                .collect(Collectors.toList()))
                        .build())
                .collect(Collectors.toList());


    }
    public static BookReadDto mapBookToBookReadDto(Book book) {
        return BookReadDto.builder()
                .title(book.getTitle())
                .publishHouse(book.getPublishHouse())
                .pages(book.getPages())
                .publicationDate(book.getPublicationDate())
                .coverPrintType(book.getCoverPrintType())
                .authorDtos(book.getAuthors().stream()
                        .map(BookAuthor::getAuthor).toList()
                        .stream()
                        .map(author -> AuthorDto.builder()
                                .firstName(author.getFirstName())
                                .lastName(author.getLastName())
                                .build())
                        .collect(Collectors.toList()))
                .build();
    }
}
