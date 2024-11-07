package co.edu.uniquindio.uni_eventos.entities;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CouponType {

    MULTIPLE("Multiple"), UNIQUE("Unique");

    private final String type;
}
