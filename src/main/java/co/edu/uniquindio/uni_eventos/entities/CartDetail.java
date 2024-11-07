package co.edu.uniquindio.uni_eventos.entities;

import lombok.Builder;
import lombok.Data;
import org.bson.types.ObjectId;

@Data
@Builder
public class CartDetail {

    private Integer quantity;
    private String sectionName;
    private ObjectId eventId;
}
