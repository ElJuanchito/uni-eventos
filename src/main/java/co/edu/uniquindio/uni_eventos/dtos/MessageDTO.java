package co.edu.uniquindio.uni_eventos.dtos;

public record MessageDTO<T>(
    boolean error,
    T response
) {
}
