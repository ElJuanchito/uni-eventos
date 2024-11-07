package co.edu.uniquindio.uni_eventos.entities;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document(collection = "accounts")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
public class Account {

    @Id
    @EqualsAndHashCode.Include
    private String id;
    private Role role;
    private String email;
    private ValidationCode registrationCode;
//    private ObjectId userId;
    private User user;
    private LocalDateTime registrationDate;
    private String password;
    private AccountStatus status;
    private ValidationCode passwordCode;
}
