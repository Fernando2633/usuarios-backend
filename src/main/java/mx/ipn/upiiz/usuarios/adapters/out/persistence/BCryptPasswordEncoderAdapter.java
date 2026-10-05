package mx.ipn.upiiz.usuarios.adapters.out.persistence;

import mx.ipn.upiiz.usuarios.domain.ports.out.PasswordEncoderPort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

/** Adaptador de salida para cifrar la contraseña con BCrypt antes de guardarla. */
@Component
public class BCryptPasswordEncoderAdapter implements PasswordEncoderPort {

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Override
    public String cifrar(String passwordPlano) {
        return encoder.encode(passwordPlano);
    }
}
