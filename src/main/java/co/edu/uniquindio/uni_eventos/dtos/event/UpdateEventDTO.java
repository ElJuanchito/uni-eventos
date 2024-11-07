package co.edu.uniquindio.uni_eventos.dtos.event;

import co.edu.uniquindio.uni_eventos.entities.EventStatus;
import co.edu.uniquindio.uni_eventos.entities.EventType;

import java.time.LocalDateTime;

public record UpdateEventDTO(
        String id,
        String coverImg,
        EventStatus status,
        String name,
        String description,
        String address,
        String sectionImg,
        EventType type,
        LocalDateTime date,
        String city
) {
}
