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
        mostrarMensaje1(graphics, "Presiona cualquier tecla para iniciar una nueva partida");
    }

    private void mostrarMensaje(Graphics g, String mensaje) {
        g.setColor(Color.white);
        g.setFont(new Font("Arial", 8, 30));
        g.drawString(mensaje, ancho/2-150, 380);
    }
    private void mostrarMensaje1(Graphics g, String mensaje) {
        g.setColor(Color.white);
        g.setFont(new Font("Arial", 8, 20));
        g.drawString(mensaje, 150, 570);
    }
}
