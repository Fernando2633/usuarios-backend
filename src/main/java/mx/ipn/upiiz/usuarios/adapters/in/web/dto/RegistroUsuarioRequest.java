package mx.ipn.upiiz.usuarios.adapters.in.web.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

/** DTO de entrada para el registro. */
public record RegistroUsuarioRequest(
        @NotBlank(message = "El nombre es obligatorio.")
        @Size(max = 50, message = "El nombre no puede pasar de 50 caracteres.")
        String nombre,

        @NotBlank(message = "El apellido paterno es obligatorio.")
        @Size(max = 50, message = "El apellido paterno no puede pasar de 50 caracteres.")
        String apellidoPaterno,

        @NotBlank(message = "El apellido materno es obligatorio.")
        @Size(max = 50, message = "El apellido materno no puede pasar de 50 caracteres.")
        String apellidoMaterno,

        @NotBlank(message = "El correo electrónico es obligatorio.")
        @Email(message = "El correo electrónico no tiene un formato válido.")
        @Size(max = 100, message = "El correo no puede pasar de 100 caracteres.")
        String correo,

        @NotBlank(message = "El usuario es obligatorio.")
        @Size(min = 5, max = 50, message = "El usuario debe tener al menos 5 caracteres.")
        String usuario,

        @NotBlank(message = "La contraseña es obligatoria.")
        @Size(min = 8, max = 100, message = "La contraseña debe tener al menos 8 caracteres.")
        String password,

        @NotNull(message = "La fecha de nacimiento es obligatoria.")
        @Past(message = "La fecha de nacimiento debe ser una fecha pasada.")
        @JsonFormat(pattern = "yyyy-MM-dd")
        LocalDate fechaNacimiento
) {
}
