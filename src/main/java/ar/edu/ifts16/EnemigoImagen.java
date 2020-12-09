package ar.edu.ifts16;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class EnemigoImagen extends Jugador {

    //atributos enemigo
    private final BufferedImage img;
    private final String enemy;

    //el string enemy se crea exclusivamente para recibir como parametro los distintos fantasmas ya que todos son enemy.
    public EnemigoImagen(int posicionX, int posicionY, int velocidadX, int velocidadY, int ancho, int largo,
                         String enemy) {
        super(posicionX, posicionY, velocidadX, velocidadY, ancho, largo);
        this.enemy = enemy;
//		el absolutePath es necesario para llegar a la ruta de la imagen. 
        String path = "src/main/resources/images";
        File file = new File(path);
        String absolutePath = file.getAbsolutePath();

        try {
            this.img = ImageIO.read(new File(absolutePath + this.enemy));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void dibujarse(Graphics graphics) {
        try {
            int gpx = (int) getPosicionX();
            int gpy = (int) getPosicionY();
            graphics.drawImage(img, gpx, gpy, this.getAncho(), this.getLargo(), null);
        } catch (Exception e1) {
            throw new RuntimeException(e1);
        }
    }

}
