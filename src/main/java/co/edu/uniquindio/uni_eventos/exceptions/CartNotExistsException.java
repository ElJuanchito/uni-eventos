package co.edu.uniquindio.uni_eventos.exceptions;

public class CartNotExistsException extends Exception {

    private static final long serialVersionUID = 1L;

    public CartNotExistsException(String msg) {
        super(msg);
    }
}
