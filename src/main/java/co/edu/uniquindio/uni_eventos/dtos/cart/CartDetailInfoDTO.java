package co.edu.uniquindio.uni_eventos.dtos.cart;

import co.edu.uniquindio.uni_eventos.dtos.event.EventInfoDTO;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record CartDetailInfoDTO(
        @Min(value = 1) Integer quantity,
        @NotBlank String sectionName,
        @NotBlank String eventId
) {
}
