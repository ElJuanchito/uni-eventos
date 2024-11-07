package co.edu.uniquindio.uni_eventos.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Document(collection = "events")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Event {

    @Id
    @EqualsAndHashCode.Include
    private String id;
    private String coverImg;
    private EventStatus status;
    private String name;
    private String description;
    private String address;
    private String sectionImg;
    private EventType type;
    private LocalDateTime date;
    private String city;
    private List<Section> sections;
}
