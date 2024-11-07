package co.edu.uniquindio.uni_eventos.controllers;

import co.edu.uniquindio.uni_eventos.dtos.MessageDTO;
import co.edu.uniquindio.uni_eventos.dtos.cart.AddCartDetailDTO;
import co.edu.uniquindio.uni_eventos.dtos.cart.CartDetailInfoDTO;
import co.edu.uniquindio.uni_eventos.dtos.cart.CartInfoDTO;
import co.edu.uniquindio.uni_eventos.dtos.cart.RemoveCartDetailDTO;
import co.edu.uniquindio.uni_eventos.exceptions.AccountNotExistsException;
import co.edu.uniquindio.uni_eventos.exceptions.CartExistsException;
import co.edu.uniquindio.uni_eventos.services.CartService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/carts")
@SecurityRequirement(name = "bearerAuth")
public class CartController {

    private final CartService cartService;

    @PostMapping("/create-cart/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public MessageDTO<String> createCart(@PathVariable @NotBlank String userId) throws CartExistsException, AccountNotExistsException {
        cartService.createCart(userId);
        return new MessageDTO<>(false, "Cart created successfully");
    }

    @PutMapping("/add-detail")
    @ResponseStatus(HttpStatus.OK)
    public MessageDTO<String> addCartDetail(@RequestBody @Valid AddCartDetailDTO cartDetailDTO) throws Exception {
        cartService.addCartDetail(cartDetailDTO);
        return new MessageDTO<>(false, "Cart added successfully");
    }

    @DeleteMapping("/remove-detail")
    @ResponseStatus(HttpStatus.OK)
    public MessageDTO<String> removeCartDetail(@RequestBody @Valid RemoveCartDetailDTO cartDetailDTO) throws Exception {
        cartService.removeCartDetail(cartDetailDTO);
        return new MessageDTO<>(false, "Cart removed successfully");
    }

    @PutMapping("/clean-cart/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public MessageDTO<String> cleanCart(@NotBlank @PathVariable String userId) throws Exception {
        cartService.cleanCart(userId);
        return new MessageDTO<>(false, "Cart cleaned successfully");
    }

    @GetMapping("/get/cart-details/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public MessageDTO<List<CartDetailInfoDTO>> getCartDetailsInfo(@PathVariable @NotBlank String userId) throws Exception {
        List<CartDetailInfoDTO> cartDetailInfoDTO = cartService.getCartDetailsInfo(userId);
        return new MessageDTO<>(false, cartDetailInfoDTO);
    }

    @GetMapping("/get/cart/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public MessageDTO<CartInfoDTO> getCartInfo(@PathVariable @NotBlank String userId) throws Exception {
        CartInfoDTO cartInfo = cartService.getCartInfo(userId);
        return new MessageDTO<>(false, cartInfo);
    }

}
