package co.edu.uniquindio.uni_eventos.entities;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EventStatus {

    ACTIVE("Active"), INACTIVE("Inactive");

    private final String status;
}
