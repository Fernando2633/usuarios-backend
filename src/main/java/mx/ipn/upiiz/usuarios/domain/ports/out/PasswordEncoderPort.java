package mx.ipn.upiiz.usuarios.domain.ports.out;

/** Puerto de salida para cifrar contraseñas sin amarrar el dominio a una libreria. */
public interface PasswordEncoderPort {
    String cifrar(String passwordPlano);
}
