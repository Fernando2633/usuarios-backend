package mx.ipn.upiiz.usuarios.domain.ports.out;

import mx.ipn.upiiz.usuarios.domain.model.Usuario;

import java.util.List;

/** Puerto de salida: lo que el dominio necesita de la persistencia. */
public interface UsuarioRepositoryPort {
    Usuario guardar(Usuario usuario);
    boolean existeCorreo(String correo);
    boolean existeUsuario(String usuario);
    List<Usuario> buscarPorTexto(String texto);
}
