package co.edu.uniquindio.uni_eventos.exceptions;

public class CouponNotExistsException extends Exception{

    private static final long serialVersionUID = 1L;

    public CouponNotExistsException(String msg) {
        super(msg);
    }
}
