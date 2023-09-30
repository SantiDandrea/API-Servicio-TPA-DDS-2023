# Servicio de fusión de comunidades

El servicio sugerirá fusiones de comunidades. Para ello analizará las comunidades existentes y
buscará las siguientes coincidencias (todos los parámetros numéricos definidos deben ser fácilmente
modificables):
* Coincidan en más del 75% de los establecimientos observados
* Coincidan en más del 75% de los servicios estándares observados
* Tengan un mismo grado de confianza
* Tengan un 5% de usuarios en común

Se deben observar las siguientes condiciones:
* No sé podrá tener 2 propuestas de fusión para una misma comunidad a la vez.
* No sé podrá repetir la propuesta de fusión de 2 comunidades por 6 meses.

El servicio además recibirá la aceptación o rechazo de una fusión. En caso de aceptación procederá a
generar la fusión creando una nueva comunidad e inactivando las anteriores. La nueva comunidad será
una combinación de las comunidades originales.

La API proporciona dos métodos: `GET` y `POST` para gestionar sugerencias. A continuación se describe cada uno de los métodos.

## 1. Obtener todas las sugerencias

- **URL:** `/api/sugerencias`
- **Método HTTP:** `GET`
- **Descripción:** Este método permite obtener una lista de todas las sugerencias disponibles en el sistema.
- **Respuesta exitosa (Código 200):** Devuelve un arreglo de objetos `Sugerencia`.
  
## 2. Aceptar sugerencia y crear nueva comunidad

- **URL:** `/api/sugerencias/{sugerenciaId}`
- **Método HTTP:** `POST`
- **Descripción:** Este método permite aceptar una sugerencia y crear una nueva comunidad basada en esa sugerencia.
- **Parámetros de Ruta:**
  - `sugerenciaId` (entero): ID de la sugerencia que se está aceptando.
- **Cuerpo de la Solicitud:** Debe incluir un objeto de tipo `AceptacionSugerencia`.
- **Respuestas:**
  - Respuesta exitosa (Código 201): Devuelve un objeto de tipo `Comunidad`.
  - Respuesta de error (Código 400): Devuelve un objeto `BadRequestResponse` en caso de solicitud incorrecta.
  - Respuesta de error (Código 500): Devuelve un objeto `InternalServerErrorResponse` en caso de error interno del servidor.

## Ejemplos de Uso

### Obtener todas las sugerencias (GET)

GET /api/sugerencias

### Aceptar sugerencia y crear nueva comunidad (POST)

POST /api/sugerencias/{sugerenciaId}
{
  "nombreNuevaComunidad": "NombreDeEjemplo",
}
