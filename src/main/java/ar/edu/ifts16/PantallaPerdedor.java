package ar.edu.ifts16;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class PantallaPerdedor extends PantallaImagen {

    private Puntaje puntaje;

    public PantallaPerdedor(int ancho, int largo, String resource, Puntaje puntaje) {
        super(ancho, largo, resource);
        this.puntaje = puntaje;
    }

    public void dibujarse(Graphics graphics) {
        super.dibujarse(graphics);
        String mensajePuntos = " punto";
        if (puntaje.getPuntajeActual() >= 1) {
            mensajePuntos += "s";
        }
        mostrarMensaje(graphics, "Obtuviste: " + puntaje.getPuntajeActual() + mensajePuntos);
    }

    private void mostrarMensaje(Graphics g, String mensaje) {
        g.setColor(Color.blue);
        g.setFont(new Font("Arial", 8, 30));
        g.drawString(mensaje, 10, 40);
    }

}
