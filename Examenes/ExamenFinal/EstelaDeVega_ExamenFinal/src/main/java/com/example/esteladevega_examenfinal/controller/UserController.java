package com.example.esteladevega_examenfinal.controller;

import com.example.esteladevega_examenfinal.model.User;
import com.example.esteladevega_examenfinal.service.UserServices;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api") // RUTA BASE DE LAS PETICIONES DEL CONTROLADOR DE USER
public class UserController {

    private final UserServices userServices; // SERVICIO USUARIO

    public UserController(UserServices userService) {
        this.userServices = userService;
    } // CONSTRUCTOR PARA AÑADIR EL SERVICIO USUARIO EN EL CONTROLADOR

    @PostMapping("/loginUser")
    public ResponseEntity<?> login(@RequestParam("username") String name, @RequestParam("password") String pwd) {
        try {
            // ENCRIPTAMOS LA CONTRASEÑA
            String pwdEncriptada = DigestUtils.sha256Hex(pwd);
            // VERIFICAMOS SI EL USUARIO EXISTE EN LA BASE DE DATOS
            Optional<User> optionalUser = userServices.isExistsUser(name, pwdEncriptada);

            if (optionalUser.isEmpty()) {
                return new ResponseEntity<>(
                        "Usuario o contraseña incorrectos", HttpStatus.UNAUTHORIZED);
            } // SI EL USUARIO NO EXISTE, SE LANZA UNA EXCEPCIÓN 401

            // SI EL USUARIO EXISTE, SE CREA EL TOKEN
            System.out.println("Token creado");
            String token = getJWTToken(name); // OBTENEMOS EL TOKEN
            User user = userServices.isExistsUser(name, pwdEncriptada).get();
            userServices.save(user);

            return ResponseEntity.ok(token);  // RETORNAMOS EL TOKEN GENERADO

        } catch (Exception e) {
            // SE MANEJA UNA EXCEPCION Y SE LANZA UN BAD REQUEST SI FALLA
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Error al logear usuario", e);
        }
    } // METODO PARA INTERCEPTAR LAS PETICIONES POST AL endpoint /user


    private String getJWTToken(String id) {
        String secretKey = "mySecretKey";
        // ESTABLECEMOS LOS ROLES DEL USUARIO
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList("ROLE_USER");
        String token = Jwts
                .builder()
                .setId("softtekJWT") // ID DEL TOKEN
                .setSubject(id)
                .claim("authorities",
                        grantedAuthorities.stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis())) // FECHA DE EMISION DEL TOKEN
                .setExpiration(new Date(System.currentTimeMillis() + 600000)) // FECHA DE EXPIRACION (10 MINUTOS)
                .signWith(SignatureAlgorithm.HS512,
                        secretKey.getBytes()).compact();

        return "Bearer " + token; // RETORNAMOS EL TOKEN EN FORMATO BEARER
    } // METODO PARA CONSTRUIR EL TOKEN, SE USARA EL GrantedAuthority PARA AUTORIZAR LAS PETICIONES
}