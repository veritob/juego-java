package ar.edu.ifts16;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Paths;

public class Inicio {

    public static void main(String[] args) {

        // Propiedades del Juego
        int anchoVentana = 800;
        int largoVentana = 600;

        // Activar aceleracion de graficos en 2 dimensiones
        System.setProperty("sun.java2d.opengl", "true");

        // Crear un objeto de tipo JFrame que es la ventana donde va estar el juego
        JFrame ventana = new JFrame("Pac-Man ATR");
        String path = null;
        try {
            path = Paths.get(Inicio.class.getClassLoader().getResource("images/icono.png").toURI()).toString();
            ventana.setIconImage(ImageIO.read(new File(path)));
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }
        // Cerrar la aplicacion cuando el usuario hace click en la 'X'
        ventana.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // Abrir la ventana en el centro de la pantalla
        ventana.setLocationRelativeTo(null);

        // Mostrar la ventana
        ventana.setVisible(true);

        // Crear un "JPanel" llamado Juego y agregarlo a la ventana
        Juego panel = new Juego(anchoVentana, largoVentana);

        // Agregar a la ventana el JPanel (Panel hereda de JPanel)
        ventana.add(panel);

        // Achicar la ventana lo maximo posible para que entren los componentes
        ventana.pack();

        // Centrar pantalla
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        ventana.setLocation(dim.width / 2 - ventana.getSize().width / 2, dim.height / 2 - ventana.getSize().height / 2);

        // ventana.addMouseMotionListener(panel3);
        ventana.addKeyListener(panel);

        // Crear un thread y pasarle como parametro al juego que implementa la interfaz
        // "Runnable"
        Thread thread = new Thread(panel);

        // Arrancar el juego
        thread.start();
    }
}
