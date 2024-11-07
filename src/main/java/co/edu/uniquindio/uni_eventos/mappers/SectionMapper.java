package co.edu.uniquindio.uni_eventos.mappers;

import co.edu.uniquindio.uni_eventos.dtos.event.CreateSectionDTO;
import co.edu.uniquindio.uni_eventos.entities.Section;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SectionMapper {

    @Mapping(target = "ticketsSold", ignore = true)
    Section toSection(CreateSectionDTO createSectionDTO);
}
