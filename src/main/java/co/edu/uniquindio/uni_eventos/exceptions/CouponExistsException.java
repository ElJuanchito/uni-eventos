package co.edu.uniquindio.uni_eventos.exceptions;

public class CouponExistsException extends Exception{

    private static final long serialVersionUID = 1L;

    public CouponExistsException(String msg) {
        super(msg);
    }
}
