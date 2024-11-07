package co.edu.uniquindio.uni_eventos.entities;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ValidationCode {

    private LocalDateTime creationDate;
    private String code;
}
