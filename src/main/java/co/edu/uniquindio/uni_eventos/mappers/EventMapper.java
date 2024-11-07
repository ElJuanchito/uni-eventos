package co.edu.uniquindio.uni_eventos.mappers;

import co.edu.uniquindio.uni_eventos.dtos.event.CreateEventDTO;
import co.edu.uniquindio.uni_eventos.dtos.event.EventInfoDTO;
import co.edu.uniquindio.uni_eventos.dtos.event.UpdateEventDTO;
import co.edu.uniquindio.uni_eventos.entities.Event;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EventMapper {

    EventInfoDTO ToInfoDTO(Event event);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "coverImg", ignore = true)
    @Mapping(target = "sectionImg", ignore = true)
    Event toEvent(CreateEventDTO createEventDTO);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "coverImg", ignore = true)
    @Mapping(target = "sectionImg", ignore = true)
    Event toUpdateEvent(UpdateEventDTO toUpdateEventDTO);

}
