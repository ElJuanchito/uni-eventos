package co.edu.uniquindio.uni_eventos.entities;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CouponStatus {

    AVALIABLE("Available"), UNAVAILABLE("Unavailable");

    private final String status;
}
