package co.edu.uniquindio.uni_eventos.dtos.event;

import co.edu.uniquindio.uni_eventos.entities.EventStatus;
import co.edu.uniquindio.uni_eventos.entities.EventType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;

public record CreateEventDTO(
        @NotBlank String coverImg,
        @NotNull EventStatus status,
        @NotBlank String name,
        @NotBlank String description,
        @NotBlank String address,
        @NotBlank String sectionImg,
        @NotNull EventType type,
        @NotNull LocalDateTime date,
        @NotBlank String city,
        @NotEmpty List<CreateSectionDTO> sections
) {
}
