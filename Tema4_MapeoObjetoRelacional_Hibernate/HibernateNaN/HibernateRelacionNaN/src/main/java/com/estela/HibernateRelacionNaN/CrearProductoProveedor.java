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

            // AÑADIR LA RELACIÓN A AMBOS LADOS (RELACIÓN BIDIRECCIONAL)
            pepino.addProductoProveedor(productoProveedor);
            eugenio.addProductoProveedor(productoProveedor);

            // COMMIT DE LA TRANSACCIÓN
            transaction.commit();

            // -----------------------------------------------------
            // OBTENER LOS DATOS DESDE LA BASE DE DATOS Y AÑADIR MÁS RELACIONES
            transaction = session.beginTransaction();

            // OBTENER PRODUCTO Y PROVEEDOR POR SU ID (SUPONIENDO QUE EL ID DE PEPINO ES 1 Y EL DE EUGENIO ES 1)
            pepino = session.get(Productos.class, 1);
            eugenio = session.get(Proveedores.class, 1);

            // CREAR MÁS RELACIONES SI SE REQUIERE
            Producto_Proveedor otroProductoProveedor = new Producto_Proveedor(eugenio, pepino, 50);
            session.save(otroProductoProveedor);

            // AÑADIR ESTA NUEVA RELACIÓN
            pepino.addProductoProveedor(otroProductoProveedor);
            eugenio.addProductoProveedor(otroProductoProveedor);

            // COMMIT DE LA TRANSACCIÓN
            transaction.commit();

            // -----------------------------------------------------
            // BORRAR UN REGISTRO DE LA TABLA PRODUCTO_PROVEEDOR
            transaction = session.beginTransaction();
            Producto_Proveedor productoProveedor1 = session.get(Producto_Proveedor.class, 1); // OBTENEMOS LA RELACIÓN
            session.delete(productoProveedor1); // ELIMINA LA RELACIÓN
            transaction.commit();

            // -----------------------------------------------------
            // BORRAR UN PRODUCTO Y SU PROVEEDOR

            transaction = session.beginTransaction();
            Productos producto = session.get(Productos.class, 1);
            session.delete(producto); // ELIMINANDO EL PRODUCTO
            transaction.commit();

            // ELIMINAR EL PROVEEDOR DE LA MISMA MANERA
            transaction = session.beginTransaction();
            Proveedores proveedor = session.get(Proveedores.class, 1);
            session.delete(proveedor); // ELIMINAR EL PROVEEDOR
            transaction.commit();

            session.close();


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
            session.save(felipe);

            transaction.commit();
            session.close();*/
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