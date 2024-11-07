package co.edu.uniquindio.uni_eventos.services;

import co.edu.uniquindio.uni_eventos.dtos.cart.AddCartDetailDTO;
import co.edu.uniquindio.uni_eventos.dtos.cart.CartDetailInfoDTO;
import co.edu.uniquindio.uni_eventos.dtos.cart.CartInfoDTO;
import co.edu.uniquindio.uni_eventos.dtos.cart.RemoveCartDetailDTO;
import co.edu.uniquindio.uni_eventos.entities.Cart;
import co.edu.uniquindio.uni_eventos.exceptions.*;

import java.util.List;

public interface CartService {

    void createCart(String userId) throws CartExistsException, AccountNotExistsException;
    void addCartDetail(AddCartDetailDTO cartDetailDTO) throws CartDetailExistsException, CartNotExistsException;
    void removeCartDetail(RemoveCartDetailDTO cartDetailDTO) throws CartDetailNotExistsException,CartNotExistsException;
    void cleanCart(String userId) throws CartNotExistsException, AccountNotExistsException;
    List<CartDetailInfoDTO> getCartDetailsInfo(String userId) throws AccountNotExistsException, CartNotExistsException;
    CartInfoDTO getCartInfo(String userId) throws AccountNotExistsException, CartNotExistsException;
    Cart getCartByUserId(String userId) throws CartNotExistsException;
}
