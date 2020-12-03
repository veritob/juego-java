package ar.edu.ifts16;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.Graphics;

	public class Enemigo extends ElementoBasico {

	    private BufferedImage img;

		public Enemigo(int posicionX, int posicionY, double velocidadX, double velocidadY, int ancho, int largo,
	            Color color) {
	        super(posicionX, posicionY, velocidadX, velocidadY, ancho, largo, color);
	    }
	    public void EnemigoImagen(int posicionX, int posicionY, double velocidadX, double velocidadY, int ancho, int largo,
	            Color color) {
	    
	        String path = Paths.get(Enemigo.class.getClassLoader().getResource("images/ghostViolet.gif").getPath())
	                .toString();
	        try {
	            this.img = ImageIO.read(new File(path));
	        } catch (IOException e) {
	            throw new RuntimeException(e);
	        }
	    }

	    public void dibujarse(Graphics graphics) {
	        graphics.setColor(getColor());
	        graphics.fillOval((int)getPosicionX(), (int)getPosicionY(), getAncho(), getLargo());
	    }
	    
	    public void moverse() {
	    	setPosicionX(getPosicionX() + getVelocidadX()*0.5);
	    	setPosicionY(getPosicionY() + getVelocidadY()*0.5);
	    }
	    
	    

	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
