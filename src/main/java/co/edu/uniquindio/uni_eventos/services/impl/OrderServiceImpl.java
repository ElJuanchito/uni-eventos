package co.edu.uniquindio.uni_eventos.services.impl;

import co.edu.uniquindio.uni_eventos.dtos.order.CreateOrderDTO;
import co.edu.uniquindio.uni_eventos.dtos.order.OrderDetailInfoDTO;
import co.edu.uniquindio.uni_eventos.dtos.order.OrderInfoDTO;
import co.edu.uniquindio.uni_eventos.dtos.order.PaymentDTO;
import co.edu.uniquindio.uni_eventos.entities.*;
import co.edu.uniquindio.uni_eventos.exceptions.*;
import co.edu.uniquindio.uni_eventos.repositories.OrderRepository;
import co.edu.uniquindio.uni_eventos.services.*;
import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.payment.PaymentClient;
import com.mercadopago.client.preference.PreferenceBackUrlsRequest;
import com.mercadopago.client.preference.PreferenceClient;
import com.mercadopago.client.preference.PreferenceItemRequest;
import com.mercadopago.client.preference.PreferenceRequest;
import com.mercadopago.resources.preference.Preference;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor //TODO Completar los datos de la pasarela de pago
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final EventService eventService;
    private final CartService cartService;
    private final CouponService couponService;
    private final AccountService accountService;

    @Override
    public void createOrder(CreateOrderDTO orderDTO) throws Exception {

        Cart cart = cartService.getCartByUserId(orderDTO.userId());


        List<CartDetail> cartDetails = cart.getItems();
        List<OrderDetail> orderDetails = new ArrayList<>();
        float total = 0f;

        for (CartDetail detail : cartDetails) {
            Event event = eventService.getEventById(detail.getEventId().toString());
            List<Section> sections = event.getSections();
            for (Section section : sections) {
                if(!detail.getSectionName().equals(section.getName())) continue;
                if(!(section.getTicketsSold() + detail.getQuantity() <= section.getMaxCapacity())) throw new SectionMaxException("No hay mas lugares disponibles para la localidad elegida");

                float subTotal = detail.getQuantity() * section.getPrice();

                orderDetails.add(
                        OrderDetail.builder()
                                .eventId(detail.getEventId())
                                .price(subTotal)
                                .sectionName(section.getName())
                                .quantity(detail.getQuantity())
                                .build()
                );
                eventService.updateSectionCapacity(event.getId(), detail.getSectionName(), detail.getQuantity());

                total += subTotal;
            }
        }
        if(orderDTO.couponId()!=null && !orderDTO.couponId().isBlank()) {
            Coupon coupon = couponService.getCouponById(orderDTO.couponId());
            if (couponService.validateCoupon(coupon.getId())) {
                float descuento = coupon.getDiscount() / 100f;
                total = total-(total*descuento);
                couponService.redeemCoupon(orderDTO.couponId());
            }
        }


        Order order = Order.builder()
                .userId(new ObjectId(orderDTO.userId()))
                .date(LocalDateTime.now())
                .items(orderDetails)
                .total(total)
                .couponId(orderDTO.couponId()!=null&&!orderDTO.couponId().isBlank()?new ObjectId(orderDTO.couponId()):null)
                .build();

        orderRepository.save(order);
        cartService.cleanCart(orderDTO.userId());
    }

    @Override
    public void cancelOrder(String orderId) throws Exception {
        Order order = orderRepository.findById(orderId).orElseThrow(()-> new OrderNotExistsException("La orden con id:" + orderId + " no existe"));
        List<OrderDetail> items = order.getItems();
        for(OrderDetail item : items){
            Event event= eventService.getEventById(item.getEventId().toString());
            Section section = getSection(event, item.getSectionName());
            int vendidas = section.getTicketsSold()-item.getQuantity();
            eventService.updateSectionCapacity(event.getId(), item.getSectionName(), vendidas);
        }
        orderRepository.deleteById(orderId);
    }

    @Override
    public List<OrderInfoDTO> getOrderHistory(String userId) throws Exception {
        Account account = accountService.getAccountById(userId);
        List<Order> orders = orderRepository.findAllByUserId(new ObjectId(account.getId()));
        if(orders.isEmpty()) throw new Exception("La cuenta no tiene ordenes asociadas");

        return orders.stream().map(this::toInfoDTO).toList();
    }

    @Override
    public OrderInfoDTO getOrder(String id) throws OrderNotExistsException {
        Order order = orderRepository.findById(id).orElseThrow(() -> new OrderNotExistsException("La orden con id:"+id+", no existe"));

        return toInfoDTO(order);
    }

    @Override
    public PaymentDTO makePayment(String orderId) throws Exception {
        Order order = getOrderById(orderId);
        List<PreferenceItemRequest> gatewayItems = new ArrayList<>();

        for(OrderDetail item : order.getItems()){
            Event event = eventService.getEventById(item.getEventId().toString());
            Section section = event
                    .getSections()
                    .stream()
                    .filter(s-> s.getName().equals(item.getSectionName()))
                    .findFirst().get();

            PreferenceItemRequest itemRequest = PreferenceItemRequest.builder()
                    .id(event.getId())
                    .title(event.getName())
                    .pictureUrl(event.getCoverImg())
                    .categoryId(event.getType().name())
                    .quantity(item.getQuantity())
                    .currencyId("COP")
                    .unitPrice(BigDecimal.valueOf(section.getPrice()))
                    .build();

            gatewayItems.add(itemRequest);
        }

        MercadoPagoConfig.setAccessToken("APP_USR-518908745717953-101020-d1359a7c2fdcf52383858e72a61d4758-2016716731");


        // Configurar las urls de retorno de la pasarela (Frontend)
        PreferenceBackUrlsRequest backUrls = PreferenceBackUrlsRequest.builder()
                .success("URL PAGO EXITOSO")
                .failure("URL PAGO FALLIDO")
                .pending("URL PAGO PENDIENTE")
                .build();


        // TODO Construir la preferencia de la pasarela con los ítems, metadatos y urls de retorno
        PreferenceRequest preferenceRequest = PreferenceRequest.builder()
                .backUrls(backUrls)
                .items(gatewayItems)
                .metadata(Map.of("id_orden", order.getId()))
                .notificationUrl("https://cow-enough-caiman.ngrok-free.app/api/orders/notification")
                .build();


        // Crear la preferencia en la pasarela de MercadoPago
        PreferenceClient client = new PreferenceClient();
        Preference preference = client.create(preferenceRequest);


        // Guardar el código de la pasarela en la orden
        order.setGatewayCode(preference.getId());
        orderRepository.save(order);
        couponService.sendFirstPaymentCoupon(order.getUserId().toString());


        return new PaymentDTO(preference.getInitPoint(), orderId);
    }

    @Override
    public void receiveMercadoLibreNotification(Map<String, Object> request) {
        try {


            // Obtener el tipo de notificación
            Object type = request.get("type");


            // Si la notificación es de un pago entonces obtener el pago y la orden asociada
            if ("payment".equals(type)) {


                // Capturamos el JSON que viene en el request y lo convertimos a un String
                String input = request.get("data").toString();


                // Extraemos los números de la cadena, es decir, el id del pago
                String idPago = input.replaceAll("\\D+", "");


                // Se crea el cliente de MercadoPago y se obtiene el pago con el id
                PaymentClient client = new PaymentClient();
                com.mercadopago.resources.payment.Payment payment = client.get( Long.parseLong(idPago) );


                // Obtener el id de la orden asociada al pago que viene en los metadatos
                String idOrden = payment.getMetadata().get("id_orden").toString();


                // Se obtiene la orden guardada en la base de datos y se le asigna el pago
                Order orden = getOrderById(idOrden);
                Payment pago = crearPago(payment);
                orden.setPayment(pago);
                orderRepository.save(orden);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private Payment crearPago(com.mercadopago.resources.payment.Payment payment) throws Exception {
        Payment pago = new Payment();
        pago.setId(payment.getId().toString());
        pago.setDate( payment.getDateCreated().toLocalDateTime() );
        pago.setStatus(payment.getStatus());
        pago.setStatusDetail(payment.getStatusDetail());
        pago.setPaymentType(payment.getPaymentTypeId());
        pago.setCurrency(payment.getCurrencyId());
        pago.setAuthorizationCode(payment.getAuthorizationCode());
        pago.setTransactionAmount(payment.getTransactionAmount().floatValue());
        return pago;
    }


    private Order getOrderById(@NotNull String id) throws Exception {
        Optional<Order> optionalOrder = orderRepository.findById(id);

        if(optionalOrder.isEmpty()) throw new OrderNotExistsException("La orden con el id " + id + "no existe" );
        return optionalOrder.get();
    }

    private Section getSection(Event event, String name) throws Exception {
        for(Section section: event.getSections()){
            if(section.getName().equals(name)){
                return section;
            }
        }
        throw new Exception("La localidad: "+name+" no existe");
    }

    private OrderDetailInfoDTO toDetailInfoDTO(OrderDetail detail) {
        Event event = null;
        try {
            event = eventService.getEventById(detail.getEventId().toString());
        } catch (EventNotExistsException e) {
            throw new RuntimeException(e);
        }
        return new OrderDetailInfoDTO(event.getName(), detail.getSectionName(), detail.getQuantity(), detail.getPrice());
    }

    private OrderInfoDTO toInfoDTO(Order order) {
        return new OrderInfoDTO(order.getId(), order.getTotal(), order.getDate(), order.getItems().stream().map(this::toDetailInfoDTO).toList());
    }
}
