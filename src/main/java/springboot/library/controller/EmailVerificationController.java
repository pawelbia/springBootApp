package springboot.library.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import springboot.library.model.dto.write.AppUserEmailDto;
import springboot.library.registration.email.EmailDtoService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/email-update")
@AllArgsConstructor
public class EmailVerificationController {
    private final EmailDtoService emailDtoService;


    @PutMapping("/users/{id}/user-details")
    public String updateEmail(@PathVariable(value="id") Long id, @RequestBody @Valid AppUserEmailDto appUserEmailDto) {
        return emailDtoService.emailUpdate(appUserEmailDto, id);
    }

    @GetMapping(path="confirm")
    public String confirm(@RequestParam("token") String token) {
        return emailDtoService.confirmToken(token);
    }
}
