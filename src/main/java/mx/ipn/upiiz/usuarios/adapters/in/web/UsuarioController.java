package mx.ipn.upiiz.usuarios.adapters.in.web;

import jakarta.validation.Valid;
import mx.ipn.upiiz.usuarios.adapters.in.web.dto.RegistroUsuarioRequest;
import mx.ipn.upiiz.usuarios.adapters.in.web.dto.UsuarioBusquedaResponse;
import mx.ipn.upiiz.usuarios.adapters.in.web.dto.UsuarioRegistradoResponse;
import mx.ipn.upiiz.usuarios.domain.model.Usuario;
import mx.ipn.upiiz.usuarios.domain.ports.in.BuscarUsuariosUseCase;
import mx.ipn.upiiz.usuarios.domain.ports.in.RegistrarUsuarioUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Adaptador de entrada (REST). Solo recibe la peticion, la convierte
 * y delega en los casos de uso. No tiene logica de negocio.
 */
@RestController
@RequestMapping("/api/v1/usuarios")
public class UsuarioController {

    private final RegistrarUsuarioUseCase registrarUsuario;
    private final BuscarUsuariosUseCase buscarUsuarios;
    private final UsuarioWebMapper mapper;

    public UsuarioController(RegistrarUsuarioUseCase registrarUsuario,
                             BuscarUsuariosUseCase buscarUsuarios,
                             UsuarioWebMapper mapper) {
        this.registrarUsuario = registrarUsuario;
        this.buscarUsuarios = buscarUsuarios;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<UsuarioRegistradoResponse> registrar(@Valid @RequestBody RegistroUsuarioRequest request) {
        Usuario registrado = registrarUsuario.registrar(mapper.toDomain(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toRegistradoResponse(registrado));
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<UsuarioBusquedaResponse>> buscar(@RequestParam(name = "texto", required = false) String texto) {
        List<UsuarioBusquedaResponse> resultados = buscarUsuarios.buscar(texto)
                .stream()
                .map(mapper::toBusquedaResponse)
                .toList();
        return ResponseEntity.ok(resultados);
    }
}
