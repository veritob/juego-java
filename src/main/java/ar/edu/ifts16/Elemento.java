package ar.edu.ifts16;

import java.awt.*;

public interface Elemento extends Dibujable {

    double getPosicionX();

    double getPosicionY();

    int getAncho();

    int getLargo();

    double getVelocidadX();

    double getVelocidadY();

    void dibujarse(Graphics graphics);

    void moverse();

    boolean hayColision(Elemento elemento);

}
