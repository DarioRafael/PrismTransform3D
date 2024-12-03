package formasADibujar.Rotacion;

import formasADibujar.Rotacion.Punto;

import java.util.ArrayList;
import java.util.List;

public class Linea {
    private Punto puntoInicio;
    private Punto puntoFin;
    private static List<Linea> lineas = new ArrayList<>();
    private boolean esParteDeFiguraAnonima;
    private double puntoNumero; // Changed to double
    private static List<String> radioLabels = new ArrayList<>(); // Lista estática para los nombres de los radios
    private String nombreRadio; // Nuevo campo para el nombre del radio

    public Linea(Punto puntoInicio, Punto puntoFin, boolean esParteDeFiguraAnonima) {
        this.puntoInicio = puntoInicio;
        this.puntoFin = puntoFin;
        this.esParteDeFiguraAnonima = esParteDeFiguraAnonima;
        lineas.add(this);
    }

    public Linea(Punto puntoInicio, Punto puntoFin, boolean esParteDeFiguraAnonima, double puntoNumero) {
        this.puntoInicio = puntoInicio;
        this.puntoFin = puntoFin;
        this.esParteDeFiguraAnonima = esParteDeFiguraAnonima;
        this.puntoNumero = puntoNumero; // Asignar el número del punto
        lineas.add(this);
    }

    public double getPuntoNumero() {
        return puntoNumero;
    }

    // Nuevo getter y setter para el nombre del radio
    public String getNombreRadio() {
        return nombreRadio;
    }

    public void setNombreRadio(String nombreRadio) {
        this.nombreRadio = nombreRadio;
    }

    public Punto getPuntoInicio() {
        return puntoInicio;
    }

    public Punto getPuntoFin() {
        return puntoFin;
    }

    public static List<Linea> getLineas() {
        return lineas;
    }

}