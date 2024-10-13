package com.example.esteladevega_ejercicioformulario.Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
    public static boolean isValidMail(String mail) {
        Pattern pattern =
                Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)" + // INDICAMOS QUE PUEDA TENER CARACTERES MAY,MIN. NUM, ETC
                        "*@" // LUEGO DEBE CONTENER UN @
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]{2,})$"); //DEBE TENER UN PUNTO Y LUEGO TENER MIN 2 CARACTERES DESPUES DE ESE PUNTO
        // LLAMAMOS A LA CLASE PATTERN Y USAMOS EL METODO MATCHER. Y PASAMOS COMO PARAMETRO EL CORREO
        Matcher matcher = pattern.matcher(mail);
        // SI ENCUENTRA COINCIDENCIA CON EL CORREO Y LA EXPRESION REGULAR
        return matcher.find();
    } // VALIDAR CON EXPRESIONES REGULARES EL MAIL (texto) @ (texto) . (texto)

    public static boolean isValidPassword(String password) {
        if (password.length() >= 8) { // TIENE QUE TENER MINIMO 8 CARACTERES
            boolean mayuscula = false;
            boolean numero = false;
            boolean especial = false;

            // TIENE QUE CONTENER CARACTERES ESPECIALES
            Pattern special = Pattern.compile("[?!¡@¿.,´)]");
            Matcher hasSpecial = special.matcher(password);

            // RECORRER LA CONTRASEÑA ARA VALIDAR QUE TIENE TODOS LOS REQUISITOS
            for (int i = 0; i < password.length(); i++) {
                char l = password.charAt(i);
                // SE USA LA CLASE Character PARA OBTENER INFORMACION SOBRE LOS CARACTERES
                if (Character.isDigit(l)) {// CONTIENE MINIMO UN NUMERO.
                    numero = true;
                }
                if (Character.isUpperCase(l)) { // CONTIENE MINIMO UNA MAYUSCULA
                    mayuscula = true;
                }
                if (hasSpecial.find()) { // CONTIENE CARACTERES ESPECIALES
                    especial = true;
                }
            }
            return numero && mayuscula && especial;
        } else {
            return false;
        }
    } // VALIDAR LA CONTRASEÑA

    public static boolean contieneNumeros(String texto) {
        /** Este método verifica si la cadena de texto proporcionada contiene solamente números.
         * Se utiliza el método matches() para comprobar si la cadena coincide con la expresión regular.
         * En este caso, la expresión regular utilizada es "\\d+", que significa:
         *  - '\\d': representa cualquier dígito (del 0 al 9).
         *  - '+': indica que debe haber al menos uno o más dígitos consecutivos.
         * Si la cadena contiene solo números, el método `matches()` devolverá true.
         * Si la cadena contiene cualquier otro carácter que no sea un número, devolverá false. */
        return texto.matches("\\d+");
    } // VALIDACION PARA QUE LOS CAMPOS NO CONTENGAN CADENAS
}
