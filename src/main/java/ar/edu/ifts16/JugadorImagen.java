package ar.edu.ifts16;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class JugadorImagen extends Jugador {

    private final BufferedImage imgU;
    private final BufferedImage imgD;
    private final BufferedImage imgR;
    private final BufferedImage imgL;
    private boolean up;
    private boolean down;
    private boolean right;
    private boolean left;

//	aca se inicializan todos los atributos de la imagen pacman.
// se inicializan las 4 imagenes que se muestran hacia los distintos lados.

    public JugadorImagen(int posicionX, int posicionY, int velocidadX, int velocidadY, int ancho, int largo) {
        super(posicionX, posicionY, velocidadX, velocidadY, ancho, largo);

//		obtener la ruta correcta a las imagenes.
        String path = "src/main/resources/images";
        File file = new File(path);
        String absolutePath = file.getAbsolutePath();

// aca se les asigna valor a los atributos anteriores, con el absolutePath +  la imagen deseada.
        try {
            this.imgU = ImageIO.read(new File(absolutePath + "/pacmanUp.png"));
            this.imgD = ImageIO.read(new File(absolutePath + "/pacmanDown.png"));
            this.imgR = ImageIO.read(new File(absolutePath + "/pacmanRight.png"));
            this.imgL = ImageIO.read(new File(absolutePath + "/pacmanLeft.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //aca se dibuja el Jugador en el tablero, se ejecuta desde prueba1
    public void dibujarse(Graphics graphics) {
        try {
            int gpx = (int) getPosicionX();
            int gpy = (int) getPosicionY();
            if (this.up) {
                graphics.drawImage(this.imgU, gpx, gpy, this.getAncho(), this.getLargo(), null);
            }
            if (this.down) {
                graphics.drawImage(this.imgD, gpx, gpy, this.getAncho(), this.getLargo(), null);
            }
            if (this.right) {
                graphics.drawImage(this.imgR, gpx, gpy, this.getAncho(), this.getLargo(), null);
            }
            if (this.left) {
                graphics.drawImage(this.imgL, gpx, gpy, this.getAncho(), this.getLargo(), null);
            }
            // Dibujarse a la derecha al comienzo del juego
            if (!this.up && !this.down && !this.right && !this.left) {
                graphics.drawImage(this.imgR, gpx, gpy, this.getAncho(), this.getLargo(), null);
            }
        } catch (Exception e1) {
            throw new RuntimeException(e1);
        }
    }

    //	con este metodo se elije siempre que sea true... el lado de la imagen (boca) y se settean constantemente segun que flecha toca el usuario.
    public void setMoverse(int lado) {

        if (lado == 1) {
            this.up = true;
            this.down = false;
            this.right = false;
            this.left = false;
        }

        if (lado == 2) {
            this.up = false;
            this.down = true;
            this.right = false;
            this.left = false;
        }

        if (lado == 3) {
            this.up = false;
            this.down = false;
            this.right = true;
            this.left = false;
        }

        if (lado == 4) {
            this.up = false;
            this.down = false;
            this.right = false;
            this.left = true;
        }

    }

}