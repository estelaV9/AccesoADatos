package es.softtek.jwtDemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import es.softtek.jwtDemo.security.JWTAuthorizationFilter;

@SpringBootApplication
public class JwtDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(JwtDemoApplication.class, args);
	}


	/*
	La clase interna WebSecurityConfig, decorada con @EnableWebSecurity y @Configuration,
	nos permite especificar la configuración de acceso a los recursos publicados.
	En este caso se permiten todas las llamadas al controlador /user, y al directorio categoria
	pero el resto de las llamadas requieren autenticación.
	 */
	@EnableWebSecurity
	@Configuration
	class WebSecurityConfig extends WebSecurityConfigurerAdapter {

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.csrf().disable()
				.addFilterAfter(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
				.authorizeRequests()
				.antMatchers(HttpMethod.POST, "/user").permitAll()//antMatchers OBSOLETO
					//.requestMatchers(HttpMethod.POST, "/user").permitAll()
					.antMatchers("/categoria/**").permitAll()
				.anyRequest().authenticated();//cualquier solicitud debe ser autenticada, de lo contrario, mi aplicación Spring devolverá una respuesta 401.

			/*
			        El método más común para especificar una URL es a través de antMatchers . Por lo tanto, si queremos proteger:

			http://www.ejemplo.com/static	Abierto a todos: CSS, JavaScript
			http://www.example.com/register	Abierto a todos
			http://www.ejemplo.com/user/	ROLE_USER o ROLE_ADMIN – Área de usuario
			http://www.ejemplo.com/admin/
			ROLE_ADMIN solamente y restringido a IPADDRESS – Área de administración

		También especificamos páginas o directorios individuales:


. antMatchers ( "register.html" ); // Individuo

. antMatchers ( "/admin/**" ); // Directorio

			El ejemplo anterior Seria de la siguiente forma
 			.antMatchers("/static","/register").permitAll()
           .antMatchers("/user/**").hasRoles("USER", "ADMIN") // can pass multiple roles
           .antMatchers("/admin/**").access("hasRole('ADMIN') and hasIpAddress('123.123.123.123')") // pass SPEL using access method
           .anyRequest().authenticated()

			 */
		}
	}

}

