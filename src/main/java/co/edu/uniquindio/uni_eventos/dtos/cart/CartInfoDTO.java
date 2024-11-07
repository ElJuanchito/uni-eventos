package co.edu.uniquindio.uni_eventos.dtos.cart;

import java.time.LocalDateTime;
import java.util.List;

public record CartInfoDTO(
        LocalDateTime date,
        List<CartDetailInfoDTO> cartDetailInfoDTOS
) {
}
