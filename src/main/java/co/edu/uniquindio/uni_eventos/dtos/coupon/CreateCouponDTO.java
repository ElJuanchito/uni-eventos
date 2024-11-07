package co.edu.uniquindio.uni_eventos.dtos.coupon;

import co.edu.uniquindio.uni_eventos.entities.CouponStatus;
import co.edu.uniquindio.uni_eventos.entities.CouponType;
import jakarta.validation.constraints.*;

import java.time.LocalDateTime;

public record CreateCouponDTO(
        @Min(value = 1) Float discount,
        @Future LocalDateTime expirationDate,
        @NotNull CouponStatus status,
        @NotNull CouponType type,
        @NotBlank String name
) {
}
