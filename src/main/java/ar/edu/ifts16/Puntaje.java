package ar.edu.ifts16;

import java.awt.*;

public class Puntaje{

	private static int puntajeActual = 0;
	
	public Puntaje() {
		puntajeActual = 0;
	}
	
	public void refreshPuntaje(Graphics graphics) {
        Font font = new Font("Monospaced", Font.BOLD, 24);
        graphics.setFont(font);
        graphics.setColor(Color.YELLOW);
        graphics.drawString("PUNTAJE: " + puntajeActual, 50, 20);
	}
	
	public void aumentarPuntaje(Graphics graphics)
	{
		this.puntajeActual++;
		this.refreshPuntaje(graphics);
	}

	public void resetearPuntaje() {
		puntajeActual = 0;
	}

	public int getPuntajeActual() {
		return puntajeActual;
	}
}
