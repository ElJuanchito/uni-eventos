package co.edu.uniquindio.uni_eventos.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
public class User {

    @Id
    @EqualsAndHashCode.Include
    private String id;

    private String phone;
    private String address;
    private String cedula;
    private String name;
}
