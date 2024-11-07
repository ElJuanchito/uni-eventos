package co.edu.uniquindio.uni_eventos.dtos.coupon;

import co.edu.uniquindio.uni_eventos.entities.CouponStatus;
import co.edu.uniquindio.uni_eventos.entities.CouponType;

import java.time.LocalDateTime;

public record CouponInfoDTO(
        String id,
        Float discount,
        LocalDateTime expirationDate,
        String code,
        CouponStatus status,
        CouponType type,
        String name
) {
}
