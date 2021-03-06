package ar.edu.ifts16;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Juego extends JPanel implements Runnable, KeyListener {

    private static final int PANTALLA_INICIO = 1;
    private static final int PANTALLA_JUEGO = 2;
    private static final int PANTALLA_ESPERA = 3;
    private static final int PANTALLA_PERDEDOR = 4;
    private static final int PANTALLA_GANADOR = 5;
    private static final long serialVersionUID = 1L;
    private static int posicionInicialEnemigoX;
    private static int posicionInicialEnemigoY;
    private int anchoJuego;
    private int largoJuego;
    private Sonidos sonidos;
    private final PantallaImagen portada;
    private final PantallaImagen pantallaGanador;
    private final PantallaImagen tablero;
    private final PantallaImagen pantallaEsperar;
    private final PantallaPerdedor pantallaPerdedor;
    private final int[][] tableroPosiciones;
    private int pantallaActual;
    private final ElementoBasico jugador;
    private final ElementoBasico enemigoImagenViolet;
    private final ElementoBasico enemigoImagenBlue;
    private final ElementoBasico enemigoImagenRed;
    private final ElementoBasico enemigoImagenGreen;
    private final Puntaje puntaje;
    private final Comida comida;
    private final Vidas vidas;

    public Juego(int anchoJuego, int largoJuego) {
        inicializarVentana(anchoJuego, largoJuego);
        posicionInicialEnemigoX = anchoJuego / 2;
        posicionInicialEnemigoY = largoJuego / 2 - 40;
        cargarSonidos();
        sonidos.tocarSonido("beginning");
        this.puntaje = new Puntaje();
        this.comida = new Comida(anchoJuego);
        this.vidas = new Vidas();
        this.portada = new PantallaImagen(anchoJuego, largoJuego, "images/bienvenidoAlJuego2.png");
        this.tablero = new PantallaImagen(anchoJuego, largoJuego, "images/tablero2.png");
        this.pantallaEsperar = new PantallaImagen(anchoJuego, largoJuego, "images/Perdiste1vidaEsperar.jpg");
        this.pantallaPerdedor = new PantallaPerdedor(anchoJuego, largoJuego, "images/Perdiste.jpeg", this.puntaje);
        this.pantallaGanador = new PantallaGanador(anchoJuego, largoJuego, "images/ganaste.jpeg");
        this.enemigoImagenViolet = new EnemigoImagen(posicionInicialEnemigoX - 20, posicionInicialEnemigoY, 0, 0, 40, 40, "/ghostViolet.png");
        this.enemigoImagenBlue = new EnemigoImagen(posicionInicialEnemigoX - 15, posicionInicialEnemigoY, 0, 0, 40, 40, "/ghostBlue.png");
        this.enemigoImagenRed = new EnemigoImagen(posicionInicialEnemigoX - 10, posicionInicialEnemigoY, 0, 0, 40, 40, "/ghostRed.png");
        this.enemigoImagenGreen = new EnemigoImagen(posicionInicialEnemigoX, posicionInicialEnemigoY, 0, 0, 40, 40, "/ghostGreen.png");
        this.jugador = new JugadorImagen(40, largoJuego - 60, 0, 0, 30, 30);
        this.tableroPosiciones = inicializarTableroPosiciones(anchoJuego, largoJuego);
        inicializarJuego(true);
    }

    private void inicializarJuego(boolean nuevaPartida) {
        if (nuevaPartida == true) {
            pantallaActual = PANTALLA_INICIO;
            sonidos.tocarSonido("beginning");
            this.puntaje.resetearPuntaje();
            this.vidas.resetearVidas();
            this.comida.resetearComida();
        } else {
            pantallaActual = PANTALLA_JUEGO;
            sonidos.repetirSonido("background");
        }

        this.jugador.setMoverse(3);
        this.jugador.setPosicionX(40);
        this.jugador.setPosicionY(largoJuego - 60);
        enemigoImagenViolet.setPosicionX(posicionInicialEnemigoX - 70);
        enemigoImagenViolet.setPosicionY(posicionInicialEnemigoY);
        enemigoImagenBlue.setPosicionX(posicionInicialEnemigoX - 30);
        enemigoImagenBlue.setPosicionY(posicionInicialEnemigoY);
        enemigoImagenRed.setPosicionX(posicionInicialEnemigoX + 10);
        enemigoImagenRed.setPosicionY(posicionInicialEnemigoY);
        enemigoImagenGreen.setPosicionX(posicionInicialEnemigoX + 50);
        enemigoImagenGreen.setPosicionY(posicionInicialEnemigoY);
    }


    private int[][] inicializarTableroPosiciones(int anchoJuego, int largoJuego) {
        int[][] resultado = new int[largoJuego][anchoJuego];
        for (int i = 0; i < largoJuego; i++) {
            for (int j = 0; j < anchoJuego; j++) {
                if (i < 20 || i > largoJuego - 50 || j < 40 || j > anchoJuego - 60) {
                    resultado[i][j] = 1;
                } else {
                    resultado[i][j] = 0;
                }
            }
        }

        return resultado;
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
            if (pantallaActual == PANTALLA_JUEGO) {
                actualizarAmbiente();
            }
            if (pantallaActual == PANTALLA_ESPERA) {
                esperar(5000);
                inicializarJuego(false);
            }
            if (pantallaActual == PANTALLA_PERDEDOR || pantallaActual == PANTALLA_GANADOR) {
                esperar(5000);
                inicializarJuego(true);
            }
            dibujar();
            esperar(20);
        }
    }

    private void actualizarAmbiente() {
        verificarEstadoAmbiente();
        jugador.moverse();
        enemigoImagenViolet.moverse();
        enemigoImagenBlue.moverse();
        enemigoImagenRed.moverse();
        enemigoImagenGreen.moverse();
    }

    // aca le se le indica movimiento a los enemigos
    private void verificarEstadoAmbiente() {

        // violet
        if (enemigoImagenViolet.getPosicionX() < jugador.getPosicionX()) {
            enemigoImagenViolet.setVelocidadX(1);
        } else {
            enemigoImagenViolet.setVelocidadX(-1);
        }

        if (enemigoImagenViolet.getPosicionY() < jugador.getPosicionY()) {
            enemigoImagenViolet.setVelocidadY(1);
        } else {
            enemigoImagenViolet.setVelocidadY(-1);
        }

        // blue
        if (enemigoImagenBlue.getPosicionX() < jugador.getPosicionX()) {
            enemigoImagenBlue.setVelocidadX(2);
        } else {
            enemigoImagenBlue.setVelocidadX(-2);
        }

        if (enemigoImagenBlue.getPosicionY() < jugador.getPosicionY()) {
            enemigoImagenBlue.setVelocidadY(2);
        } else {
            enemigoImagenBlue.setVelocidadY(-2);
        }

        // red
        if (enemigoImagenRed.getPosicionX() < jugador.getPosicionX()) {
            enemigoImagenRed.setVelocidadX(0.5);
        } else {
            enemigoImagenRed.setVelocidadX(-0.5);
        }

        if (enemigoImagenRed.getPosicionY() < jugador.getPosicionY()) {
            enemigoImagenRed.setVelocidadY(0.5);
        } else {
            enemigoImagenRed.setVelocidadY(-0.5);
        }

        // Green
        if (enemigoImagenGreen.getPosicionX() < jugador.getPosicionX()) {
            enemigoImagenGreen.setVelocidadX(1.5);
        } else {
            enemigoImagenGreen.setVelocidadX(-1.5);
        }

        if (enemigoImagenGreen.getPosicionY() < jugador.getPosicionY()) {
            enemigoImagenGreen.setVelocidadY(1.5);
        } else {
            enemigoImagenGreen.setVelocidadY(-1.5);
        }

        verificarReboteJugadorContraParedLateral();
        verificarColisionEntreEnemigoYJugador();
    }

    private void verificarColisionEntreEnemigoYJugador() {
        if (jugador.hayColision(enemigoImagenBlue) ||
                jugador.hayColision(enemigoImagenGreen) ||
                jugador.hayColision(enemigoImagenRed) ||
                jugador.hayColision(enemigoImagenViolet)) {
            vidas.perderVida(sonidos);
            sonidos.detenerSonido("background");

            if (vidas.getVidasActual() == 0) {
                pantallaActual = PANTALLA_PERDEDOR;
            } else {
                pantallaActual = PANTALLA_ESPERA;
            }
        }
    }

    private void verificarReboteJugadorContraParedLateral() {
        double nuevaPosicionX = jugador.getPosicionX() + jugador.getVelocidadX();
        double nuevaPosicionY = jugador.getPosicionY() + jugador.getVelocidadY();

        if (nuevaPosicionX >= anchoJuego || nuevaPosicionX < 0) {
            jugador.setVelocidadX(0);
        } else if (nuevaPosicionY >= largoJuego || nuevaPosicionY < 0) {
            jugador.setVelocidadY(0);
        } else if (tableroPosiciones[(int) nuevaPosicionY][(int) nuevaPosicionX] == 1) {
            jugador.setVelocidadX(0);
            jugador.setVelocidadY(0);
        }
    }

    private void verificarGanador() {

        if (this.puntaje.getPuntajeActual() == this.comida.getTotalComida()) {
            this.pantallaActual = PANTALLA_GANADOR;
        }
    }

    private void dibujar() {
        this.repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        switch (pantallaActual) {
            case PANTALLA_INICIO:
                dibujarPortada(g);
                break;
            case PANTALLA_JUEGO:
                dibujarInicioJuego(g);
                this.comida.verificarComida(g, this.jugador, this.puntaje, this.sonidos);
                this.puntaje.refreshPuntaje(g);
                this.vidas.dibujarse(g);
                this.verificarGanador();
                break;
            case PANTALLA_ESPERA:
                dibujarEspera(g);
                break;
            case PANTALLA_PERDEDOR:
                dibujarPantallaPerdedor(g);
                break;
            case PANTALLA_GANADOR:
                dibujarPantallaGanador(g);
                break;
        }
        dibujar();
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

    //aca es el metodo que se ejecuta cuando se apreta una tecla . x un lado se setea la pantalla, y cx otro lado los movimientos del jugador
    @Override
    public void keyPressed(KeyEvent e) {

        if (pantallaActual == PANTALLA_INICIO) {
            inicializarJuego(false);
        }

// aca se setea el movimiento del jugador con su velocidad(setVelocidadX) y x otro lado se le envia el valor de la tecla que toco a jugador imagen para cambiar la posicion de la boca en la imagen
        if (pantallaActual == PANTALLA_JUEGO) {
            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                jugador.setMoverse(3);
                jugador.setVelocidadX(4);
            }

            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                jugador.setMoverse(4);
                jugador.setVelocidadX(-4);
            }

            if (e.getKeyCode() == KeyEvent.VK_UP) {
                jugador.setMoverse(1);
                jugador.setVelocidadY(-4);
            }

            if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                jugador.setMoverse(2);
                jugador.setVelocidadY(4);
            }

        }
    }

    // cuando se deja de tocar la flecha. se setea la velocidad en 0 para que se detenga-
    @Override
    public void keyReleased(KeyEvent arg0) {
        if (arg0.getKeyCode() == 39 || arg0.getKeyCode() == 37 || arg0.getKeyCode() == 38 || arg0.getKeyCode() == 40) {
            jugador.setVelocidadX(0);
            jugador.setVelocidadY(0);
        }
    }

    private void cargarSonidos() {
        try {
            sonidos = new Sonidos();
            sonidos.agregarSonido("comer", "sonidos/pacman_chomp.wav");
            sonidos.agregarSonido("beginning", "sonidos/pacman_beginning.wav");
            sonidos.agregarSonido("muerte", "sonidos/pacman_death.wav");
            sonidos.agregarSonido("background", "sonidos/pacman_background.wav");
        } catch (Exception e1) {
            throw new RuntimeException(e1);
        }

    }

    //aca se dibuja el juego
    private void dibujarInicioJuego(Graphics g) {
        tablero.dibujarse(g);
        jugador.dibujarse(g);
        this.comida.dibujarse(g);
        enemigoImagenViolet.dibujarse(g);
        enemigoImagenBlue.dibujarse(g);
        enemigoImagenRed.dibujarse(g);
        enemigoImagenGreen.dibujarse(g);
        vidas.dibujarse(g);
    }

    private void dibujarPortada(Graphics g) {
        portada.dibujarse(g);
    }

    private void dibujarEspera(Graphics g) {
        pantallaEsperar.dibujarse(g);
    }

    private void dibujarPantallaPerdedor(Graphics g) {
        pantallaPerdedor.dibujarse(g);
    }

    private void dibujarPantallaGanador(Graphics g) {
        pantallaGanador.dibujarse(g);
    }
}
