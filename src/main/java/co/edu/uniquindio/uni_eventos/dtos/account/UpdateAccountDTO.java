package co.edu.uniquindio.uni_eventos.dtos.account;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record UpdateAccountDTO(
        @NotBlank String id,
        @NotBlank @Length(max = 50) String name,
        @Length(max = 10) String phone,
        @Length(max = 100) String address
) {
}
