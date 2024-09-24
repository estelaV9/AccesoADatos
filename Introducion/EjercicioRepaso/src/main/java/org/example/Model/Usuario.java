package org.example.Model;

public class Usuario extends Cliente{
    private int PremiumUs;

    public Usuario(){

    }

    public Usuario(String mailCl, String contraseñaCl, double descuentoCl, int premiumUs) {
        super(mailCl, contraseñaCl, descuentoCl);
        PremiumUs = premiumUs;
    }

    public int isPremiumUs() {
        return PremiumUs;
    }

    public void setPremiumUs(int premiumUs) {
        PremiumUs = premiumUs;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "PremiumUs=" + PremiumUs +
                '}';
    }
}
