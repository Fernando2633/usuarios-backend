package mx.ipn.upiiz.usuarios.config;

import mx.ipn.upiiz.usuarios.application.services.BuscarUsuariosService;
import mx.ipn.upiiz.usuarios.application.services.RegistrarUsuarioService;
import mx.ipn.upiiz.usuarios.domain.ports.in.BuscarUsuariosUseCase;
import mx.ipn.upiiz.usuarios.domain.ports.in.RegistrarUsuarioUseCase;
import mx.ipn.upiiz.usuarios.domain.ports.out.PasswordEncoderPort;
import mx.ipn.upiiz.usuarios.domain.ports.out.UsuarioRepositoryPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Aqui se "conectan" los puertos con los adaptadores.
 * Asi la capa de aplicacion no depende de Spring.
 */
@Configuration
public class BeanConfig {

    @Bean
    public RegistrarUsuarioUseCase registrarUsuarioUseCase(UsuarioRepositoryPort repositorio,
                                                           PasswordEncoderPort passwordEncoder) {
        return new RegistrarUsuarioService(repositorio, passwordEncoder);
    }

    @Bean
    public BuscarUsuariosUseCase buscarUsuariosUseCase(UsuarioRepositoryPort repositorio) {
        return new BuscarUsuariosService(repositorio);
    }
}
