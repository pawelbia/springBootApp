package springboot.library.model.dto.read;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class BookReadDetailsDto {

    private String title;

    private String publishHouse;

    private int pages;

    private String publicationDate;

    private String coverPrintType;

    private LocalDateTime borrowedAt;

    private LocalDateTime borrowedEndAt;
}

