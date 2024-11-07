package co.edu.uniquindio.uni_eventos.exceptions;

public class WrongCodeException extends Exception{

    private static final long serialVersionUID = 1L;

    public WrongCodeException(String message) {
        super(message);
    }
}
