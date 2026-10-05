package mx.ipn.upiiz.usuarios.domain.ports.in;

import mx.ipn.upiiz.usuarios.domain.model.Usuario;

import java.util.List;

/** Puerto de entrada: caso de uso "buscar usuarios". */
public interface BuscarUsuariosUseCase {
    List<Usuario> buscar(String texto);
}
