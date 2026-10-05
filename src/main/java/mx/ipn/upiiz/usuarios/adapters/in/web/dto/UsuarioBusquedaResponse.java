package mx.ipn.upiiz.usuarios.adapters.in.web.dto;

/** DTO de salida de la busqueda: solo lo necesario, nunca la contraseña. */
public record UsuarioBusquedaResponse(
        Long id,
        String nombre,
        String apellidoPaterno,
        String apellidoMaterno,
        String usuario
) {
}
