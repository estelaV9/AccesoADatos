package org.example;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

public class Main {
    public static void main(String[] args) {
        Properties properties= new Properties();
        String host="", port="", name="", username="", password="";
        try {
            properties.load(new FileInputStream(new File("src/main/resources/configuration/database.properties")));
            //System.out.println(properties.get("driver"));
            host=String.valueOf(properties.get("host"));
            port=String.valueOf(properties.get("port"));
            name=String.valueOf(properties.get("name"));
            username=String.valueOf(properties.get("username"));
            password=String.valueOf(properties.get("password"));
            System.out.println(host+"  "+port+"  "+name+"  "+username+"  "+password);

            // PARA LEER LAS PROPIEDADES UTILIZAMOS EL OBJETO ENUMERATION
            Enumeration<Object> claves = properties.keys();
            while (claves.hasMoreElements()) {
                Object clave = claves.nextElement();
                System.out.println(clave.toString() + " - " + properties.get(clave).toString());
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}