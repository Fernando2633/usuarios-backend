package mx.ipn.upiiz.usuarios.domain.exception;

public class UsuarioDuplicadoException extends DominioException {
    public UsuarioDuplicadoException(String mensaje) {
        super(mensaje);
    }
}
