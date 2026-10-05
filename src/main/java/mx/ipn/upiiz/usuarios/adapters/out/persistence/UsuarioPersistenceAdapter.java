package mx.ipn.upiiz.usuarios.adapters.out.persistence;

import mx.ipn.upiiz.usuarios.domain.model.Usuario;
import mx.ipn.upiiz.usuarios.domain.ports.out.UsuarioRepositoryPort;
import org.springframework.stereotype.Component;

import java.util.List;

/** Adaptador de salida: implementa el puerto usando Spring Data JPA + MySQL. */
@Component
public class UsuarioPersistenceAdapter implements UsuarioRepositoryPort {

    private final UsuarioJpaRepository jpaRepository;

    public UsuarioPersistenceAdapter(UsuarioJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Usuario guardar(Usuario usuario) {
        UsuarioEntity guardado = jpaRepository.save(toEntity(usuario));
        return toDomain(guardado);
    }

    @Override
    public boolean existeCorreo(String correo) {
        return jpaRepository.existsByCorreoIgnoreCase(correo);
    }

    @Override
    public boolean existeUsuario(String usuario) {
        return jpaRepository.existsByUsuarioIgnoreCase(usuario);
    }

    @Override
    public List<Usuario> buscarPorTexto(String texto) {
        return jpaRepository.buscarPorTexto(texto).stream().map(this::toDomain).toList();
    }

    private UsuarioEntity toEntity(Usuario u) {
        return new UsuarioEntity(u.getId(), u.getNombre(), u.getApellidoPaterno(), u.getApellidoMaterno(),
                u.getCorreo(), u.getUsuario(), u.getPassword(), u.getFechaNacimiento());
    }

    private Usuario toDomain(UsuarioEntity e) {
        return new Usuario(e.getId(), e.getNombre(), e.getApellidoPaterno(), e.getApellidoMaterno(),
                e.getCorreo(), e.getUsuario(), e.getPassword(), e.getFechaNacimiento());
    }
}
