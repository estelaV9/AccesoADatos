# Tema 8: ¿Qué es JWT?
## Introducción
JWT significa **JSON Web Token**. Es un estándar abierto (RFC 7519) que permite transmitir información de forma segura entre partes como un objeto JSON. Se firma digitalmente, lo que garantiza su autenticidad.

El cliente solo se autentica con el servidor una vez. Luego, el servidor emite un **JWT**, que el cliente usará en futuras solicitudes para autenticarse sin reenviar credenciales.

## Flujo de trabajo de JWT
1. **Autenticación**: El cliente envía credenciales al servidor.
2. **Generación del Token**: El servidor valida la identidad y genera un JWT.
3. **Acceso a Recursos**: El cliente usa el JWT para acceder a la API protegida.
4. **Validación del Token**: El servidor verifica el JWT antes de otorgar acceso.

## Estructura del Token JWT
El JWT consta de tres partes codificadas en Base64:
```
header.payload.signature
```
- **Header**: Contiene el algoritmo de cifrado y el tipo de token.
- **Payload**: Contiene los datos y claims (ej. ID de usuario, roles).
- **Signature**: Firma digital para garantizar la integridad del token.

**Nota**: La información en el payload **no es confidencial**. No se deben almacenar contraseñas ni datos sensibles sin cifrado adicional.


## Implementación de un API con JWT en Spring Boot
### 1. Creación del Endpoint Protegido
```java
@RestController
public class HelloWorldController {
   @RequestMapping("hello")
   public String helloWorld(@RequestParam(value="name", defaultValue="World") String name) {
      return "Hello Mundo";
   }
}
```

### 2. Configuración de Seguridad con Spring Security
```java
@EnableWebSecurity
@Configuration
class WebSecurityConfig extends WebSecurityConfigurerAdapter {
   @Override
   protected void configure(HttpSecurity http) throws Exception {
      http.csrf().disable()
         .addFilterAfter(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
         .authorizeRequests()
         .antMatchers(HttpMethod.POST, "/user").permitAll()
         .anyRequest().authenticated();
   }
}
```

### 3. Implementación del Endpoint de Autenticación
```java
@PostMapping("user")
public User login(@RequestParam("user") String username, @RequestParam("password") String pwd) {
   if(username.equals("juan") && pwd.equals("juan")) {
      String token = getJWTToken(username);
      User user = new User();
      user.setUser(username);
      user.setToken(token);
      return user;
   }
   return null;
}
```

### 4. Generación del Token JWT
```java
private String getJWTToken(String username) {
   String secretKey = "mySecretKey";
   List<GrantedAuthority> grantedAuthorities = AuthorityUtils
      .commaSeparatedStringToAuthorityList("ROLE_USER");
      
   return "Bearer " + Jwts.builder()
      .setId("softtekJWT")
      .setSubject(username)
      .claim("authorities", grantedAuthorities.stream()
         .map(GrantedAuthority::getAuthority)
         .collect(Collectors.toList()))
      .setIssuedAt(new Date(System.currentTimeMillis()))
      .setExpiration(new Date(System.currentTimeMillis() + 600000))
      .signWith(SignatureAlgorithm.HS512, secretKey.getBytes()).compact();
}
```

### 5. Implementación del Filtro de Autorización
```java
@Override
protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
   throws ServletException, IOException {
   try {
      if (checkJWTToken(request, response)) {
         Claims claims = validateToken(request);
         if (claims.get("authorities") != null) {
            setUpSpringAuthentication(claims);
         } else {
            SecurityContextHolder.clearContext();
         }
      } else {
         SecurityContextHolder.clearContext();
      }
      chain.doFilter(request, response);
   } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException e) {
      response.setStatus(HttpServletResponse.SC_FORBIDDEN);
      response.sendError(HttpServletResponse.SC_FORBIDDEN, e.getMessage());
   }
}
```

## Prueba de la API con Postman
1. **Arrancar el servidor** con:
   ```sh
   mvn spring-boot:run
   ```
2. **Hacer una petición GET a `/hello`** (recibe error 403 porque está protegido).
3. **Hacer una petición POST a `/user`** con usuario y contraseña para obtener el token.
4. **Hacer una petición GET a `/hello`** incluyendo el token en la cabecera `Authorization`.

## Recursos adicionales
- [JWT en Spring Boot - TechGeekNext](https://www.techgeeknext.com/spring/spring-boot-security-token-authentication-jwt)
- [Autenticación con Spring y JWT - Softtek](https://blog.softtek.com/es/autenticando-apis-con-spring-y-jwt)
- [Spring Security con JWT - Amo el Código](https://amoelcodigo.com/spring-security-roles-jwt/)

---
>_Estela de Vega Martín | IES Ribera de Castilla 24/25_
