package org.example;

import org.example.Model.Cliente;
import org.example.Model.Usuario;

import java.util.ArrayList;
import java.util.Scanner;

public class ClienteCRUD {
    public static ArrayList insertarCliente (ArrayList lista, Cliente cli){
        Scanner reader = new Scanner(System.in);
        String mail, contraseña;
        double descuento;
        boolean semaforo = false; // atributo boolean para definir si un usuario esta o no registrado
        int clientePremium;
        Usuario user;
        System.out.println("**************AÑADIR CLIENTE*************");
        System.out.println("¿Qué usuario desea añadir?. Mail: ");
        mail = reader.next();

        do{
            for(Usuario us: lista){
                if(us.getMailCl().equals(mail)){
                    semaforo = true;
                    break;
                } else {
                    semaforo = false;
                }
            } // for para buscar ese usuario
            if(semaforo){
                System.out.println("Ese mail ya esta registrado por otro usuario. Prueba con otro, por favor");
                mail = reader.next();
            } // if si ese usuario si existe
        } while (semaforo); // bucle para repetir el mail hasta que no este registrado

        System.out.println("Contraseña: ");
        contraseña = reader.next();
        System.out.println("Descuento: ");
        descuento = reader.nextDouble();
        System.out.println("¿Es Cliente Premium?\n1-Si\n2-No");
        clientePremium = reader.nextInt();

        while(clientePremium > 2 || clientePremium < 1) {
            System.out.println("Elige una opcion entre 1 y 2");
            clientePremium = reader.nextInt();
        } // while para validar que el cliente solo elija una de las 2 opciones

        if(clientePremium == 1){
            user = new Usuario(mail, contraseña, descuento, true);
            lista.add(user);
            System.out.println("Se ha añadido correctamente");
        } else {
            user = new Usuario(mail, contraseña, descuento, false);
            lista.add(user);
            System.out.println("Se ha añadido correctamente");
        } // if para si el usuario es premium o no
        return lista;
    } // METODO PARA INSERTAR UN NUEVO CLIENTE


    public static void buscarUsuario (ArrayList<Usuario> lista, String mail){
        boolean semaforo = false; // atributo boolean para definir si ese usuario esta o no en la lista
        for(Usuario user : lista){
            if(user.getMailCl().equals(mail)){
                semaforo = true;
                break;
            }
        }
        if(semaforo){
            System.out.println("El usuario " + mail + " esta registrado");
        } else {
            System.out.println("No se ha encontrado ningun usuario con ese mail");
        } // if si el usuario se encuentra, se mostrara un mensaje
    } // METODO PARA BUSCAR UN USUARIO POR MAIL


    public static void totalIngreso (ArrayList<Usuario> lista){
        double totalIngresos = 0; // se guarda el total de ingresos segun su rol (si es premium o no)
        // restando su respectivo descuento establecido
        for(Usuario user : lista){
            if(user.isPremiumUs()){
                totalIngresos = totalIngresos + 35.5 - user.getDescuentoCl();
            } else {
                totalIngresos = totalIngresos + 20.5 - user.getDescuentoCl();
            }
        }
        System.out.println("El total de ingresos es de: " + totalIngresos);
    } // METODO PARA CALCULAR EL TOTAL DE INGRESOS DE LOS USUARIOS
}
