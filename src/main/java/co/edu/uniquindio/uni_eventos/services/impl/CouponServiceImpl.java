package co.edu.uniquindio.uni_eventos.services.impl;

import co.edu.uniquindio.uni_eventos.dtos.coupon.CouponInfoDTO;
import co.edu.uniquindio.uni_eventos.dtos.coupon.CreateCouponDTO;
import co.edu.uniquindio.uni_eventos.dtos.coupon.UpdateCouponDTO;
import co.edu.uniquindio.uni_eventos.entities.Account;
import co.edu.uniquindio.uni_eventos.entities.Coupon;
import co.edu.uniquindio.uni_eventos.entities.CouponStatus;
import co.edu.uniquindio.uni_eventos.entities.CouponType;
import co.edu.uniquindio.uni_eventos.exceptions.AccountNotExistsException;
import co.edu.uniquindio.uni_eventos.exceptions.CouponExistsException;
import co.edu.uniquindio.uni_eventos.exceptions.CouponNotExistsException;
import co.edu.uniquindio.uni_eventos.mappers.CouponMapper;
import co.edu.uniquindio.uni_eventos.repositories.AccountRepository;
import co.edu.uniquindio.uni_eventos.repositories.CouponRepository;
import co.edu.uniquindio.uni_eventos.services.AccountService;
import co.edu.uniquindio.uni_eventos.services.CodeGeneratorService;
import co.edu.uniquindio.uni_eventos.services.CouponService;
import co.edu.uniquindio.uni_eventos.services.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class CouponServiceImpl implements CouponService {

    private final CouponRepository couponRepository;
    private final CouponMapper couponMapper;
    private final CodeGeneratorService codeGenerator;
    private final AccountRepository accountRepository;
    private final EmailService emailService;

    @Override
    public CouponInfoDTO createCoupon(CreateCouponDTO dto) {
        Coupon coupon = couponMapper.toCoupon(dto);
        coupon.setCode(codeGenerator.generateCode(6));

        coupon = couponRepository.save(coupon);
        return couponMapper.toInfoDTO(coupon);
    }

    @Override
    public boolean validateCoupon(String id) throws CouponNotExistsException {
        Coupon coupon = getCouponOrThrow(id);

        if(coupon.getExpirationDate().isBefore(LocalDateTime.now())) return false;
        return coupon.getStatus().equals(CouponStatus.AVALIABLE);
    }

    @Override
    public void redeemCoupon(String id) throws CouponNotExistsException {
        Coupon coupon = getCouponOrThrow(id);

        if(coupon.getType().equals(CouponType.UNIQUE)) coupon.setStatus(CouponStatus.UNAVAILABLE);
    }

    @Override
    public List<CouponInfoDTO> getAllCoupons() {
        return couponRepository.findAll().stream().map(couponMapper::toInfoDTO).toList();
    }

    @Override
    public CouponInfoDTO getCouponInfoById(String id) throws CouponNotExistsException {
        Coupon coupon = getCouponOrThrow(id);
        return couponMapper.toInfoDTO(coupon);
    }

    @Override
    public void editCoupon(UpdateCouponDTO dto) throws CouponNotExistsException {
        Coupon coupon = getCouponOrThrow(dto.id());
        coupon.setDiscount(dto.discount());
        coupon.setExpirationDate(dto.expirationDate());
        coupon.setStatus(dto.status());
        coupon.setType(dto.type());
        coupon.setName(dto.name());
        coupon = couponRepository.save(coupon);
    }

    @Override
    public void deleteCoupon(String id) throws CouponNotExistsException {
        Coupon coupon = getCouponOrThrow(id);
        coupon.setStatus(CouponStatus.UNAVAILABLE);
        couponRepository.save(coupon);
    }

    @Override
    public CouponInfoDTO getCouponInfoByCode(String code) throws CouponNotExistsException {
        Optional<Coupon> optionalCoupon = couponRepository.findByCode(code);
        if(optionalCoupon.isEmpty()) throw new CouponNotExistsException("El cupon con codigo: " + code + ", no existe");
        return couponMapper.toInfoDTO(optionalCoupon.get());
    }

    @Override
    public void sendFirstPaymentCoupon(String userId) throws AccountNotExistsException {
        Coupon coupon = Coupon.builder()
                .discount(10f)
                .expirationDate(LocalDateTime.of(2050, 12,31, 23, 00))
                .code(codeGenerator.generateCode(6))
                .status(CouponStatus.AVALIABLE)
                .type(CouponType.UNIQUE)
                .name("First payment coupon")
                .build();

        couponRepository.save(coupon);
        Account account = accountRepository.findById(userId).orElseThrow(() -> new AccountNotExistsException("El usuario no existe"));
        emailService.sendEmail(account.getEmail(), "Cupon de primera compra", String.format("Acabar de recibir un cupon con de descuento por tu primera compra, redimelo con el codigo: %s, su fecha de expiracion es: %s", coupon.getCode(), coupon.getExpirationDate().toString()));
    }

    @Override
    public Coupon getCouponById(String id) throws CouponNotExistsException {
        return getCouponOrThrow(id);
    }

    private Coupon getCouponOrThrow(String id) throws CouponNotExistsException {
        Optional<Coupon> optionalCoupon = couponRepository.findById(id);
        return optionalCoupon.orElseThrow(() -> new CouponNotExistsException("El cupon con id: " + id + ", no existe"));
    }
}
