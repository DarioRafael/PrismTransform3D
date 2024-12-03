package formasADibujar.Rotacion;

import java.util.ArrayList;
import java.util.List;
public class Punto {
    private double x;
    private double y;
    private double z; // Changed to double
    private String nombrePunto;
    private static List<Punto> puntos = new ArrayList<>();
    private static int contadorPunto = 1;
    private int puntoNumero;

    // 2D Point Constructor
    public Punto(double x, double y) {
        this.x = x;
        this.y = y;
        this.z = 0.0; // Default z to 0.0 for 2D points
        puntos.add(this);
    }

    // 3D Point Constructor with boolean for line
    public Punto(double x, double y, double z, boolean esParteDeLinea) {
        this.x = x;
        this.y = y;
        this.z = z;
        if (esParteDeLinea) {
            this.nombrePunto = "P" + contadorPunto++;
        }
        puntos.add(this);
    }

    // 3D Point Constructor with point number
    public Punto(double x, double y, double z, int puntoNumero) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.puntoNumero = puntoNumero;
        puntos.add(this);
    }

    // 3D Point Constructor
    public Punto(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
        puntos.add(this);
    }

    // Getters
    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() { // Changed return type to double
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