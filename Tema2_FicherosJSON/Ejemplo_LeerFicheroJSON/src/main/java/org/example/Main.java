package org.example;

import java.util.ArrayList;
import java.io.File;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Main {
    public static final ObjectMapper JSON_MAPPER = new ObjectMapper();

    public static void main(String[] args) {
        // La biblioteca tiene dos métodos principales para la serialización y deserialización.
        //  writeValue(...): se utiliza para convertir el objeto Java en un objeto JSON. (Serializacion)
        // readValue(...): se utiliza para convertir un objeto JSON en un objeto Java. (Deserialización)

        try {
            // convierte un fichero JSON a un arrayList    Deserializacion
            ArrayList<Persona> personas =
                    JSON_MAPPER.readValue(new File("src/main/resources/Persona.json"),
                            JSON_MAPPER.getTypeFactory().constructCollectionType
                                    (ArrayList.class, Persona.class));

            for (Persona p : personas) {
                System.out.print(p.getNombre() + " ");
                System.out.print(p.getApellidos() + " ");
                System.out.print(p.getEdad() + " ");
                System.out.print(p.isCarnet() + " ");
                System.out.println();
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}