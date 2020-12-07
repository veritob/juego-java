package ar.edu.ifts16;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.ImageObserver;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class Inicio {
	private final Font smallfont = new Font("Helvetica", Font.BOLD, 14);
	private final int tamanioBloque = 24;
    private final int cantidadBloque = 15;
    private final int tamanioPantalla = cantidadBloque * tamanioBloque;
    private int vidas, puntaje;
    private Image pacman3left;
    private Dimension d;
    private Image ii;
    
    public static void main(String[] args) {

		// Propiedades del Juego
		int anchoVentana = 800;
		int largoVentana = 600;

		// Activar aceleracion de graficos en 2 dimensiones
		System.setProperty("sun.java2d.opengl", "true");

		// Crear un objeto de tipo JFrame que es la ventana donde va estar el juego
		JFrame ventana = new JFrame("Mi Juego");

		// Cerrar la aplicacion cuando el usuario hace click en la 'X'
		ventana.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		// Abrir la ventana en el centro de la pantalla
		ventana.setLocationRelativeTo(null);

		// Mostrar la ventana
		ventana.setVisible(true);

		// Crear un "JPanel" llamado Juego y agregarlo a la ventana

		Prueba1 panel = new Prueba1(anchoVentana, largoVentana);

		// Agregar a la ventana el JPanel (Panel hereda de JPanel)
		ventana.add(panel);

		// Achicar la ventana lo maximo posible para que entren los componentes
		ventana.pack();

		// Centrar pantalla
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		ventana.setLocation(dim.width/2-ventana.getSize().width/2, dim.height/2-ventana.getSize().height/2);

		// ventana.addMouseMotionListener(panel3);
		ventana.addKeyListener(panel);

		// Crear un thread y pasarle como parametro al juego que implementa la interfaz
		// "Runnable"
		Thread thread = new Thread(panel);

		// Arrancar el juego
		thread.start();	

	}
}
