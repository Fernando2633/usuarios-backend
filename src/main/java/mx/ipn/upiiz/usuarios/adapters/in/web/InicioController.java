package mx.ipn.upiiz.usuarios.adapters.in.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/** Pagina de inicio de la API: muestra que esta en linea y que endpoints tiene. */
@RestController
public class InicioController {

    @GetMapping("/")
    public Map<String, Object> inicio() {
        Map<String, Object> info = new LinkedHashMap<>();
        info.put("aplicacion", "usuarios-backend");
        info.put("descripcion", "API REST de usuarios con Arquitectura Hexagonal");
        info.put("estado", "en linea");
        info.put("endpoints", List.of(
                "POST /api/v1/usuarios",
                "GET /api/v1/usuarios/buscar?texto=juan"));
        return info;
    }
}
