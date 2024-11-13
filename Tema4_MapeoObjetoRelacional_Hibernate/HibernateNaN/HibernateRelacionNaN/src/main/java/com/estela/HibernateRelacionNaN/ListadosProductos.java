package com.estela.HibernateRelacionNaN;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query; // ESTA ES LO QUE HAY QUE IMPORTAR

import model.*;
import util.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

public class ListadosProductos {

    public static void main(String[] args) {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = HibernateUtil.getSession();
        Query consulta;

        try {
            session.beginTransaction();

            //************************** HQL *********************************
            //busquedas

            //Obtener un objeto identificado por el id utilizando HQL
			/*
			HQL (Hibernate Query Language), muy similar al lenguaje SQL que se usa en las base de datos relacionales,
			pero en este caso totalmente Orientado a Objetos puesto que en vez de trabajar con las tablas se trabaja
			 con las clases y sus atributos directamente.
			 */

            //Obtener un objeto identificado por el id

            Productos prod = session.get(Productos.class, 1);
            System.out.println(prod);

            //Obtener todos los objetos de una clase
            consulta = (Query) session.createQuery("FROM Productos");

            ArrayList<Productos> listaProductos = (ArrayList<Productos>) consulta.list();
            for (Productos producto : listaProductos) {
                //imprimimos el objeto
                System.out.println(producto);
            }

            //con parametros
            String hql = "FROM Productos WHERE nombre = :nombre";
            consulta = session.createQuery(hql);
            consulta.setParameter("nombre", "Pera");

            List<Productos> resultados = consulta.list();
            for (Productos producto : resultados) {
                //imprimimos el objeto
                System.out.println(producto);
            }

//*****************************************  SQL **************************************************************************
            //Tambien podemos utilizar el lenguaje  SQL,trabajando entonces directamente con las tablas y campos de la base de datos

			/*
				El método createNativeQuery() crea la consulta
				El método list() la ejecuta y devuelve una lista de arrays de objetos (List<Object[]>)
				Cuando hacemos consultas que devuelven entidades,  se puede especificar la clase
			 */

            String sql = "SELECT * from Productos";

            List<Productos> listaProductos1 = session.createNativeQuery(sql, Productos.class).list();
            for (Productos producto : listaProductos1) {
                //imprimimos el objeto pivote
                System.out.println(producto);
            }

            //con parametros

            //List<Person> persons = session.createNativeQuery(    "SELECT * FROM TblPerson" +    "WHERE name like :vname and id > :vid" , Person.class ) .setParameter( "vname", "J%" ) .setParameter( "vid", 5 ) .list()
            sql = "SELECT * FROM Productos WHERE stock >= ?";

            List<Productos> resultado1 = session.createNativeQuery(sql, Productos.class).setParameter(1, 5).list();

            for (Productos producto1 : resultado1) {

                //imprimimos el objeto pivote
                System.out.println(producto1);
            }

            // commit de la transaccion
            session.getTransaction().commit();
        } finally {
            factory.close();
        }
    }
}