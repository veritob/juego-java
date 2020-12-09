package ar.edu.ifts16;

import java.awt.*;

// este es el padre e implementa de la interfaz Elemento, de aqui extiende jugador y de jugador a jugadorImagen y EnemigoImagen-
public abstract class ElementoBasico implements Elemento {

    private double posicionX;
    private double posicionY;
    private double velocidadX;
    private double velocidadY;
    private int ancho;
    private int largo;

    public ElementoBasico(int posicionX, int posicionY, double velocidadX, double velocidadY, int ancho, int largo) {
        this.posicionX = posicionX;
        this.posicionY = posicionY;
        this.velocidadX = velocidadX;
        this.velocidadY = velocidadY;
        this.ancho = ancho;
        this.largo = largo;
    }

    // aca getters y setters que utilizaran jugador y enemigo
    public abstract void dibujarse(Graphics graphics);

    @Override
    public boolean hayColision(Elemento elemento) {
		return Utilidades.hayColision(
				(int) this.getPosicionX(),
				(int) this.getPosicionY(),
				this.getAncho(),
				this.getLargo(),
				(int) elemento.getPosicionX(),
				(int) elemento.getPosicionY(),
				elemento.getAncho(),
				elemento.getLargo());
    }

    public void moverse() {
        posicionX = posicionX + velocidadX;
        posicionY = posicionY + velocidadY;
    }

    public double getVelocidadX() {
        return velocidadX;
    }

    public void setVelocidadX(double velocidadX) {
        this.velocidadX = velocidadX;
    }

    public double getVelocidadY() {
        return velocidadY;
    }

    public void setVelocidadY(double velocidadY) {
        this.velocidadY = velocidadY;
    }

    public double getPosicionX() {
        return posicionX;
    }

    public void setPosicionX(double posicionX) {
        this.posicionX = posicionX;
    }

    public double getPosicionY() {
        return posicionY;
    }

    public void setPosicionY(double posicionY) {
        this.posicionY = posicionY;
    }

    public int getAncho() {
        return ancho;
    }

    public int getLargo() {
        return largo;
    }

    protected abstract void setMoverse(int i);

}
