package ar.edu.ifts16;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.image.ImageObserver;

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
	
	public void setPuntajeExtra(int puntajeExtra, Graphics graphics) {
		this.puntajeActual += puntajeExtra;
		this.refreshPuntaje(graphics);
	}
	
	public void ResetearPuntaje(Graphics graphics)
	{
		this.puntajeActual = 0;
		this.refreshPuntaje(graphics);
	}
}
