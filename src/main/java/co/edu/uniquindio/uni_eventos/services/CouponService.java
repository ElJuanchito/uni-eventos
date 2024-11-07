package co.edu.uniquindio.uni_eventos.services;

import co.edu.uniquindio.uni_eventos.dtos.coupon.CouponInfoDTO;
import co.edu.uniquindio.uni_eventos.dtos.coupon.CreateCouponDTO;
import co.edu.uniquindio.uni_eventos.dtos.coupon.UpdateCouponDTO;
import co.edu.uniquindio.uni_eventos.entities.Coupon;
import co.edu.uniquindio.uni_eventos.exceptions.AccountNotExistsException;
import co.edu.uniquindio.uni_eventos.exceptions.CouponNotExistsException;

import java.util.List;

public interface CouponService {

        CouponInfoDTO createCoupon(CreateCouponDTO dto);
        boolean validateCoupon(String id) throws CouponNotExistsException;
        void redeemCoupon(String id) throws CouponNotExistsException;
        List<CouponInfoDTO> getAllCoupons();
        CouponInfoDTO getCouponInfoById(String id) throws CouponNotExistsException;
        void editCoupon(UpdateCouponDTO dto) throws CouponNotExistsException;
        void deleteCoupon(String id) throws CouponNotExistsException;
        CouponInfoDTO getCouponInfoByCode(String code) throws CouponNotExistsException;
        void sendFirstPaymentCoupon(String userId) throws AccountNotExistsException;
        Coupon getCouponById(String id) throws CouponNotExistsException;

}
