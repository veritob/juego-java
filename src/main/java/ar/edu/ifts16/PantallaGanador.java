package ar.edu.ifts16;

import java.awt.*;

public class PantallaGanador extends PantallaImagen {


    public PantallaGanador(int ancho, int largo, String resource) {
        super(ancho, largo, resource);

    }

    public void dibujarse(Graphics graphics) {
        super.dibujarse(graphics);
        mostrarMensaje(graphics, "Espere 5 segundos para comenzar una nueva partida");
    }


    private void mostrarMensaje(Graphics g, String mensaje) {
        g.setColor(Color.white);
        g.setFont(new Font("Arial", 8, 15));
        g.drawString(mensaje, 200, 598);
    }
}
