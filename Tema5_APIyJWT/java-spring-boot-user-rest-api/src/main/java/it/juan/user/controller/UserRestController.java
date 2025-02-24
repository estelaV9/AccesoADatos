package it.juan.user.controller;

import it.juan.user.entity.User;
import it.juan.user.service.UserService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/*

Un poco de teoria que nos va a ser utili luego para entender las cosas

Ejemplo 1:
@RequestParam  se utiliza principalmente con fines de filtrado. Digamos que desea obtener el libro de George Martin:
GET localhost:8080/books?author=georgemartin
aquí lo pasamos author=georgemartincomo parámetro de solicitud. Supuestamente esto obtendrá todos los libros de Martin, por ejemplo,
la serie Juego de Tronos. Esto se utilizará principalmente para la operación GET.
Veamos un ejemplo:

@GetMapping("/api/usuarios")
@ResponseBody
public String obtenerUsuario(@RequestParam String id) {
    return "ID del usuario: " + id;
}
Para este ejemplo la aplicación espera recibir un parámetro id en la URL. Un ejemplo de solicitud sería:
http://localhost:8080/api/usuarios?id=123. Aquí, el valor 123 se pasará como parámetro y el método lo procesará devolviendo el ID del usuario.

Ejemplo 2:
@PathVariable  se utiliza principalmente para obtener objetos individuales o datos. Digamos que desea obtener un libro por su identificación:
GET localhost:8080/books/1
aquí pasamos 1 como variable de ruta. Supuestamente esto obtendrá el libro 1 con id 1, ejemplo de la primera parte del libro de Juego de Tronos. Esto se utilizará principalmente para la operación BORRAR/OBTENER.

EJEMPLO

@GetMapping("/productos/{id}")
public String obtenerProducto(@PathVariable("id") String id) {
    return "Producto con ID: " + id;
}

OTRO EJEMPLO

@GetMapping("/empleados/{id}/departamento/{nombre}")
public String obtenerEmpleadoPorDepartamento(@PathVariable("id") String id, @PathVariable("nombre") String departamento) {
    return "Empleado con ID: " + id + " del departamento: " + departamento;
}

Ejemplo 3:
@RequestBody  se utiliza principalmente para guardar objetos (o datos).
 */

//Indiciamos que es un controlador rest
@RestController
@RequestMapping("/api") //esta sera la raiz de la url, es decir http://127.0.0.1:8080/api/

public class UserRestController {

    //Inyectamos el servicio para poder hacer uso de el
    @Autowired
    private UserService userService;

    /*Este método se hará cuando por una petición GET (como indica la anotación) se llame a la url
    http://127.0.0.1:8080/api/users*/
    @GetMapping("/users")
    public List<User> findAll() {
        //retornará todos los usuarios
        return userService.findAll();
    }

    /*Este método se hará cuando por una petición GET (como indica la anotación) se llame a la url + el id de un usuario
    http://127.0.0.1:8080/api/users/1 */
    @GetMapping("/users/{userId}")
    public User getUser(@PathVariable int userId) {
        User user = userService.findById(userId);

        if (user == null) {
            throw new RuntimeException("User id not found -" + userId);
        }
        //retornará al usuario con id pasado en la url
        return user;
    }

    /*Este método se hará cuando por una petición POST (como indica la anotación) se llame a la url
    http://127.0.0.1:8080/api/users/  */
    @PostMapping("/users")
    public User addUser(@RequestBody User user) {
        user.setId(0);

        //Este metodo guardará al usuario enviado
        userService.save(user);

        return user;

    }

    /*Este método se hará cuando por una petición PUT (como indica la anotación) se llame a la url
    http://127.0.0.1:8080/api/users/  */
    @PutMapping("/users")
    public User updateUser(@RequestBody User user) {

        userService.save(user);

        //este metodo actualizará al usuario enviado

        return user;
    }

    /*Este método se hará cuando por una petición DELETE (como indica la anotación) se llame a la url + id del usuario
    http://127.0.0.1:8080/api/users/1  */
    @DeleteMapping("users/{userId}")
    public String deteteUser(@PathVariable int userId) {

        User user = userService.findById(userId);

        if (user == null) {
            throw new RuntimeException("User id not found -" + userId);
        }

        userService.deleteById(userId);

        //Esto método, recibira el id de un usuario por URL y se borrará de la bd.
        return "Deleted user id - " + userId;
    }
}
