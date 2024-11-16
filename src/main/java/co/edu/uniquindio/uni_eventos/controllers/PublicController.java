package co.edu.uniquindio.uni_eventos.controllers;

import co.edu.uniquindio.uni_eventos.dtos.MessageDTO;
import co.edu.uniquindio.uni_eventos.dtos.event.EventInfoDTO;
import co.edu.uniquindio.uni_eventos.services.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/public")
@RequiredArgsConstructor
public class PublicController {

    private final EventService eventService;

    @GetMapping("/event/get-types")
    @ResponseStatus(HttpStatus.OK)
    public MessageDTO<List<String>> getTypes() throws Exception {
        List<String> types = eventService.getTypes();
        return new MessageDTO<>(false, types);
    }

    @GetMapping("/event/get-cities")
    @ResponseStatus(HttpStatus.OK)
    public MessageDTO<List<String>> getCities() throws Exception {
        List<String> cities = eventService.getCities();
        return new MessageDTO<>(false, cities);
    }

    @GetMapping("/event/get-all")
    @ResponseStatus(HttpStatus.OK)
    public MessageDTO<List<EventInfoDTO>> getAllEvents() throws Exception {
        List<EventInfoDTO> events = eventService.getAllEvents();
        return new MessageDTO<>(false, events);
    }

    @GetMapping("/event/get/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MessageDTO<EventInfoDTO> getEventById(@PathVariable String id) throws Exception {
        EventInfoDTO event = eventService.getEvent(id);
        return new MessageDTO<>(false, event);
    }
}
