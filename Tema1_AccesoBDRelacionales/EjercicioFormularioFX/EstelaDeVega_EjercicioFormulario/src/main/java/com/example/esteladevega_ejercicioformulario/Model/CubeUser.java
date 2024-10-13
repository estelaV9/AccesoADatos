package com.example.esteladevega_ejercicioformulario.Model;

import java.time.LocalDate;

public class CubeUser {
    private int idUser; // ATRIBUTO PARA GUARDAR EL ID DEL USUARIO
    private String nameUser; // ATRIBUTO PARA GUARDAR EL NOMBRE DEL USUARIO
    private String passwordUser; // ATRIBUTO PARA GUARDAR LA CONTRASEÑA DEL USUARIO
    private String mail; // ATRIBUTO PARA GUARDAR EL MAIL DEL USUARIO
    private LocalDate registrationDate; // ATRIBUTO PARA GUARDAR LA FECHA DE CREACION DEL USUARIO

    public CubeUser() {

    }
    public CubeUser(String mail, String passwordUser) {
        this.mail = mail;
        this.passwordUser = passwordUser;
    }
    public CubeUser(String nameUser, String passwordUser, String mail, LocalDate registrationDate) {
        this.nameUser = nameUser;
        this.passwordUser = passwordUser;
        this.mail = mail;
        this.registrationDate = registrationDate;
    }
    public CubeUser(int idUser, String nameUser, String passwordUser, String mail, LocalDate registrationDate) {
        this.idUser = idUser;
        this.nameUser = nameUser;
        this.passwordUser = passwordUser;
        this.mail = mail;
        this.registrationDate = registrationDate;
    }

    public int getIdUser() {
        return idUser;
    }
    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
    public String getNameUser() {
        return nameUser;
    }
    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }
    public String getPasswordUser() {
        return passwordUser;
    }
    public void setPasswordUser(String passwordUser) {
        this.passwordUser = passwordUser;
    }
    public String getMail() {
        return mail;
    }
    public void setMail(String mail) {
        this.mail = mail;
    }
    public LocalDate getRegistrationDate() {
        return registrationDate;
    }
    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }
}