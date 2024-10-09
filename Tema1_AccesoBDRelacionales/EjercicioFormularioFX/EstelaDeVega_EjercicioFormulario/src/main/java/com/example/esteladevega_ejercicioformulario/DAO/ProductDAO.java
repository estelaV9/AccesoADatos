package com.example.esteladevega_ejercicioformulario.DAO;

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

}
