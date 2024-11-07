package co.edu.uniquindio.uni_eventos.exceptions;

public class MailNotExistsException extends Exception {
    private static final long serialVersionUID = 1L;

    public MailNotExistsException(String msg) {
        super(msg);
    }
}
