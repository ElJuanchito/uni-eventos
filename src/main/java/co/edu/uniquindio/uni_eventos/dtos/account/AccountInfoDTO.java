package co.edu.uniquindio.uni_eventos.dtos.account;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record AccountInfoDTO(
        @NotBlank @Length(max = 10) String cedula,
        @NotBlank @Length(max = 20) String name,
        @NotBlank String phone,
        @NotBlank String address,
        @Email @NotBlank @Length(max = 50) String email
) {
}
