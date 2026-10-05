package mx.ipn.upiiz.usuarios.domain.model;

import mx.ipn.upiiz.usuarios.domain.exception.ValidacionUsuarioException;

import java.time.LocalDate;
import java.util.regex.Pattern;

/**
 * Modelo de dominio. No depende de Spring, JPA ni de la web.
 * Aqui viven las reglas propias del usuario.
 */
public class Usuario {

    public static final int MIN_USUARIO = 5;
    public static final int MIN_PASSWORD = 8;
    private static final Pattern PATRON_CORREO =
            Pattern.compile("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");

    private Long id;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String correo;
    private String usuario;
    private String password;
    private LocalDate fechaNacimiento;

    public Usuario(Long id, String nombre, String apellidoPaterno, String apellidoMaterno,
                   String correo, String usuario, String password, LocalDate fechaNacimiento) {
        this.id = id;
        this.nombre = limpiar(nombre);
        this.apellidoPaterno = limpiar(apellidoPaterno);
        this.apellidoMaterno = limpiar(apellidoMaterno);
        this.correo = correo == null ? null : correo.trim().toLowerCase();
        this.usuario = limpiar(usuario);
        this.password = password;
        this.fechaNacimiento = fechaNacimiento;
    }

    /** Reglas de negocio para dar de alta un usuario nuevo. */
    public void validar() {
        obligatorio(nombre, "El nombre es obligatorio.");
        obligatorio(apellidoPaterno, "El apellido paterno es obligatorio.");
        obligatorio(apellidoMaterno, "El apellido materno es obligatorio.");
        obligatorio(correo, "El correo electrónico es obligatorio.");
        obligatorio(usuario, "El usuario es obligatorio.");
        obligatorio(password, "La contraseña es obligatoria.");
        if (fechaNacimiento == null) {
            throw new ValidacionUsuarioException("La fecha de nacimiento es obligatoria.");
        }
        if (nombre.length() > 50 || apellidoPaterno.length() > 50 || apellidoMaterno.length() > 50) {
            throw new ValidacionUsuarioException("El nombre y los apellidos no pueden pasar de 50 caracteres.");
        }
        if (!PATRON_CORREO.matcher(correo).matches() || correo.length() > 100) {
            throw new ValidacionUsuarioException("El correo electrónico no tiene un formato válido.");
        }
        if (usuario.length() < MIN_USUARIO) {
            throw new ValidacionUsuarioException("El usuario debe tener al menos " + MIN_USUARIO + " caracteres.");
        }
        if (usuario.length() > 50 || usuario.contains(" ")) {
            throw new ValidacionUsuarioException("El usuario no debe tener espacios ni pasar de 50 caracteres.");
        }
        if (password.length() < MIN_PASSWORD) {
            throw new ValidacionUsuarioException("La contraseña debe tener al menos " + MIN_PASSWORD + " caracteres.");
        }
        LocalDate hoy = LocalDate.now();
        if (fechaNacimiento.isAfter(hoy)) {
            throw new ValidacionUsuarioException("La fecha de nacimiento no puede ser una fecha futura.");
        }
        if (fechaNacimiento.isBefore(hoy.minusYears(120))) {
            throw new ValidacionUsuarioException("La fecha de nacimiento no es válida.");
        }
    }

    public String getNombreCompleto() {
        return nombre + " " + apellidoPaterno + " " + apellidoMaterno;
    }

    private static String limpiar(String valor) {
        return valor == null ? null : valor.trim();
    }

    private static void obligatorio(String valor, String mensaje) {
        if (valor == null || valor.isBlank()) {
            throw new ValidacionUsuarioException(mensaje);
        }
    }

    public Long getId() { return id; }
    public String getNombre() { return nombre; }
    public String getApellidoPaterno() { return apellidoPaterno; }
    public String getApellidoMaterno() { return apellidoMaterno; }
    public String getCorreo() { return correo; }
    public String getUsuario() { return usuario; }
    public String getPassword() { return password; }
    public LocalDate getFechaNacimiento() { return fechaNacimiento; }

    public void setId(Long id) { this.id = id; }
    public void setPassword(String password) { this.password = password; }
}
