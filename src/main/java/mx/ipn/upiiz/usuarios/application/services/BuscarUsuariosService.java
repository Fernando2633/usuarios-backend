package mx.ipn.upiiz.usuarios.application.services;

import mx.ipn.upiiz.usuarios.domain.model.CriterioBusqueda;
import mx.ipn.upiiz.usuarios.domain.model.Usuario;
import mx.ipn.upiiz.usuarios.domain.ports.in.BuscarUsuariosUseCase;
import mx.ipn.upiiz.usuarios.domain.ports.out.UsuarioRepositoryPort;

import java.util.List;

/** Implementa el caso de uso de busqueda. La busqueda se resuelve en la BD, no en Java. */
public class BuscarUsuariosService implements BuscarUsuariosUseCase {

    private final UsuarioRepositoryPort repositorio;

    public BuscarUsuariosService(UsuarioRepositoryPort repositorio) {
        this.repositorio = repositorio;
    }

    @Override
    public List<Usuario> buscar(String texto) {
        CriterioBusqueda criterio = new CriterioBusqueda(texto); // valida minimo 3 caracteres
        return repositorio.buscarPorTexto(criterio.texto());
    }
}
