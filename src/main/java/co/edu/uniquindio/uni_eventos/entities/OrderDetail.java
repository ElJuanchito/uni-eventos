package co.edu.uniquindio.uni_eventos.entities;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
public class OrderDetail {

    private ObjectId eventId;
    private Float price;
    private String sectionName;
    private Integer quantity;
}
