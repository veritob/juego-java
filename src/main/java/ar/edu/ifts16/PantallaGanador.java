package ar.edu.ifts16;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class PantallaGanador extends PantallaImagen {
	private Puntaje puntaje;

    public PantallaGanador(int ancho, int largo, String resource, Puntaje puntaje) {
        super(ancho, largo, resource);
        this.puntaje = puntaje;
    }

    public void dibujarse(Graphics graphics) {
        super.dibujarse(graphics);
        String mensajePuntos = " puntos";
        if (puntaje.getPuntajeActual() == 5) {//362
            mensajePuntos += "s";
        mostrarMensaje(graphics, "Puntaje perfecto! " + puntaje.getPuntajeActual() + mensajePuntos);
    }}

    private void mostrarMensaje(Graphics g, String mensaje) {
        g.setColor(Color.white);
        g.setFont(new Font("Arial", 8, 30));
        g.drawString(mensaje, 10, 40);
    }
}
