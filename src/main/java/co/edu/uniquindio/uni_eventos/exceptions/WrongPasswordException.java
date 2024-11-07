package co.edu.uniquindio.uni_eventos.exceptions;

public class WrongPasswordException extends Exception {

    private static final long serialVersionUID = 1L;

    public WrongPasswordException(String msg) {
        super(msg);
    }
}
