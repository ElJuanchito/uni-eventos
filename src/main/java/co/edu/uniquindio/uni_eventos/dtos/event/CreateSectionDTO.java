package co.edu.uniquindio.uni_eventos.dtos.event;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CreateSectionDTO(
        @NotNull @Positive Float price,
        @NotBlank String name,
        @Min(value = 1) Integer maxCapacity
) {
}
