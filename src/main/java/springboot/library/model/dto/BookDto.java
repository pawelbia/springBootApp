package springboot.library.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;


@AllArgsConstructor
@Getter
@Builder
public class BookDto {

    @NotBlank
    private String title;

    @NotBlank
    private String publishHouse;

    @NotBlank
    private int pages;

    @NotBlank
    private String publicationDate;

    // TODO: regex to add possibly only two types HardCover or SoftCover
    @NotBlank
    private String coverPrintType;

}
