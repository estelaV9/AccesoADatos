package com.estela.HibernateRelacionNaN;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import util.HibernateUtil;
import model.*;

public class CrearProductoProveedor {
    public static void main(String[] args) {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = HibernateUtil.getSession(); // CREAMOS LA SESSION

        try {
            session.beginTransaction(); // INICIAMOS LA TRANSACCION
            Proveedores paco = new Proveedores("Ana", "221133", "Valladolid");
            session.save(paco);

            Productos pepino = new Productos("Pepino", "Frances", 5);
            Productos endivia = new Productos("Endivia", "Nada envidiosa", 7);

            session.save(pepino);
            session.save(endivia);

            session.getTransaction().commit(); // COMMIT DE KA TRANSACCION
            session.close();
        } finally {
            factory.close();
        }
    }
}