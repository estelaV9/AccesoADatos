package org.example;

import org.example.Model.Usuario;

import java.util.ArrayList;
import java.util.Scanner;

public class ClienteCRUD {
    public static void insertarCliente (ArrayList<Usuario> lista){
        Scanner reader = new Scanner(System.in);
        boolean usuarioRegistrado = false; // atributo boolean para definir si un usuario esta o no registrado
        Usuario user;
        System.out.println("**************AÑADIR CLIENTE*************");
        System.out.println("¿Qué usuario desea añadir? Mail: ");
        String mail = reader.next();
        for(Usuario us: lista){
            if(us.getMailCl().equals(mail)){
                usuarioRegistrado = true;
                break;
            }
        } // for para buscar ese usuario

        if(usuarioRegistrado){
            System.out.println("Ese mail ya esta registrado por otro usuario. Prueba con otro, por favor");
        } else {
            try {
                System.out.println("Contraseña: ");
                String contraseña = reader.next();

                System.out.println("Descuento: ");
                double descuento = reader.nextDouble();

                System.out.println("¿Es Cliente Premium?\n1-Si\n2-No");
                int clientePremium = reader.nextInt();

                while (clientePremium > 2 || clientePremium < 1) {
                    System.out.println("Elige una opción entre 1 y 2");
                    clientePremium = reader.nextInt();
                } // while para validar que el cliente solo elija una de las 2 opciones
                user = new Usuario(mail, contraseña, descuento, clientePremium); // SE AÑADE EL USUARIO
                lista.add(user);
            } catch (NumberFormatException e) {
                System.out.println("Por favor, ingresa un número válido para el descuento.");
            } // EXCEPCION PARA MANEJAR QUE EL USUARIO NO PONGA UNA LETRA EN EL DESCUENTO
        } // if si ese usuario existe o no
    } // METODO PARA INSERTAR UN NUEVO CLIENTE


    public static void buscarUsuario (ArrayList<Usuario> lista, String mail){
        boolean usuarioExistente = false; // atributo boolean para definir si ese usuario esta o no en la lista
        for(Usuario user : lista){
            if(user.getMailCl().equals(mail)){
                usuarioExistente = true;
                break;
            }
        } // RECORRER LOS USUARIO PARA VER SI EXISTE O NO
        if(usuarioExistente){
            System.out.println("El usuario " + mail + " esta registrado");
        } else {
            System.out.println("No se ha encontrado ningun usuario con ese mail");
        } // if si el usuario se encuentra, se mostrara un mensaje
    } // METODO PARA BUSCAR UN USUARIO POR MAIL


    public static void totalIngreso (ArrayList<Usuario> lista){
        double totalIngresos = 0; // se guarda el total de ingresos segun su rol (si es premium o no)
                                  // restando su respectivo descuento establecido
        for(Usuario user : lista){
            if(user.isPremiumUs() == 1){
                totalIngresos = totalIngresos + 35.5 - user.getDescuentoCl();
            } else {
                totalIngresos = totalIngresos + 20.5 - user.getDescuentoCl();
            }
        } // SE RECORRE TODOS LOS USUARIOS Y DEPENDIENDO SI ES PREMIUM O NO SE SUMA UNA CANTIDAD U OTRA
        System.out.println("El total de ingresos es de: " + totalIngresos);
    } // METODO PARA CALCULAR EL TOTAL DE INGRESOS DE LOS USUARIOS
}
