package org.example.Model;

public class Usuario extends Cliente{
    private boolean PremiumUs;

    public Usuario(){

    }

    public Usuario(String mailCl, String contraseñaCl, double descuentoCl, boolean premiumUs) {
        super(mailCl, contraseñaCl, descuentoCl);
        PremiumUs = premiumUs;
    }

    public boolean isPremiumUs() {
        return PremiumUs;
    }

    public void setPremiumUs(boolean premiumUs) {
        PremiumUs = premiumUs;
    }
}
