package mx.ipn.upiiz.usuarios.adapters.in.web;

import mx.ipn.upiiz.usuarios.adapters.in.web.dto.ErrorResponse;
import mx.ipn.upiiz.usuarios.domain.exception.BusquedaInvalidaException;
import mx.ipn.upiiz.usuarios.domain.exception.CorreoDuplicadoException;
import mx.ipn.upiiz.usuarios.domain.exception.UsuarioDuplicadoException;
import mx.ipn.upiiz.usuarios.domain.exception.ValidacionUsuarioException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.List;

/** Traduce las excepciones del dominio a respuestas HTTP con mensajes claros. */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> validacionDto(MethodArgumentNotValidException ex) {
        List<String> detalles = ex.getBindingResult().getFieldErrors().stream()
                .map(e -> e.getDefaultMessage())
                .distinct()
                .toList();
        return respuesta(HttpStatus.BAD_REQUEST, detalles.get(0), detalles);
    }

    @ExceptionHandler({ValidacionUsuarioException.class, BusquedaInvalidaException.class})
    public ResponseEntity<ErrorResponse> reglaDominio(RuntimeException ex) {
        return respuesta(HttpStatus.BAD_REQUEST, ex.getMessage(), List.of(ex.getMessage()));
    }

    @ExceptionHandler({CorreoDuplicadoException.class, UsuarioDuplicadoException.class})
    public ResponseEntity<ErrorResponse> duplicado(RuntimeException ex) {
        return respuesta(HttpStatus.CONFLICT, ex.getMessage(), List.of(ex.getMessage()));
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponse> integridad(DataIntegrityViolationException ex) {
        String msg = "El correo o el usuario ya están registrados.";
        return respuesta(HttpStatus.CONFLICT, msg, List.of(msg));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> jsonInvalido(HttpMessageNotReadableException ex) {
        String msg = "Datos inválidos. Revisa que la fecha de nacimiento sea válida (aaaa-mm-dd).";
        return respuesta(HttpStatus.BAD_REQUEST, msg, List.of(msg));
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ErrorResponse> rutaNoEncontrada(NoResourceFoundException ex) {
        String msg = "La ruta solicitada no existe.";
        return respuesta(HttpStatus.NOT_FOUND, msg, List.of(msg));
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErrorResponse> metodoNoPermitido(HttpRequestMethodNotSupportedException ex) {
        String msg = "Método HTTP no permitido para esta ruta.";
        return respuesta(HttpStatus.METHOD_NOT_ALLOWED, msg, List.of(msg));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> general(Exception ex) {
        String msg = "Ocurrió un error inesperado en el servidor.";
        return respuesta(HttpStatus.INTERNAL_SERVER_ERROR, msg, List.of(msg));
    }

    private ResponseEntity<ErrorResponse> respuesta(HttpStatus status, String mensaje, List<String> detalles) {
        return ResponseEntity.status(status)
                .body(ErrorResponse.of(status.value(), status.getReasonPhrase(), mensaje, detalles));
    }
}
