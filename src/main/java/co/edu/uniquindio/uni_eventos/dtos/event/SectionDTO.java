package co.edu.uniquindio.uni_eventos.dtos.event;

public record SectionDTO(
        Float price,
        String name,
        Integer ticketsSold,
        Integer maxCapacity
) {
}
