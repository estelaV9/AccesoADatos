package org.example.Model;

public class Cliente {
    private String mailCl;
    private String contraseñaCl;
    private double descuentoCl;

    public Cliente() {
    }

    public Cliente(String mailCl, String contraseñaCl, double descuentoCl) {
        this.mailCl = mailCl;
        this.contraseñaCl = contraseñaCl;
        this.descuentoCl = descuentoCl;
    }

    public String getMailCl() {
        return mailCl;
    }

    public void setMailCl(String mailCl) {
        this.mailCl = mailCl;
    }

    public String getContraseñaCl() {
        return contraseñaCl;
    }

    public void setContraseñaCl(String contraseñaCl) {
        this.contraseñaCl = contraseñaCl;
    }

    public double getDescuentoCl() {
        return descuentoCl;
    }

    public void setDescuentoCl(double descuentoCl) {
        this.descuentoCl = descuentoCl;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "mailCl='" + mailCl + '\'' +
                ", contraseñaCl='" + contraseñaCl + '\'' +
                ", descuentoCl=" + descuentoCl +
                '}';
    }
}
