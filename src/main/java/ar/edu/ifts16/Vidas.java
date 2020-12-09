package ar.edu.ifts16;

import java.awt.*;

public class Vidas implements Dibujable {

    private int vidasActual;

    public Vidas() {
        this.vidasActual = 3;
    }

    public void dibujarse(Graphics g) {
        Font font = new Font("Monospaced", Font.BOLD, 24);
        g.setFont(font);
        g.setColor(Color.YELLOW);
        g.drawString("VIDAS: " + vidasActual, 650, 20);
    }

    public void perderVida(Sonidos sonidos) {
        vidasActual--;
        sonidos.tocarSonido("muerte");
    }

    public int getVidasActual() {
        return vidasActual;
    }

    public void resetearVidas() {
        this.vidasActual = 3;
    }

}


