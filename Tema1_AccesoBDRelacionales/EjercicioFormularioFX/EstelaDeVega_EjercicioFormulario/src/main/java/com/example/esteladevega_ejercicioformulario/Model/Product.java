package com.example.esteladevega_ejercicioformulario.Model;

public class Product {
    private int idProduct; // ATRIBUTO PARA GUARDAR EL ID DEL PRODUCTO
    private String nameProduct; // ATRIBUTO PARA GUARDAR EL NOMBRE DEL PRODUCTO
    private String category; // ATRIBUTO PARA GUARDAR LA CATEGORIA DEL PRODUCTO
    private double price; // ATRIBUTO PARA GUARDAR EL PRECIO DEL PRODUCTO
    private String owner; // ATRIBUTO PARA GUARDAR EL PROPIETARIO DEL PRODUCTO

    public Product(){

    }
    public Product(String nameProduct, String category, double price) {
        this.nameProduct = nameProduct;
        this.category = category;
        this.price = price;
    }
    public Product(String nameProduct, String category, double price, String owner) {
        this.nameProduct = nameProduct;
        this.category = category;
        this.price = price;
        this.owner = owner;
    }
    public Product(int idProduct, String nameProduct, String category, double price, String owner) {
        this.idProduct = idProduct;
        this.nameProduct = nameProduct;
        this.category = category;
        this.price = price;
        this.owner = owner;
    }

    public int getIdProduct() {
        return idProduct;
    }
    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }
    public String getNameProduct() {
        return nameProduct;
    }
    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public String getOwner() {
        return owner;
    }
    public void setOwner(String owner) {
        this.owner = owner;
    }
}