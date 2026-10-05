package mx.ipn.upiiz.usuarios.application.services;

import mx.ipn.upiiz.usuarios.domain.exception.CorreoDuplicadoException;
import mx.ipn.upiiz.usuarios.domain.exception.UsuarioDuplicadoException;
import mx.ipn.upiiz.usuarios.domain.model.Usuario;
import mx.ipn.upiiz.usuarios.domain.ports.in.RegistrarUsuarioUseCase;
import mx.ipn.upiiz.usuarios.domain.ports.out.PasswordEncoderPort;
import mx.ipn.upiiz.usuarios.domain.ports.out.UsuarioRepositoryPort;

/**
 * Implementa el caso de uso de registro.
 * No usa anotaciones de Spring: el bean se crea en config/BeanConfig.
 */
public class RegistrarUsuarioService implements RegistrarUsuarioUseCase {

    private final UsuarioRepositoryPort repositorio;
    private final PasswordEncoderPort passwordEncoder;

    public RegistrarUsuarioService(UsuarioRepositoryPort repositorio, PasswordEncoderPort passwordEncoder) {
        this.repositorio = repositorio;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Usuario registrar(Usuario usuario) {
        usuario.validar();

        if (repositorio.existeCorreo(usuario.getCorreo())) {
            throw new CorreoDuplicadoException("El correo electrónico ya está registrado.");
        }
        if (repositorio.existeUsuario(usuario.getUsuario())) {
            throw new UsuarioDuplicadoException("El nombre de usuario ya está registrado.");
        }

        usuario.setPassword(passwordEncoder.cifrar(usuario.getPassword()));
        return repositorio.guardar(usuario);
    }
}
