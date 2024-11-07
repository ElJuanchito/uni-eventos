package co.edu.uniquindio.uni_eventos.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Payment {

    @Id
    @EqualsAndHashCode.Include
    private String id;
    private String currency;
    private String paymentType;
    private String statusDetail;
    private String authorizationCode;
    private LocalDateTime date;
    private Float transactionAmount;
    private String status;
}
