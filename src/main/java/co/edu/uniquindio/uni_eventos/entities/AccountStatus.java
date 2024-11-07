package co.edu.uniquindio.uni_eventos.entities;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AccountStatus {

    ACTIVE("Active"), INACTIVE("Inactive"), DELETED("Deleted");

    private final String status;
}
