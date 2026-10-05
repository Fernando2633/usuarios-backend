package mx.ipn.upiiz.usuarios.adapters.in.web.dto;

import java.time.LocalDateTime;
import java.util.List;

public record ErrorResponse(
        int status,
        String error,
        String mensaje,
        List<String> detalles,
        LocalDateTime fecha
) {
    public static ErrorResponse of(int status, String error, String mensaje, List<String> detalles) {
        return new ErrorResponse(status, error, mensaje, detalles, LocalDateTime.now());
    }
}
