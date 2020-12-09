package ar.edu.ifts16;

import java.awt.*;

public class Jugador extends ElementoBasico {

    public Jugador(int posicionX, int posicionY, double velocidadX, double velocidadY, int ancho, int largo) {
        super(posicionX, posicionY, velocidadX, velocidadY, ancho, largo);
    }

    public void dibujarse(Graphics graphics) {

        graphics.fillOval((int) getPosicionX(), (int) getPosicionY(), getAncho(), getLargo());
    }

    public void setMoverse(int lado) {

    }

    public void moverse() {
        setPosicionX(getPosicionX() + getVelocidadX() * 0.5);
        setPosicionY(getPosicionY() + getVelocidadY() * 0.5);
    }


}
