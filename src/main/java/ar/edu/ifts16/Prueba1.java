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
	private ElementoBasico enemigo;


	// Pelotita
	private int pelotaPosicionX;
	private int pelotaPosicionY;
	private int pelotaVelocidadX;
	private int pelotaVelocidadY;
	private int pelotaAncho;
	private int pelotaLargo;

	public Prueba1(int anchoJuego, int largoJuego) {
		inicializarVentana(anchoJuego, largoJuego);
		this.pantallaActual = PANTALLA_INICIO;
		cargarSonidos();
		sonidos.tocarSonido("tic");
		this.sonidos.repetirSonido("tic");
		this.portada = new PantallaImagen(anchoJuego, largoJuego, "images/bienvenidoAlJuego.png");
		this.tablero = new PantallaImagen(anchoJuego, largoJuego, "images/tablero2.png");
		this.enemigo = new Enemigo(200, 200 - 20, 0, 0, 40, 40, Color.red);
		this.jugador = new Jugador(40, largoJuego - 60, 0, 0, 30, 30, Color.yellow);
		this.tableroPosiciones = inicializarTableroPosiciones(anchoJuego, largoJuego);
	}

	private int[][] inicializarTableroPosiciones(int anchoJuego, int largoJuego) {
		int [][] resultado = new int[largoJuego][anchoJuego];
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

	private void inicializarPelota() {
		this.pelotaAncho = 20;
		this.pelotaLargo = 20;
		this.pelotaPosicionX = 400;
		this.pelotaPosicionY = 300;

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
		enemigo.moverse();
	}

	private void verificarEstadoAmbiente() {
		
		if (enemigo.getPosicionX() < jugador.getPosicionX()) {
			enemigo.setVelocidadX(1);
		} else {
			enemigo.setVelocidadX(-1);
		}

		if (enemigo.getPosicionY() < jugador.getPosicionY()) {
			enemigo.setVelocidadY(1);
		} else {
			enemigo.setVelocidadY(-1);
		}
		verificarRebotePelotaContraParedLateral();
		verificarRebotePelotaContraLaParedSuperior();
	}

	// se verifica si la pelota colisiona contra la pared lateral, si es asi, se
	// hace rebotar la pelota en el eje X
	private void verificarRebotePelotaContraParedLateral() {

		double nuevaPosicionX = jugador.getPosicionX() + jugador.getVelocidadX();
		double nuevaPosicionY = jugador.getPosicionY() + jugador.getVelocidadY();

		if (jugador.getVelocidadX() != 0 || jugador.getVelocidadY() != 0) {
			System.out.println(String.format("(%s, %s)", nuevaPosicionX, nuevaPosicionY));
			System.out.println("anchoJuego = " + anchoJuego);
			System.out.println("largoJuego = " + largoJuego);
		}

		if (nuevaPosicionX >= anchoJuego || nuevaPosicionX < 0) {
			jugador.setVelocidadX(0);
		} else if (nuevaPosicionY >=  largoJuego || nuevaPosicionY < 0) {
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

	@Override
	public void keyPressed(KeyEvent e) {

		if (pantallaActual == PANTALLA_INICIO) {
			inicializarPelota();
			pantallaActual = PANTALLA_JUEGO;
		}

		if (pantallaActual == PANTALLA_JUEGO) {
			if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				jugador.setVelocidadX(3);
			}

			if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				jugador.setVelocidadX(-3);
			}
			
			if (e.getKeyCode() == KeyEvent.VK_UP) {
				jugador.setVelocidadY(-3);
			}
			
			if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				jugador.setVelocidadY(3);
			}
			
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// si suelto la tecla 39 o la 37 se asigna velocidad 0 a la paleta
		if (arg0.getKeyCode() == 39 || arg0.getKeyCode() == 37 || arg0.getKeyCode() == 38 || arg0.getKeyCode() == 40) {
			jugador.setVelocidadX(0);
			jugador.setVelocidadY(0);
		}
	}
	
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

	private void dibujarInicioJuego(Graphics g) {
		tablero.dibujarse(g);
		jugador.dibujarse(g);
		enemigo.dibujarse(g);
	}

	private void dibujarPortada(Graphics g) {
		portada.dibujarse(g);
	}

}

  
