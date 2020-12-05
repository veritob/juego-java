package ar.edu.ifts16;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

public class Prueba1 extends JPanel implements Runnable, KeyListener {

	private static final long serialVersionUID = 1L;
	private final static int PANTALLA_INICIO = 1;
	private final static int PANTALLA_JUEGO = 2;
	private int anchoJuego;
	private int largoJuego;
	private Sonidos sonidos;
	private PantallaImagen portada;
	private PantallaImagen tablero;
	private int[][] tableroPosiciones;
	private int pantallaActual;
	private ElementoBasico jugador;
	private ElementoBasico enemigoImagenViolet;
	private ElementoBasico enemigoImagenBlue;
	private ElementoBasico enemigoImagenRed;
	private ElementoBasico enemigoImagenGreen;

	public Prueba1(int anchoJuego, int largoJuego) {
		inicializarVentana(anchoJuego, largoJuego);
		this.pantallaActual = PANTALLA_INICIO;
		cargarSonidos();
		sonidos.tocarSonido("tic");
		this.sonidos.repetirSonido("tic");
		this.portada = new PantallaImagen(anchoJuego, largoJuego, "images/bienvenidoAlJuego2.png");
		this.tablero = new PantallaImagen(anchoJuego, largoJuego, "images/tablero2.png");
		this.enemigoImagenViolet = new EnemigoImagen(300, 300 - 20, 0, 0, 40, 40, "/ghostViolet.gif");
		this.enemigoImagenBlue = new EnemigoImagen(100, 100 - 20, 0, 0, 40, 40, "/ghostBlue.gif");
		this.enemigoImagenRed = new EnemigoImagen(300, 100 - 20, 0, 0, 40, 40, "/ghostRed.gif");
		this.enemigoImagenGreen = new EnemigoImagen(400, 300 - 20, 0, 0, 40, 40, "/ghostGreen.gif");
		this.jugador = new JugadorImagen(40, largoJuego - 60, 0, 0, 30, 30);
		this.tableroPosiciones = inicializarTableroPosiciones(anchoJuego, largoJuego);
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
			actualizarAmbiente();
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
// aca le se le indica mopvimiento a los enemigos.-
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
			enemigoImagenRed.setVelocidadX(0.5);
		}

		if (enemigoImagenRed.getPosicionY() < jugador.getPosicionY()) {
			enemigoImagenRed.setVelocidadY(0.5);
		} else {
			enemigoImagenRed.setVelocidadY(0.5);
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

		verificarRebotePelotaContraParedLateral();
		verificarRebotePelotaContraLaParedSuperior();
	}

	// se verifica si la pelota colisiona contra la pared lateral, si es asi, se
	// hace rebotar la pelota en el eje X
	private void verificarRebotePelotaContraParedLateral() {

		double nuevaPosicionX = jugador.getPosicionX() + jugador.getVelocidadX();
		double nuevaPosicionY = jugador.getPosicionY() + jugador.getVelocidadY();

//		if (jugador.getVelocidadX() != 0 || jugador.getVelocidadY() != 0) {
//			System.out.println(String.format("(%s, %s)", nuevaPosicionX, nuevaPosicionY));
//			System.out.println("anchoJuego = " + anchoJuego);
//			System.out.println("largoJuego = " + largoJuego);
//		}

		if (nuevaPosicionX >= anchoJuego || nuevaPosicionX < 0) {
			jugador.setVelocidadX(0);
		} else if (nuevaPosicionY >= largoJuego || nuevaPosicionY < 0) {
			jugador.setVelocidadY(0);
		} else if (tableroPosiciones[(int) nuevaPosicionY][(int) nuevaPosicionX] == 1) {
			jugador.setVelocidadX(0);
			jugador.setVelocidadY(0);
		}
	}

	// se verifica si la pelota colisiona contra la pared superior, si es asi se
	// hace rebotar la pelota en el eje Y
	private void verificarRebotePelotaContraLaParedSuperior() {
		if (jugador.getPosicionY() <= 0 || jugador.getPosicionY() + jugador.getAncho() >= largoJuego) {
			jugador.setVelocidadY(0);
		}
	}

	private void dibujar() {
		this.repaint();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		if (pantallaActual == PANTALLA_INICIO) {
			dibujarPortada(g);
		} else {
			dibujarInicioJuego(g);

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
			pantallaActual = PANTALLA_JUEGO;
		}
// aca se setea el movimiento del jugador con su velocidad(setVelocidadX) y x otro lado se le envia el valor de la tecla que toco a jugador imagen para cambiar la posicion de la boca en la imagen
		if (pantallaActual == PANTALLA_JUEGO) {
			if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				jugador.setMoverse(3);
				jugador.setVelocidadX(3);
			}

			if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				jugador.setMoverse(4);
				jugador.setVelocidadX(-3);
			}

			if (e.getKeyCode() == KeyEvent.VK_UP) {
				jugador.setMoverse(1);
				jugador.setVelocidadY(-3);
			}

			if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				jugador.setMoverse(2);
				jugador.setVelocidadY(3);
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
// aca se utiliza solo el sonido de background, "el resto estan para usarse o cambiarse"
	private void cargarSonidos() {
		try {
			sonidos = new Sonidos();
			sonidos.agregarSonido("toc", "sonidos/pacman_chomp.wav");
			sonidos.agregarSonido("tic", "sonidos/pacman_beginning.wav");
			sonidos.agregarSonido("muerte", "sonidos/pacman_death.wav");
			sonidos.agregarSonido("background", "sonidos/pacman_beginning.wav");
		} catch (Exception e1) {
			throw new RuntimeException(e1);
		}

	}
//aca se dibuja todo, tablero de fondo, jugador y enemigos-(aca deberias dibujar la comida)
	private void dibujarInicioJuego(Graphics g) {
		tablero.dibujarse(g);
		jugador.dibujarse(g);
		enemigoImagenViolet.dibujarse(g);
		enemigoImagenBlue.dibujarse(g);
		enemigoImagenRed.dibujarse(g);
		enemigoImagenGreen.dibujarse(g);
	}
//aca se dibuja solo la pantalla de inicio
	private void dibujarPortada(Graphics g) {
		portada.dibujarse(g);
	}

}
