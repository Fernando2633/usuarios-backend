package mx.ipn.upiiz.usuarios.domain.ports.in;

import mx.ipn.upiiz.usuarios.domain.model.Usuario;

/** Puerto de entrada: caso de uso "registrar usuario". */
public interface RegistrarUsuarioUseCase {
    Usuario registrar(Usuario usuario);
}
