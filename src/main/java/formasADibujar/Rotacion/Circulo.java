package formasADibujar.Rotacion;

import java.util.ArrayList;
import java.util.List;

public class Circulo {
    private static List<Circulo> circulos = new ArrayList<>(); // Lista estática para almacenar los círculos
    private Punto centro;
    private int radio;

    // Constructor
    public Circulo(Punto centro, int radio) {
        this.centro = centro;
        this.radio = radio;
        circulos.add(this); // Agregar el círculo a la lista al crearlo
    }

    // Getters y setters
    public Punto getCentro() {
        return centro;
    }

    public void setCentro(Punto centro) {
        this.centro = centro;
    }

    public int getRadio() {
        return radio;
    }

    public void setRadio(int radio) {
        this.radio = radio;
    }

    // Método para calcular el área del círculo
    public double calcularArea() {
        return Math.PI * Math.pow(radio, 2);
    }

    // Método para calcular el perímetro del círculo (circunferencia)
    public double calcularPerimetro() {
        return 2 * Math.PI * radio;
    }

    // Método para obtener la lista de círculos
    public static List<Circulo> getCirculos() {
        return circulos;
    }

    // Método para dibujar el círculo en el plano cartesiano


    // Método para calcular los puntos del círculo
    public List<Punto> calcularPuntos() {
        List<Punto> puntos = new ArrayList<>();
        for (int angulo = 0; angulo < 360; angulo++) {
            double radianes = Math.toRadians(angulo);
            int x = (int) Math.round(centro.getX() + radio * Math.cos(radianes));
            int y = (int) Math.round(centro.getY() + radio * Math.sin(radianes));
            puntos.add(new Punto(x, y));
        }
        return puntos;
    }
}