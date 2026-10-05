package mx.ipn.upiiz.usuarios.adapters.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UsuarioJpaRepository extends JpaRepository<UsuarioEntity, Long> {

    boolean existsByCorreoIgnoreCase(String correo);

    boolean existsByUsuarioIgnoreCase(String usuario);

    /** Busqueda parcial resuelta por MySQL (no se traen todos los usuarios a Java). */
    @Query("""
            SELECT u FROM UsuarioEntity u
            WHERE LOWER(u.nombre) LIKE LOWER(CONCAT('%', :texto, '%'))
               OR LOWER(u.apellidoPaterno) LIKE LOWER(CONCAT('%', :texto, '%'))
               OR LOWER(u.apellidoMaterno) LIKE LOWER(CONCAT('%', :texto, '%'))
               OR LOWER(u.usuario) LIKE LOWER(CONCAT('%', :texto, '%'))
               OR LOWER(CONCAT(u.nombre, ' ', u.apellidoPaterno, ' ', u.apellidoMaterno)) LIKE LOWER(CONCAT('%', :texto, '%'))
            ORDER BY u.nombre, u.apellidoPaterno
            """)
    List<UsuarioEntity> buscarPorTexto(@Param("texto") String texto);
}
