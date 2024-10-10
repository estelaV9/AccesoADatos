package com.example.esteladevega_ejercicioformulario.DAO;

import com.example.esteladevega_ejercicioformulario.Controller.RegistrationCtrller;
import com.example.esteladevega_ejercicioformulario.Model.CubeUser;
import com.example.esteladevega_ejercicioformulario.Model.Product;
import com.example.esteladevega_ejercicioformulario.Utilities.StaticCode;
import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    public static List<Product> listProduct(Connection con) {
        List<Product> products = new ArrayList<>();
        try {
            String sql = "SELECT NAME_PRODUCT, CATEGORY, PRICE, NAME_OWNER_PRODUCT FROM CUBE_PRODUCT;";
            PreparedStatement statement = con.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String nameProduct = resultSet.getString("NAME_PRODUCT");
                int price = resultSet.getInt("PRICE");
                String category = resultSet.getString("CATEGORY");
                String nameOwner = resultSet.getString("NAME_OWNER_PRODUCT");
                Product product = new Product(nameProduct, category, price, nameOwner);
                products.add(product);
            }
        } catch (SQLException e) {
            StaticCode.Alerts("ERROR", "Error de conexión", "¡ERROR!",
                    "Error al conectar a la base de datos: " + e.getMessage());
        }
        return products;
    }

    public static List<Product> myListProduct (Connection con, String mail) {
        List<Product> products = new ArrayList<>();
        try {
            String sql = "SELECT NAME_PRODUCT, CATEGORY, PRICE FROM CUBE_PRODUCT " +
                    "WHERE NAME_OWNER_PRODUCT = (SELECT NAME_USER FROM CUBE_USERS WHERE MAIL = ?);";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, mail);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String nameProduct = resultSet.getString("NAME_PRODUCT");
                int price = resultSet.getInt("PRICE");
                String category = resultSet.getString("CATEGORY");
                Product product = new Product(nameProduct, category, price);
                products.add(product);
            }
        } catch (SQLException e) {
            StaticCode.Alerts("ERROR", "Error de conexión", "¡ERROR!",
                    "Error al conectar a la base de datos: " + e.getMessage());
        }
        return products;
    }

    public static boolean modifyProduct(Connection con, Product product, String oldName) {
        try {
            String sqlUpdate = "UPDATE CUBE_PRODUCT SET NAME_PRODUCT = ?, CATEGORY = ?, PRICE = ? WHERE NAME_PRODUCT = ?;";
            PreparedStatement statement = con.prepareStatement(sqlUpdate);
            statement.setString(1, product.getNameProduct());
            statement.setString(2, product.getCategory());
            statement.setDouble(3, product.getPrice());
            statement.setString(4, oldName);
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                return true;
            }
        } catch (SQLException e) {
            StaticCode.Alerts("ERROR", "Error de conexión", "¡ERROR!",
                    "Error al conectar a la base de datos: " + e.getMessage());
            return false;
        }
        return false;
    }




    public static boolean isExistsNameUser(Connection con, String name) {
        try {
            String sqlQuery = "SELECT NAME_PRODUCT FROM CUBE_PRODUCT WHERE NAME_PRODUCT = ?;";
            PreparedStatement statementQuery = con.prepareStatement(sqlQuery);
            statementQuery.setString(1, name);
            ResultSet resultSet = statementQuery.executeQuery();
            if (resultSet.next()) {
                // SI EL NOMBRE DE PRODUCTO EXISTE, DEVUELVE TRUE
                return true;
            }
        } catch (SQLException e) {
            StaticCode.Alerts("ERROR", "Error de conexión", "¡ERROR!",
                    "Error al conectar a la base de datos: " + e.getMessage());
            return false;
        }
        return false;
    } // METODO PARA COMPROBAR SI EL NOMBRE DE PRODUCTO YA EXISTE


    public static boolean deleteProduct(Connection con, String nameProduct) {
        try {
            String sqlDelete = "DELETE FROM CUBE_PRODUCT WHERE NAME_PRODUCT = ?";
            PreparedStatement statement = con.prepareStatement(sqlDelete);
            statement.setString(1, nameProduct);
            int rowsDelete = statement.executeUpdate();
            if (rowsDelete > 0) {
                // SI HA ELIMINADO CORRECTAMENTE RETURN TRUE
                return true;
            }
        } catch (SQLException e) {
            StaticCode.Alerts("ERROR", "Error de conexión", "¡ERROR!",
                    "Error al conectar a la base de datos: " + e.getMessage());
            return false;
        }
        return false;
    }

    public static boolean insertProdut(Connection con, Product product) {
        try {
            String sqlInsert = "INSERT INTO CUBE_PRODUCT (NAME_PRODUCT, CATEGORY, PRICE, NAME_OWNER_PRODUCT) " +
                    "VALUES (?, ?, ?, ?);";
            PreparedStatement statement = con.prepareStatement(sqlInsert);
            statement.setString(1, product.getNameProduct());
            statement.setString(2, product.getCategory());
            statement.setDouble(3, product.getPrice());
            statement.setString(4, product.getOwner());
            int rowsInserted = statement.executeUpdate();
            // COMPROBAR SI SE HA INSERTADO CORRECTAMENTE
            if (rowsInserted > 0) {
                return true;
            }
        } catch (SQLException e) {
            StaticCode.Alerts("ERROR", "Error de conexión", "¡ERROR!",
                    "Error al conectar a la base de datos: " + e.getMessage());
            return false;
        }
        return false;
    }

}
