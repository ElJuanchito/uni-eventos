package co.edu.uniquindio.uni_eventos.dtos.event;

import co.edu.uniquindio.uni_eventos.entities.EventType;

public record FilterEventDTO(
        String name,
        String type,
        String city

) {
}
