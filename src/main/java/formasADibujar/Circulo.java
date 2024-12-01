package formasADibujar;

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