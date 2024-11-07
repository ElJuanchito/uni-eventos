package co.edu.uniquindio.uni_eventos.dtos.order;

import java.time.LocalDateTime;
import java.util.List;

public record OrderInfoDTO(
        String id,
        Float total,
        LocalDateTime date,
        List<OrderDetailInfoDTO> orderDetails
) {
}
