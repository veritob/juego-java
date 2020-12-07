package ar.edu.ifts16;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;

public class Sonidos {

    private Map<String, Clip> sonidos;

    public Sonidos() {
        this.sonidos = new HashMap<>();
    }

    public void agregarSonido(String nombre, String archivo) {
        try {
            byte[] fileContent = Files
                    .readAllBytes(Paths.get(Sonidos.class.getClassLoader().getResource(archivo).toURI()));

            InputStream myInputStream = new ByteArrayInputStream(fileContent);
            AudioInputStream ais = AudioSystem.getAudioInputStream(myInputStream);
            DataLine.Info info = new DataLine.Info(Clip.class, ais.getFormat());
            Clip clip = (Clip) AudioSystem.getLine(info);
            clip.open(ais);
            sonidos.put(nombre, clip);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void tocarSonido(String sonido) {
        tocarSonido(sonido, false);
    }

    public void repetirSonido(String sonido) {
        tocarSonido(sonido, true);
    }

    public void detenerSonido(String sonido) {
        sonidos.get(sonido).stop();
    }

    private void tocarSonido(String sonido, boolean repetir) {
        Clip clip = sonidos.get(sonido);
        if (!clip.isRunning()) {
            clip.setFramePosition(0);
            if (repetir) {
                clip.loop(Clip.LOOP_CONTINUOUSLY);
            } else {
                clip.start();
            }
        }
    }
}