package ar.edu.ifts16;

import java.awt.Color;
import java.awt.Graphics;

public interface Elemento extends Dibujable {

	public double getPosicionX();

	public double getPosicionY();

	public int getAncho();

	public int getLargo();

	public double getVelocidadX();

	public double getVelocidadY();

	public void dibujarse(Graphics graphics);

	public void moverse();

	public boolean hayColision (Elemento elemento);

	public void rebotarEnEjeX();

	public void rebotarEnEjeY();

}
