package org.example;

import org.example.Model.Usuario;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        int opcion = -1;
        String mail;
        try {
            ArrayList<Usuario> listaUsuarios = new ArrayList<>();
            Usuario user = new Usuario();
            System.out.println("Bienvenido! ¿Qué desea hacer?");
            do {
                System.out.println("MENU DE OPCIONES:" +
                        "\n1.- Añadir Usuario" +
                        "\n2.- Buscar Usuario" +
                        "\n3.- Total Ingresos" +
                        "\n0.- Salir");
                System.out.println("Enter la opcion elegida: ");
                opcion = reader.nextInt();

                switch (opcion){
                    case 1:
                        ClienteCRUD.insertarCliente(listaUsuarios, user);
                        break;
                    case 2:
                        System.out.println("¿Qué usuario desea buscar?. Mail: ");
                        mail = reader.next();
                        ClienteCRUD.buscarUsuario(listaUsuarios, mail);
                        break;
                    case 3:
                        ClienteCRUD.totalIngreso(listaUsuarios);
                        break;
                } //SWITCH
            } while (opcion != 0); //WHILE

            System.out.println("Hasta pronto!");
            reader.close();
            System.exit(0);
        } catch (Exception e) {
            System.out.println("Uoop! Error!");
        } //TRY
    }
}