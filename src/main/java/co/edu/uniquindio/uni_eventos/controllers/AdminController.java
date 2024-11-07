package co.edu.uniquindio.uni_eventos.controllers;

import co.edu.uniquindio.uni_eventos.dtos.MessageDTO;
import co.edu.uniquindio.uni_eventos.dtos.coupon.CouponInfoDTO;
import co.edu.uniquindio.uni_eventos.dtos.coupon.CreateCouponDTO;
import co.edu.uniquindio.uni_eventos.dtos.coupon.UpdateCouponDTO;
import co.edu.uniquindio.uni_eventos.dtos.event.CreateEventDTO;
import co.edu.uniquindio.uni_eventos.dtos.event.UpdateEventDTO;
import co.edu.uniquindio.uni_eventos.services.CouponService;
import co.edu.uniquindio.uni_eventos.services.EventService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
@SecurityRequirement(name = "bearerAuth")
public class AdminController {

    private final EventService eventService;
    private final CouponService couponService;

    @PostMapping("/events/create-event")
    @ResponseStatus(HttpStatus.OK)
    public MessageDTO<String> createEvent(@Valid @RequestBody CreateEventDTO eventDTO) throws Exception{
        eventService.createEvent(eventDTO);
        return new MessageDTO<>(false, "Event created");
    }

    @PutMapping("/events/update-event")
    @ResponseStatus(HttpStatus.OK)
    public MessageDTO<String> updateEvent(@Valid @RequestBody UpdateEventDTO eventDTO) throws Exception{
        eventService.updateEvent(eventDTO);
        return new MessageDTO<>(false, "Event updated");
    }

    @DeleteMapping("/events/delete-event/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MessageDTO<String> deleteEvent(@NotBlank @PathVariable String id) throws Exception{
        eventService.deleteEvent(id);
        return new MessageDTO<>(false, "Event deleted");
    }

    @PostMapping("/coupons/create-coupon")
    @ResponseStatus(HttpStatus.OK)
    public MessageDTO<CouponInfoDTO> createCoupon(@Valid @RequestBody CreateCouponDTO couponDTO) throws Exception{
        CouponInfoDTO coupon = couponService.createCoupon(couponDTO);
        return new MessageDTO<>(false,coupon);
    }

    @GetMapping("/coupons/get-all")
    @ResponseStatus(HttpStatus.OK)
    public MessageDTO<List<CouponInfoDTO>> getAllCoupons() throws Exception {
        List<CouponInfoDTO> coupons = couponService.getAllCoupons();
        return new MessageDTO<>(false, coupons);
    }

    @PutMapping("/coupons/edit-coupon")
    @ResponseStatus(HttpStatus.OK)
    public MessageDTO<String> editCoupon(@Valid @RequestBody UpdateCouponDTO updateDTO) throws Exception {
        couponService.editCoupon(updateDTO);
        return new MessageDTO<>(false, "Cupon actualizado correctamente");
    }

    @DeleteMapping("/coupons/delete-coupon/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MessageDTO<String> deleteCoupon(@PathVariable @NotBlank String id) throws Exception {
        couponService.deleteCoupon(id);
        return new MessageDTO<>(false, "Cupon eliminado correctamente");
    }

    @GetMapping("/coupons/get-info-by-id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MessageDTO<CouponInfoDTO> getCouponInfoById(@PathVariable @NotBlank String id) throws Exception {
        var info = couponService.getCouponInfoById(id);
        return new MessageDTO<>(false, info);
    }

    @GetMapping("/coupons/get-info-by-code/{code}")
    @ResponseStatus(HttpStatus.OK)
    public MessageDTO<CouponInfoDTO> getCouponInfoByCode(@PathVariable @NotBlank String code) throws Exception {
        var info = couponService.getCouponInfoByCode(code);
        return new MessageDTO<>(false, info);
    }





}
