package mx.ipn.upiiz.usuarios.domain.exception;

public abstract class DominioException extends RuntimeException {
    protected DominioException(String mensaje) {
        super(mensaje);
    }
}
