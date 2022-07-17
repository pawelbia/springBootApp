package springboot.library.model.mapper.read;

import springboot.library.model.Book;
import springboot.library.model.dto.read.BookReadDetailsDto;

import java.util.List;
import java.util.stream.Collectors;

public class BookReadDetailsDtoMapper {

    public static List<BookReadDetailsDto> mapBookToBookReadDetailsDto(List<Book> books) {
        return books.stream()
                .map(book -> BookReadDetailsDto.builder()
                        .title(book.getTitle())
                        .publishHouse(book.getPublishHouse())
                        .pages(book.getPages())
                        .publicationDate(book.getPublicationDate())
                        .coverPrintType(book.getCoverPrintType())
                        .borrowedAt(book.getBorrowedAt())
                        .borrowedEndAt(book.getBorrowedEndAt())
                        .build()).collect(Collectors.toList());
    }
}
