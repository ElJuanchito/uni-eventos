package co.edu.uniquindio.uni_eventos.entities;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Document(collection = "orders")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
public class Order {

    @Id
    @EqualsAndHashCode.Include
    private String id;
    private ObjectId userId;
    private LocalDateTime date;
    private String gatewayCode;
    private List<OrderDetail> items;
    private Payment payment;
    private Float total;
    private ObjectId couponId;
}
