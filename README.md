# usuarios-backend

API REST de usuarios (registro y búsqueda) hecha con **Spring Boot** y **Arquitectura Hexagonal (Puertos y Adaptadores)**.

Fernando Antonio García Ruiz – 7CM1 – UPIIZ IPN

## Estructura

```
src/main/java/mx/ipn/upiiz/usuarios
├── domain
│   ├── model          -> Usuario, CriterioBusqueda (reglas de negocio)
│   ├── exception      -> excepciones del dominio
│   └── ports
│       ├── in         -> RegistrarUsuarioUseCase, BuscarUsuariosUseCase
│       └── out        -> UsuarioRepositoryPort, PasswordEncoderPort
├── application
│   └── services       -> RegistrarUsuarioService, BuscarUsuariosService
├── adapters
│   ├── in/web         -> UsuarioController, DTOs, mapper, GlobalExceptionHandler
│   └── out/persistence-> UsuarioEntity, UsuarioJpaRepository, UsuarioPersistenceAdapter, BCrypt
└── config             -> BeanConfig (une puertos y adaptadores), CorsConfig
```

## Endpoints

| Método | Ruta | Descripción |
|---|---|---|
| POST | `/api/v1/usuarios` | Registra un usuario |
| GET | `/api/v1/usuarios/buscar?texto=juan` | Búsqueda parcial (mínimo 3 caracteres) |

Ejemplo de JSON para registrar:

```json
{
  "nombre": "Juan",
  "apellidoPaterno": "Pérez",
  "apellidoMaterno": "López",
  "correo": "juan@correo.com",
  "usuario": "juanperez",
  "password": "12345678",
  "fechaNacimiento": "2001-03-15"
}
```

`GET /api/v1/usuarios/buscar?texto=ju` → **400 Bad Request** `"La búsqueda debe contener al menos 3 caracteres."`

## Correr en local

1. Tener MySQL corriendo y ejecutar `red_social.sql`.
2. Ajustar usuario/contraseña de MySQL (variables `DB_USER`, `DB_PASSWORD` o en `application.properties`).
3. `mvn spring-boot:run` → http://localhost:8080

## Variables de entorno (Render)

| Variable | Ejemplo |
|---|---|
| `DB_URL` | `jdbc:mysql://HOST:PUERTO/red_social?useSSL=true&serverTimezone=America/Mexico_City` |
| `DB_USER` | usuario de MySQL |
| `DB_PASSWORD` | contraseña de MySQL |
| `CORS_ORIGINS` | `https://usuarios-frontend-fagr.onrender.com` |

Render no tiene MySQL gratis, así que la BD se puede crear gratis en Aiven, Clever Cloud o Railway y solo se pega la URL en `DB_URL`.
En Render: **New → Web Service → Docker** (usa el `Dockerfile` de este repo).
