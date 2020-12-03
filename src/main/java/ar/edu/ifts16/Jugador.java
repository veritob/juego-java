package ar.edu.ifts16;

import java.awt.Color;
import java.awt.Graphics;

public class Jugador extends ElementoBasico {

	public Jugador(int posicionX, int posicionY, double velocidadX, double velocidadY, int ancho, int largo,
			Color color) {
		super(posicionX, posicionY, velocidadX, velocidadY, ancho, largo, color);
	}

	public void dibujarse(Graphics graphics) {
		graphics.setColor(getColor());
		graphics.fillOval((int) getPosicionX(), (int) getPosicionY(), getAncho(), getLargo());
	}

}
