package mx.ipn.upiiz.usuarios.domain.model;

import mx.ipn.upiiz.usuarios.domain.exception.BusquedaInvalidaException;

/**
 * Value object para el texto de busqueda.
 * La regla de minimo 3 caracteres vive en el dominio (no solo en JavaScript).
 */
public record CriterioBusqueda(String texto) {

    public static final int MIN_CARACTERES = 3;

    public CriterioBusqueda {
        texto = texto == null ? "" : texto.trim();
        if (texto.length() < MIN_CARACTERES) {
            throw new BusquedaInvalidaException(
                    "La búsqueda debe contener al menos " + MIN_CARACTERES + " caracteres.");
        }
    }
}
