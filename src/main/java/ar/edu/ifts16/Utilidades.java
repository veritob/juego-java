package ar.edu.ifts16;

public class Utilidades {

    public static boolean hayColision(
            int elemento1PosicionX, int elemento1PosicionY, int elemento1Ancho, int elemento1Largo,
            int elemento2PosicionX, int elemento2PosicionY, int elemento2Ancho, int elemento2Largo) {
        return haySolapamientoDeRango(
                elemento1PosicionX,
                elemento1PosicionX + elemento1Ancho,
                elemento2PosicionX,
                elemento2PosicionX + elemento2Ancho)
                &&
                haySolapamientoDeRango(
                        elemento1PosicionY,
                        elemento1PosicionY + elemento1Largo,
                        elemento2PosicionY,
                        elemento2PosicionY + elemento2Largo);
    }

    private static boolean haySolapamientoDeRango(int a, int b, int c, int d) {
        return a < d && b > c;
    }

}
