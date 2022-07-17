package springboot.library.model.mapper.read;

import springboot.library.model.AppUser;
import springboot.library.model.dto.read.AppUserReadDto;

public class AppUserReadDtoMapper {

    public static AppUserReadDto mapAppUserToAppUserReadDto(AppUser appUser) {
        return AppUserReadDto.builder()
                .firstName(appUser.getFirstName())
                .lastName(appUser.getLastName())
                .build();
    }
}
