package formasADibujar;

import java.util.ArrayList;
import java.util.List;

public class Punto {
    private int x;
    private int y;
    private int z; // Added z coordinate
    private String nombrePunto;
    private static List<Punto> puntos = new ArrayList<>();
    private static int contadorPunto = 1;
    private int puntoNumero;

    // 2D Point Constructor
    public Punto(int x, int y) {
        this.x = x;
        this.y = y;
        this.z = 0; // Default z to 0 for 2D points
        puntos.add(this);
    }

    // 3D Point Constructor with boolean for line
    public Punto(int x, int y, int z, boolean esParteDeLinea) {
        this.x = x;
        this.y = y;
        this.z = z;
        if (esParteDeLinea) {
            this.nombrePunto = "P" + contadorPunto++;
        }
        puntos.add(this);
    }

    // 3D Point Constructor with point number
    public Punto(int x, int y, int z, int puntoNumero) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.puntoNumero = puntoNumero;
        puntos.add(this);
    }

    // 3D Point Constructor
    public Punto(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
        puntos.add(this);
    }

    // Getters
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() { // Added getter for z
        return z;
    }

    public String getNombrePunto() {
        return nombrePunto;
    }

    public static List<Punto> getPuntos() {
        return puntos;
    }

    public void setNombrePunto(String nombrePunto) {
        this.nombrePunto = nombrePunto;
    }

    public int getPuntoNumero() {
        return puntoNumero;
    }
}