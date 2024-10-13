package com.example.esteladevega_ejercicioformulario.Model;

import com.example.esteladevega_ejercicioformulario.DAO.CubeUserDAO;

import javax.management.relation.Role;
import java.time.LocalDate;

public class CubeUser {
    private int idUser;
    private String nameUser;
    private String passwordUser;
    private int levelUser;
    private String mail;
    private LocalDate registrationDate;

    public CubeUser () {

    }



    public CubeUser(String mail, String passwordUser) {
        this.mail = mail;
        this.passwordUser = passwordUser;
    }

    public CubeUser(String nameUser, String passwordUser, int levelUser, String mail, LocalDate registrationDate) {
        this.nameUser = nameUser;
        this.passwordUser = passwordUser;
        this.levelUser = levelUser;
        this.mail = mail;
        this.registrationDate = registrationDate;
    }

    public CubeUser(int idUser, String nameUser, String passwordUser, int levelUser, String mail, LocalDate registrationDate) {
        this.idUser = idUser;
        this.nameUser = nameUser;
        this.passwordUser = passwordUser;
        this.levelUser = levelUser;
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

    public int getLevelUser() {
        return levelUser;
    }

    public void setLevelUser(int levelUser) {
        this.levelUser = levelUser;
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
