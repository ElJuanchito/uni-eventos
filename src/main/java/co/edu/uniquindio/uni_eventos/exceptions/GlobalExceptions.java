package co.edu.uniquindio.uni_eventos.exceptions;

import co.edu.uniquindio.uni_eventos.dtos.MessageDTO;
import co.edu.uniquindio.uni_eventos.dtos.ValidationDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptions {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<MessageDTO<String>> generalException(Exception e){
        return ResponseEntity.internalServerError().body( new MessageDTO<>(true, e.getMessage()) );
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<MessageDTO<List<ValidationDTO>>> validationException(MethodArgumentNotValidException ex ) {
        List<ValidationDTO> errores = new ArrayList<>();
        BindingResult results = ex.getBindingResult();


        for (FieldError e: results.getFieldErrors()) {
            errores.add( new ValidationDTO(e.getField(), e.getDefaultMessage()) );
        }


        return ResponseEntity.badRequest().body( new MessageDTO<>(true, errores) );
    }

}
