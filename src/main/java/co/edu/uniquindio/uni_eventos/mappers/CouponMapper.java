package co.edu.uniquindio.uni_eventos.mappers;

import co.edu.uniquindio.uni_eventos.dtos.coupon.CouponInfoDTO;
import co.edu.uniquindio.uni_eventos.dtos.coupon.CreateCouponDTO;
import co.edu.uniquindio.uni_eventos.entities.Coupon;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CouponMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "code", ignore = true)
    Coupon toCoupon(CreateCouponDTO dto);

    CouponInfoDTO toInfoDTO(Coupon coupon);
}
