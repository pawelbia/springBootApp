package springboot.library.model.mapper.write;

import lombok.AllArgsConstructor;
import springboot.library.model.Book;
import springboot.library.model.dto.BookDto;

import java.time.LocalDateTime;

@AllArgsConstructor
public class BookDtoMapper {
    public static Book mapBookDtoToBook(BookDto bookDto) {
        return Book.builder()
                .title(bookDto.getTitle())
                .publishHouse(bookDto.getPublishHouse())
                .pages(bookDto.getPages())
                .publicationDate(bookDto.getPublicationDate())
                .coverPrintType(bookDto.getCoverPrintType())
                .build();
    }
}
