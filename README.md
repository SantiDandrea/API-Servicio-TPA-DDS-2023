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
