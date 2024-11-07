package co.edu.uniquindio.uni_eventos.controllers;

import co.edu.uniquindio.uni_eventos.dtos.MessageDTO;
import co.edu.uniquindio.uni_eventos.dtos.account.CreateAccountDTO;
import co.edu.uniquindio.uni_eventos.dtos.account.LoginDTO;
import co.edu.uniquindio.uni_eventos.dtos.account.UpdatePasswordDTO;
import co.edu.uniquindio.uni_eventos.dtos.security.TokenDTO;
import co.edu.uniquindio.uni_eventos.services.AccountService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")

public class AuthController {

    private final AccountService accountService;
    
    @SecurityRequirement(name = "bearerAuth")
    @PostMapping("/login")
    public ResponseEntity<MessageDTO<TokenDTO>> login(@Valid @RequestBody LoginDTO loginDTO) throws Exception{
        TokenDTO token = accountService.login(loginDTO);
        return ResponseEntity.ok(new MessageDTO<>(false, token));
    }

    @PostMapping("/create-account")
    @ResponseStatus(HttpStatus.OK)
    public MessageDTO<String> createAccount(@Valid @RequestBody CreateAccountDTO dto) throws Exception {
        accountService.createAccount(dto);
        return new MessageDTO<>(false, "Cuenta creada satisfactoriamente");
    }

    @PutMapping("/validate-account")
    @ResponseStatus(HttpStatus.OK)
    public MessageDTO<String> validateAccount(@RequestParam String email, @RequestParam String code) throws Exception {
        accountService.validateAccount(email, code);
        return new MessageDTO<>(false, "Cuenta correctamente validada");
    }

    @PutMapping("/send-password-code")
    @ResponseStatus(HttpStatus.OK)
    public MessageDTO<String> sendPasswordCode(@RequestParam String email) throws Exception {
        accountService.sendPasswordCode(email);
        return new MessageDTO<>(false, "Codigo enviado correctamente");
    }

    @PutMapping("/recover-password")
    @ResponseStatus(HttpStatus.OK)
    public MessageDTO<String> recoverPassword(@RequestBody @Valid UpdatePasswordDTO dto) throws Exception {
        accountService.recoverPassword(dto);
        return new MessageDTO<>(false, "Contrasena recuperada satisfactoriamente");
    }

}
