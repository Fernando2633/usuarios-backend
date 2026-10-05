package mx.ipn.upiiz.usuarios;

import mx.ipn.upiiz.usuarios.domain.exception.BusquedaInvalidaException;
import mx.ipn.upiiz.usuarios.domain.exception.ValidacionUsuarioException;
import mx.ipn.upiiz.usuarios.domain.model.CriterioBusqueda;
import mx.ipn.upiiz.usuarios.domain.model.Usuario;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class UsuarioDominioTest {

    @Test
    void busquedaConMenosDeTresCaracteresFalla() {
        assertThrows(BusquedaInvalidaException.class, () -> new CriterioBusqueda("ju"));
        assertDoesNotThrow(() -> new CriterioBusqueda("efe"));
    }

    @Test
    void usuarioCortoFalla() {
        Usuario u = new Usuario(null, "Juan", "Pérez", "López", "juan@mail.com",
                "juan", "12345678", LocalDate.of(2000, 1, 1));
        ValidacionUsuarioException ex = assertThrows(ValidacionUsuarioException.class, u::validar);
        assertTrue(ex.getMessage().contains("5"));
    }

    @Test
    void passwordCortoFalla() {
        Usuario u = new Usuario(null, "Juan", "Pérez", "López", "juan@mail.com",
                "juanperez", "123", LocalDate.of(2000, 1, 1));
        assertThrows(ValidacionUsuarioException.class, u::validar);
    }
}
