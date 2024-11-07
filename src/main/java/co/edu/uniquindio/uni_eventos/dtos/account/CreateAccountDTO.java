package co.edu.uniquindio.uni_eventos.dtos.account;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record CreateAccountDTO(
        @NotBlank(message = "La cedula es obligatoria") @Length(max = 10) String cedula,
        @NotBlank @Length(max = 20) String name,
        @NotBlank @Length(max = 10) String phone,
        @NotBlank @Length(max = 30) String address,
        @NotBlank @Length(max = 50) @Email String email,
        @NotBlank @Length(min = 8, max = 18) String password
){

}
