package co.edu.uniquindio.uni_eventos.dtos.order;

import jakarta.validation.constraints.NotBlank;

public record CreateOrderDTO(
        @NotBlank String userId,
        String couponId
) {
}
