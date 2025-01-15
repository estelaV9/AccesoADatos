package com.example.esteladevega_practicaapi.controller;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.esteladevega_practicaapi.model.User;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class UserController {
    // localhost:9999/user
    @PostMapping("user")
    public User login(@RequestParam("id") String id, @RequestParam("password") String pwd) {
        if ((id.equals("juan")) && (pwd.equals("juan"))) {
            System.out.println("Token creado");
            String token = getJWTToken(id); // OBTENEMOS EL TOKEN
            User user = new User(); // CREAMOS UNA INSTANCIA DE USUARIO
            user.setId(id);
            user.setPassword(pwd);
            user.setToken(token);
            return user;  // RETORNAMOS EL OBJETO USER CON EL TOKEN GENERADO
        } else
            return null;  // SI LAS CREDENCIALES SON INCORRECTAS, RETORNAMOS NULL
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