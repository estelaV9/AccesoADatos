package com.estela.HibernateRelacionNaN;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import util.HibernateUtil;
import model.*;

public class CrearProductoProveedor {
    public static void main(String[] args) {
        Transaction transaction = null;
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = HibernateUtil.getSession(); // CREAMOS LA SESSION

        try {
            transaction = session.beginTransaction(); // INICIAMOS LA TRANSACCION

            /********* RELACION 1 A N ***********/
            Productos pepino = new Productos("Pepino", "Frances", 5);
            session.save(pepino);
            Proveedores eugenio = new Proveedores("Eugenio", "3", "miCorason");
            session.save(eugenio);
            Producto_Proveedor productoProveedor = new Producto_Proveedor(eugenio, pepino, 100);
            session.save(productoProveedor);


            /********* RELACION N A N ***********/
            /*
            Proveedores ana = new Proveedores("Supermercados Ana", "B221133", "Valladolid");

            session.save(ana);

            Productos pepino = new Productos("Pepino", "Frances", 5);
            Productos endivia = new Productos("Endivia", "Nada envidiosa", 7);

            ana.addProducto(pepino);

            ana.addProducto(endivia);
            session.save(pepino);
            session.save(endivia);
            transaction.commit();

            // AHORA AL REVES, VOY A ASIGNAR UNO/VARIOS PROVEEDORES A UN PRODUCTO
            Productos garbanzos = new Productos("Garbanzos", "D.O. Segovia", 10);
            session.save(garbanzos);

            //CREAR PROVEEDORES
            transaction = session.beginTransaction();
            Proveedores rocio = new Proveedores("La Tienda de Rocio", "B78945", "Segovia");
            Proveedores felipe = new Proveedores("Felipe el rey del Huerto", "B4556123", "Leon");

            // INSERTARLO EN LA TABLA producto_proveedor
            /********* RELACION N A N ***********/
            /* garbanzos.addProveedor(rocio);
            garbanzos.addProveedor(felipe);

            //ESTO SE INSERTA EN LA TABLA PROVEEDORES
            session.save(rocio);
            session.save(felipe);*/

            transaction.commit();
            session.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            if (transaction != null) {
                transaction.rollback();
            }

        } finally {
            factory.close();
        }
    }
}