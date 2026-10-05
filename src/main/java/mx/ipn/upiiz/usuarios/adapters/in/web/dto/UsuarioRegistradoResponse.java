package mx.ipn.upiiz.usuarios.adapters.in.web.dto;

/** DTO de salida del registro (sin contraseña). */
public record UsuarioRegistradoResponse(
        String mensaje,
        Long id,
        String nombreCompleto,
        String correo,
        String usuario
) {
}
