package co.edu.uniquindio.uni_eventos.entities;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {

    CUSTOMER("Customer"), ADMIN("Admin");

    private final String role;
}
