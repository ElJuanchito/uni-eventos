package co.edu.uniquindio.uni_eventos.controllers;

import co.edu.uniquindio.uni_eventos.dtos.MessageDTO;
import co.edu.uniquindio.uni_eventos.dtos.order.CreateOrderDTO;
import co.edu.uniquindio.uni_eventos.dtos.order.OrderInfoDTO;
import co.edu.uniquindio.uni_eventos.dtos.order.PaymentDTO;
import co.edu.uniquindio.uni_eventos.exceptions.OrderNotExistsException;
import co.edu.uniquindio.uni_eventos.services.OrderService;
import com.mercadopago.resources.preference.Preference;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/make-payment")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<MessageDTO<PaymentDTO>> realizarPago(@RequestParam("orderId") String orderId) throws Exception{
        return ResponseEntity.ok().body(new MessageDTO<>(false, orderService.makePayment(orderId)));
    }


    @PostMapping("/notification")
    @ResponseStatus(HttpStatus.OK)
    public void recibirNotificacionMercadoPago(@RequestBody Map<String, Object> requestBody) {
        orderService.receiveMercadoLibreNotification(requestBody);
    }

    @PostMapping("/create-order")
    @ResponseStatus(HttpStatus.OK)
    @SecurityRequirement(name = "bearerAuth")
    public MessageDTO<String> createOrder(@Valid @RequestBody CreateOrderDTO orderDTO) throws Exception{
        orderService.createOrder(orderDTO);
        return new MessageDTO<>(false, "order create successfully");
    }

    @DeleteMapping("/cancel-order/{orderId}")
    @ResponseStatus(HttpStatus.OK)
    @SecurityRequirement(name = "bearerAuth")
    public MessageDTO<String> cancelOrder(@PathVariable @NotBlank String orderId) throws Exception {
        orderService.cancelOrder(orderId);
        return new MessageDTO<>(false, "order cancel successfully");
    }

    @GetMapping("/get/history/{userId}")
    @ResponseStatus(HttpStatus.OK)
    @SecurityRequirement(name = "bearerAuth")
    public MessageDTO<List<OrderInfoDTO>> getOrderHistory(@NotBlank @PathVariable String userId) throws Exception{
        List<OrderInfoDTO> orders = orderService.getOrderHistory(userId);
        return new MessageDTO<>(false, orders);
    }

    @GetMapping("/get/{id}")
    @ResponseStatus(HttpStatus.OK)
    @SecurityRequirement(name = "bearerAuth")
    public MessageDTO<OrderInfoDTO> getOrder(@PathVariable String id) throws Exception {
        OrderInfoDTO order = orderService.getOrder(id);
        return new MessageDTO<>(false, order);
    }



}
