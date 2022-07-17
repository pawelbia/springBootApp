package springboot.library.registration;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/v1/registration")
@AllArgsConstructor
public class RegistrationController {

    private final RegistrationDtoService registrationDtoService;

    @GetMapping(path="confirm")
    public String confirm(@RequestParam("token") String token) {
        return registrationDtoService.confirmToken(token);
    }

    @PostMapping
    public String register(@RequestBody @Valid RegistrationDto registrationDto) {
        return registrationDtoService.register(registrationDto);
    }

}
