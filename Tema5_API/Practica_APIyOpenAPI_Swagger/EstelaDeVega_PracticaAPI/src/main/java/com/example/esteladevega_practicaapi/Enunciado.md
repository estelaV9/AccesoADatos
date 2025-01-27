# Práctica Obligatoria API Y Open API(Swagger)
Crea una API que ofrezca servicios web de búsqueda de hoteles.

Se mantendrá un base de datos de hoteles(`nombre`, `descripción`, `categoría`, `¿piscina?`, `localidad`) 
y de las habitaciones de los mismos(`tamaño 1 ó 2 personas`, `precio/noche`, `¿incluye desayuno?`, `¿ocupada?`).

Deberá ofrecer, sobre esos datos, las siguientes operaciones:
   - Búsqueda de hotel por localidad o categoría ✅
   - Búsqueda de habitaciones de un hotel por tamaño y precio (rango minimo→máximo). Solo mostrará aquellas habitaciones que estén marcadas como libres  ✅
   - Registrar un nuevo hotel ✅
   - Registrar una nueva habitación a un hotel ✅
   - Eliminar una habitación determinada de un hotel ✅
   - Modificar una habitación para indicar que está ocupada ✅


## API REST
Adaptar la API REST para darla seguridad utilizando JWT. ✅

Crear al menos un usuario con `id = juan` y `password =juan` ✅

Las opciones a y b podrán ser accedidas por cualquier usuario. ✅

El resto de opciones SOLO serán accesibles por usuarios autorizados y utilizando JWT. ✅

---
>_Estela de Vega Martín | IES Ribera de Castilla 24/25_