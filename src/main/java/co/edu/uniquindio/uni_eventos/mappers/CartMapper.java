package co.edu.uniquindio.uni_eventos.mappers;

import co.edu.uniquindio.uni_eventos.dtos.cart.AddCartDetailDTO;
import co.edu.uniquindio.uni_eventos.dtos.cart.CartDetailInfoDTO;
import co.edu.uniquindio.uni_eventos.entities.CartDetail;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CartMapper {

    @Mapping(target = "eventId" , ignore = true)
    CartDetail toCartDetail(AddCartDetailDTO dto);

    @Mapping(target = "eventId" , ignore = true)
    CartDetailInfoDTO toDetailInfoDTO(CartDetail cartDetail);
}
