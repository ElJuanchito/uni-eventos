package co.edu.uniquindio.uni_eventos.dtos.order;

public record OrderDetailInfoDTO(
        String eventName,
        String sectionName,
        Integer quantity,
        Float singlePrice
) {
}
