package mx.ipn.upiiz.usuarios.adapters.out.persistence;

import jakarta.persistence.*;

import java.time.LocalDate;

/** Entidad JPA: mapea la tabla usuarios. Solo existe en el adaptador de salida. */
@Entity
@Table(name = "usuarios")
public class UsuarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String nombre;

    @Column(name = "apellido_paterno", nullable = false, length = 50)
    private String apellidoPaterno;

    @Column(name = "apellido_materno", nullable = false, length = 50)
    private String apellidoMaterno;

    @Column(nullable = false, unique = true, length = 100)
    private String correo;

    @Column(nullable = false, unique = true, length = 50)
    private String usuario;

    @Column(nullable = false, length = 100)
    private String password;

    @Column(name = "fecha_nacimiento", nullable = false)
    private LocalDate fechaNacimiento;

    protected UsuarioEntity() {
    }

    public UsuarioEntity(Long id, String nombre, String apellidoPaterno, String apellidoMaterno,
                         String correo, String usuario, String password, LocalDate fechaNacimiento) {
        this.id = id;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.correo = correo;
        this.usuario = usuario;
        this.password = password;
        this.fechaNacimiento = fechaNacimiento;
    }

    public Long getId() { return id; }
    public String getNombre() { return nombre; }
    public String getApellidoPaterno() { return apellidoPaterno; }
    public String getApellidoMaterno() { return apellidoMaterno; }
    public String getCorreo() { return correo; }
    public String getUsuario() { return usuario; }
    public String getPassword() { return password; }
    public LocalDate getFechaNacimiento() { return fechaNacimiento; }
}
