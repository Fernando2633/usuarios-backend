package mx.ipn.upiiz.usuarios.adapters.in.web;

import mx.ipn.upiiz.usuarios.adapters.in.web.dto.RegistroUsuarioRequest;
import mx.ipn.upiiz.usuarios.adapters.in.web.dto.UsuarioBusquedaResponse;
import mx.ipn.upiiz.usuarios.adapters.in.web.dto.UsuarioRegistradoResponse;
import mx.ipn.upiiz.usuarios.domain.model.Usuario;
import org.springframework.stereotype.Component;

/** Convierte DTOs web <-> modelo de dominio. */
@Component
public class UsuarioWebMapper {

    public Usuario toDomain(RegistroUsuarioRequest req) {
        return new Usuario(null, req.nombre(), req.apellidoPaterno(), req.apellidoMaterno(),
                req.correo(), req.usuario(), req.password(), req.fechaNacimiento());
    }

    public UsuarioRegistradoResponse toRegistradoResponse(Usuario u) {
        return new UsuarioRegistradoResponse("Usuario registrado correctamente.",
                u.getId(), u.getNombreCompleto(), u.getCorreo(), u.getUsuario());
    }

    public UsuarioBusquedaResponse toBusquedaResponse(Usuario u) {
        return new UsuarioBusquedaResponse(u.getId(), u.getNombre(),
                u.getApellidoPaterno(), u.getApellidoMaterno(), u.getUsuario());
    }
}
