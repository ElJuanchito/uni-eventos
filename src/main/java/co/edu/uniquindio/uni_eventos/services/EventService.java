package co.edu.uniquindio.uni_eventos.services;

import co.edu.uniquindio.uni_eventos.dtos.event.CreateEventDTO;
import co.edu.uniquindio.uni_eventos.dtos.event.FilterEventDTO;
import co.edu.uniquindio.uni_eventos.dtos.event.EventInfoDTO;
import co.edu.uniquindio.uni_eventos.dtos.event.UpdateEventDTO;
import co.edu.uniquindio.uni_eventos.entities.Event;
import co.edu.uniquindio.uni_eventos.exceptions.EventNotExistsException;

import java.util.List;

public interface EventService {

    void createEvent(CreateEventDTO eventDTO) throws Exception;
    void updateEvent(UpdateEventDTO eventDTO) throws Exception;
    void deleteEvent(String id) throws Exception;
    EventInfoDTO getEvent(String id) throws Exception;
    List<EventInfoDTO> getAllEvents() throws Exception;
    List<EventInfoDTO> filterEvents(FilterEventDTO filterEventDTO) throws Exception;
    Event getEventById(String id) throws EventNotExistsException;

    void updateSectionCapacity(String id, String sectionName, Integer quantity) throws Exception;
}
