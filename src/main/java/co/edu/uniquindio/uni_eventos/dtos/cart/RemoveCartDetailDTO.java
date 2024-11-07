package co.edu.uniquindio.uni_eventos.dtos.cart;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record RemoveCartDetailDTO(
        @Min(value = 1) Integer quantity,
        @NotBlank String sectionName,
        @NotBlank String eventId,
        @NotBlank String userId
) {
}
