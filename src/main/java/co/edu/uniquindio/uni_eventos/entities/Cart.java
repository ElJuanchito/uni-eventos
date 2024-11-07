package co.edu.uniquindio.uni_eventos.entities;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Document(collection = "carts")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
public class Cart {

    @Id
    @EqualsAndHashCode.Include
    private String id;
    private LocalDateTime date;
    private List<CartDetail> items;
    private ObjectId userId;
}
