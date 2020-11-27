package ar.edu.ifts16;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;



//import paleta.Sonidos;

public class Prueba1 extends JPanel implements Runnable, KeyListener {

    private static final long serialVersionUID = 1L;
    private int anchoJuego;
    private int largoJuego;
    private Sonidos sonidos;
    
    // Paleta
    private int paletaPosicionX;
    private int paletaPosicionY;
    private int paletaVelocidadX;
    private int paletaVelocidadY;
    private int paletaAncho;
    private int paletaLargo;
    
    // Pelotita
    private int pelotaPosicionX;
    private int pelotaPosicionY;
    private int pelotaVelocidadX;
    private int pelotaVelocidadY;
    private int pelotaAncho;
    private int pelotaLargo;
    
    

    public Prueba1(int anchoJuego, int largoJuego) {
        inicializarVentana(anchoJuego, largoJuego);
        inicializarPelota();
        inicializarPaleta();
        cargarSonidos();
        sonidos.tocarSonido("toc");
        this.sonidos.repetirSonido("toc");
    }
    
    private void inicializarPelota() {
        this.pelotaAncho = 20;
        this.pelotaLargo = 20;
        this.pelotaPosicionX = 400;
        this.pelotaPosicionY = 300;
//        this.pelotaVelocidadX = 3;
//        this.pelotaVelocidadY = 3;
    }
    
    private void inicializarPaleta() {
        this.paletaPosicionY = largoJuego-20;
        this.paletaAncho = 900;
        this.paletaLargo = largoJuego;
    }
    
    private void inicializarVentana(int anchoJuego, int largoJuego) {
        this.anchoJuego = anchoJuego;
        this.largoJuego = largoJuego;
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(anchoJuego, largoJuego);
    }
    
    @Override
    public void run() {
        while (true) {
            actualizarAmbiente();
            dibujar();
            esperar(20);
            sonidos.tocarSonido("toc");
        }
    }
    
    private void actualizarAmbiente() {
        verificarEstadoAmbiente();
        moverPaleta();
        moverPelota();
    }
    
    private void verificarEstadoAmbiente() {
        verificarRebotePelotaContraParedLateral();
        verificarRebotePelotaContraLaParedSuperior();
        
    }
    
    // se verifica si la pelota colisiona contra la pared lateral, si es asi, se hace rebotar la pelota en el eje X
    private void verificarRebotePelotaContraParedLateral() {
        if (pelotaPosicionX <= 0 || pelotaPosicionX + pelotaAncho >= anchoJuego) {
            pelotaVelocidadX = -pelotaVelocidadX;
        }
    }

    // se verifica si la pelota colisiona contra la pared superior, si es asi se hace rebotar la pelota en el eje Y
    private void verificarRebotePelotaContraLaParedSuperior() {
        if (pelotaPosicionY <= 0 || pelotaPosicionY + pelotaLargo >= largoJuego) {
            pelotaVelocidadY = -pelotaVelocidadY;
        }
    }
    
    private void dibujar() {
        this.repaint();
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        dibujar(g);
    }
    
    public void dibujar(Graphics graphics) {
           dibujarPelota(graphics);
    }

    private void esperar(int milisegundos) {
        try {
            Thread.sleep(milisegundos);
        } catch (Exception e1) {
            throw new RuntimeException(e1);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void keyPressed(KeyEvent e) {
           
		if (e.getKeyCode() == 37) {
			pelotaVelocidadX = -2;
		}
		if (e.getKeyCode() == 38) {
			pelotaVelocidadY = -2;
		}
		if (e.getKeyCode() == 39) {
			pelotaVelocidadX = 2;
		}
		if (e.getKeyCode() == 40) {
			pelotaVelocidadY = 2;
		}

    }

    @Override
    public void keyReleased(KeyEvent arg0) {
        // si suelto la tecla 39 o la 37 se asigna velocidad 0 a la paleta
        if (arg0.getKeyCode() == 39 || arg0.getKeyCode() == 37 ||  arg0.getKeyCode() == 38 ||  arg0.getKeyCode() == 40) {
        	pelotaVelocidadY = 0;
        	pelotaVelocidadX = 0;
        }
    }
       
    private void dibujarPelota(Graphics graphics) {
        graphics.setColor(Color.yellow);
        graphics.fillOval(pelotaPosicionX, pelotaPosicionY, pelotaAncho, pelotaLargo);
    }
    
    private void moverPaleta() {
        paletaPosicionX = paletaPosicionX + paletaVelocidadX; 
        paletaPosicionY = paletaPosicionY + paletaVelocidadY;
    }
    
    private void moverPelota() {
        pelotaPosicionX = pelotaPosicionX + pelotaVelocidadX; 
        pelotaPosicionY = pelotaPosicionY + pelotaVelocidadY;
    }
    
    
    private void cargarSonidos() {
        try {
            sonidos = new Sonidos();
            sonidos.agregarSonido("toc", "/ghostmove.ogg");
            sonidos.agregarSonido("tic", "sonidos/tic.wav");
            sonidos.agregarSonido("muerte", "sonidos/muerte.wav");
            sonidos.agregarSonido("background", "sonidos/background.wav");
        } catch (Exception e1) {
            throw new RuntimeException(e1);
        }
    }
}
//    private boolean hayColision(
//            int elemento1PosicionX, int elemento1PosicionY, int elemento1Ancho, int elemento1Largo,
//            int elemento2PosicionX, int elemento2PosicionY, int elemento2Ancho, int elemento2Largo) {
//        if (
//            haySolapamientoDeRango(
//                elemento1PosicionX,
//                elemento1PosicionX+elemento1Ancho,
//                elemento2PosicionX,
//                elemento2PosicionX+elemento2Ancho)
//            &&     
//            haySolapamientoDeRango(
//                    elemento1PosicionY,
//                    elemento1PosicionY+elemento1Largo,
//                    elemento2PosicionY,
//                    elemento2PosicionY+elemento2Largo)) {
//            return true;
//        }
//        return false;
//    }
//    
//    private boolean haySolapamientoDeRango(int a, int b, int c, int d) {
//        if (a < d && b > c  ) {
//            return true;
//        }
//        return false;
//    }

