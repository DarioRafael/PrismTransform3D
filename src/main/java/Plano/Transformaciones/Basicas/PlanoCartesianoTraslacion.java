package Plano.Transformaciones.Basicas;

import Plano.GenericsPlano.CoordinateSystem;
import formasADibujar.Linea;
import formasADibujar.Punto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.geom.AffineTransform;
import java.util.List;

public class PlanoCartesianoTraslacion extends JPanel {

    private double offsetX = 0, offsetY = 0,offsetZ = 0;
    private int gridSize = 50;
    private double zoomFactor = 1.0;
    private Point dragStart = null;

    private static final int GRID_SIZE = 50;
    private static final int AXIS_THICKNESS = 2;
    private static final int TICK_SIZE = 5;
    private static final int LABEL_OFFSET = 20;

    private CoordinateSystem.Type currentCoordSystem = CoordinateSystem.Type.CARTESIAN_ABSOLUTE;

    private static final Color COLOR_PUNTO_ORIGINAL = Color.RED;
    private static final Color COLOR_PUNTO_TRASLADADO = Color.BLUE;
    private static final Color COLOR_PUNTO_TRASLADADO2 = Color.magenta;

    private static final Color COLOR_LINEA_ORIGINAL = Color.RED;
    private static final Color COLOR_LINEA_TRASLADADO = Color.BLUE;
    private static final Color COLOR_LINEA_TRASLADADO2 = Color.magenta;

    public PlanoCartesianoTraslacion() {
        //offsetX = -GRID_SIZE * 4; // Desplazar hacia la derecha
        //offsetY = -GRID_SIZE * 2; // Desplazar hacia arriba
        offsetZ = 0; // Inicializar desplazamiento en Z
        zoomFactor = 0.7; // Ajustar el zoom inicial a 0.8x
        setupMouseListeners();
    }

    private void setupMouseListeners() {
        MouseAdapter mouseHandler = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                dragStart = e.getPoint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                dragStart = null;
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                handleMouseDrag(e);
            }

            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                handleMouseWheel(e);
            }
        };

        addMouseListener(mouseHandler);
        addMouseMotionListener(mouseHandler);
        addMouseWheelListener(mouseHandler);
    }

    private void handleMouseDrag(MouseEvent e) {
        if (dragStart != null) {
            Point dragEnd = e.getPoint();
            offsetX += (dragEnd.x - dragStart.x) / zoomFactor;
            offsetY += (dragEnd.y - dragStart.y) / zoomFactor;
            offsetZ += (dragEnd.y - dragStart.y) / zoomFactor;
            dragStart = dragEnd;
            repaint();
        }
    }

    private void handleMouseWheel(MouseWheelEvent e) {
        double oldZoom = zoomFactor;
        if (e.getWheelRotation() < 0) {
            zoomFactor *= 1.1;
        } else {
            zoomFactor /= 1.1;
        }

        Point mousePoint = e.getPoint();
        offsetX += mousePoint.x * (1 / oldZoom - 1 / zoomFactor);
        offsetY += mousePoint.y * (1 / oldZoom - 1 / zoomFactor);

        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        setupGraphics(g2);

        AffineTransform originalTransform = g2.getTransform();
        applyTransformation(g2);

        drawComponents(g2);
        g2.setTransform(originalTransform);
    }

    private void setupGraphics(Graphics2D g2) {
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        //COLOR DEL PLANO
        g2.setColor(Color.WHITE);
        g2.fillRect(0, 0, getWidth(), getHeight());
    }

    private void applyTransformation(Graphics2D g2) {
        g2.translate(getWidth() / 2.0, getHeight() / 2.0);
        g2.scale(zoomFactor, zoomFactor);
        g2.translate(offsetX, offsetY);
    }

    private void drawComponents(Graphics2D g2) {
        drawGrid(g2);
        drawAxes(g2);

        // Dibujar líneas polares si corresponde
        if (currentCoordSystem == CoordinateSystem.Type.POLAR_ABSOLUTE ||
                currentCoordSystem == CoordinateSystem.Type.POLAR_RELATIVE) {
        }

        drawPoints(g2);
        drawLines(g2);
    }

    private void drawGrid(Graphics2D g2) {

        g2.setColor(Color.GRAY);

        g2.setStroke(new BasicStroke(1));

        double viewportWidth = getWidth() / zoomFactor;
        double viewportHeight = getHeight() / zoomFactor;

        int startX = (int) Math.floor((-offsetX - viewportWidth / 2) / GRID_SIZE);
        int endX = (int) Math.ceil((-offsetX + viewportWidth / 2) / GRID_SIZE);
        int startY = (int) Math.floor((-offsetY - viewportHeight / 2) / GRID_SIZE);
        int endY = (int) Math.ceil((-offsetY + viewportHeight / 2) / GRID_SIZE);

        for (int i = startX; i <= endX; i++) {
            int x = i * GRID_SIZE;
            g2.drawLine(x, (int) (-offsetY - viewportHeight / 2), x, (int) (-offsetY + viewportHeight / 2));
        }

        for (int i = startY; i <= endY; i++) {
            int y = i * GRID_SIZE;
            g2.drawLine((int) (-offsetX - viewportWidth / 2), y, (int) (-offsetX + viewportWidth / 2), y);
        }
    }

    private void drawAxes(Graphics2D g2) {
        // COLOR DE LOS EJES
        g2.setColor(Color.BLACK);
        g2.setStroke(new BasicStroke(AXIS_THICKNESS));

        double viewportWidth = getWidth() / zoomFactor;
        double viewportHeight = getHeight() / zoomFactor;
        double viewportDepth = 100000;
        // Dibujar los ejes X e Y
        g2.drawLine((int) (-offsetX - viewportWidth / 2), 0, (int) (-offsetX + viewportWidth / 2), 0); // Eje X
        g2.drawLine(0, (int) (-offsetY - viewportHeight / 2), 0, (int) (-offsetY + viewportHeight / 2)); // Eje Y

        // Projection calculations for Z-axis
        int zOffsetX = (int) (GRID_SIZE * Math.cos(Math.toRadians(45)));
        int zOffsetY = (int) (GRID_SIZE * Math.sin(Math.toRadians(45)));


        g2.setFont(new Font("Arial", Font.PLAIN, 12));
        String prefix = (currentCoordSystem == CoordinateSystem.Type.CARTESIAN_RELATIVE ||
                currentCoordSystem == CoordinateSystem.Type.POLAR_RELATIVE) ? "d" : "";

        // Etiquetas de los ejes
        g2.drawString(prefix + "X", (int) (-offsetX + viewportWidth / 2) - LABEL_OFFSET, -LABEL_OFFSET);
        g2.drawString("-" + prefix + "X", (int) (-offsetX - viewportWidth / 2) + LABEL_OFFSET, -LABEL_OFFSET);
        g2.drawString(prefix + "Y", LABEL_OFFSET, (int) (-offsetY - viewportHeight / 2) + LABEL_OFFSET);
        g2.drawString("-" + prefix + "Y", LABEL_OFFSET, (int) (-offsetY + viewportHeight / 2) - LABEL_OFFSET);

        // Etiquetas del eje Z
        g2.drawString(prefix + "Z", (int) (-offsetX + viewportWidth / 2) - LABEL_OFFSET, -zOffsetY * 8 + LABEL_OFFSET); // Z positiva
        g2.drawString("-" + prefix + "Z", (int) (-offsetX - viewportWidth / 2) + LABEL_OFFSET, zOffsetY * 8 - LABEL_OFFSET); // Z negativa

        g2.setFont(new Font("Arial", Font.BOLD, 14));

        // Dibujar marcas en los ejes
        int startX = (int) Math.floor((-offsetX - viewportWidth / 2) / GRID_SIZE);
        int endX = (int) Math.ceil((-offsetX + viewportWidth / 2) / GRID_SIZE);
        int startY = (int) Math.floor((-offsetY - viewportHeight / 2) / GRID_SIZE);
        int endY = (int) Math.ceil((-offsetY + viewportHeight / 2) / GRID_SIZE);
        int startZ = (int) Math.floor((-offsetZ - viewportDepth / 2) / GRID_SIZE);
        int endZ = (int) Math.ceil((-offsetZ + viewportDepth / 2) / GRID_SIZE);


        // ejes Z
        for (int i = startZ; i <= endZ; i++) {
            int zx = i * zOffsetX;
            int zy = -i * zOffsetY;
            g2.drawLine(zx, zy, zx + zOffsetX, zy - zOffsetY); // Eje Z positivo
            g2.drawLine(-zx, -zy, -zx - zOffsetX, -zy + zOffsetY); // Eje Z negativo
        }


        // Dibujar marcas en el eje Z de manera similar a X e Y
        // Dibujar marcas en el eje Z
        for (int i = startZ; i <= endZ; i++) {
            if (i != 0) {
                int zx = (int) (-i * GRID_SIZE); // Invertir signo aquí
                int zy = (int) (i * GRID_SIZE); // Invertir signo aquí

                // Dibujar marca de línea
                g2.drawLine(zx - TICK_SIZE, zy - TICK_SIZE, zx + TICK_SIZE, zy + TICK_SIZE);

                // Dibujar número de marca
                g2.drawString(Integer.toString(i), zx + 15, zy - 5); // Invertir el signo del texto si es necesario
            }
        }
        // Dibujar marcas en el eje X
        for (int i = startX; i <= endX; i++) {
            if (i != 0) {
                int x = i * GRID_SIZE;
                g2.drawLine(x, -TICK_SIZE, x, TICK_SIZE);
                g2.drawString(Integer.toString(i), x - 5, LABEL_OFFSET);
            }
        }

        // Dibujar marcas en el eje Y
        for (int i = startY; i <= endY; i++) {
            if (i != 0) {
                int y = i * GRID_SIZE;
                g2.drawLine(-TICK_SIZE, y, TICK_SIZE, y);
                g2.drawString(Integer.toString(-i), -LABEL_OFFSET, y + 5);
            }
        }



        int arrowSize = 10; // Tamaño de la punta de la flecha

        // Dibujar flechas en los ejes
        // Flechas del eje X
        drawArrow(g2, (int) (-offsetX + viewportWidth / 2 - arrowSize), 0, 0); // Flecha X positivo
        drawArrow(g2, (int) (-offsetX - viewportWidth / 2 + arrowSize), 0, 180); // Flecha X negativo

        // Flechas del eje Y
        drawArrow(g2, 0, (int) (-offsetY - viewportHeight / 2 + arrowSize), -90); // Flecha Y positivo


// Flechas del eje Z
        drawArrow(g2, -zOffsetX * 8, zOffsetY * 8, 135); // Z positiva
        drawArrow(g2, zOffsetX * 8, -zOffsetY * 8, -45); // Z negativa

        // Dibujar punto de origen
        g2.fillOval(-3, -3, 6, 6);
    }


    // Método auxiliar para dibujar las flechas
    private void drawArrow(Graphics2D g2, int x, int y, int angle) {
        int arrowSize = 10; // Tamaño de la punta de la flecha
        AffineTransform tx = g2.getTransform();
        g2.translate(x, y);
        double radians = Math.toRadians(angle);
        g2.rotate(radians);
        g2.drawLine(0, 0, -arrowSize, -arrowSize);
        g2.drawLine(0, 0, -arrowSize, arrowSize);
        g2.setTransform(tx);
    }

    private void drawPoints(Graphics2D g2) {
        List<Punto> puntos = Punto.getPuntos();
        int contador = 0;  // Contador para cambiar la ubicación

        for (Punto punto : puntos) {
            // Calcular coordenadas con consideración de Z
            int x = punto.getX() * GRID_SIZE;
            int zOffset = (int) (-punto.getZ() * GRID_SIZE); // Invertir el signo de Z para offset
            int zHeight = (int) (-punto.getZ() * GRID_SIZE); // Invertir el signo de Z para altura
            int y = -punto.getY() * GRID_SIZE - zHeight; // Restar la altura Z de Y


            if (punto.getNombrePunto() != null && punto.getNombrePunto().contains("'")) {
                if (punto.getNombrePunto().contains("''")) {
                    g2.setColor(COLOR_PUNTO_TRASLADADO2);
                } else {
                    g2.setColor(COLOR_PUNTO_TRASLADADO);
                }
            } else {
                g2.setColor(COLOR_PUNTO_ORIGINAL);
                contador++;
                System.out.println(contador);
            }


            // Dibujar el punto con desplazamiento X y Z
            g2.fillOval(x + zOffset - 3, y - 3, 6, 6);

            // Dibujar nombre del punto si existe
            String nombrePunto = punto.getNombrePunto();
            if (nombrePunto != null) {
                switch (nombrePunto) {
                    case "P1":
                    case "P1'":
                    case "P1''":
                        g2.drawString(nombrePunto, x + zOffset - 20, y - 5);
                        break;
                    case "P2":
                    case "P2'":
                    case "P2''":
                        g2.drawString(nombrePunto, x + zOffset + 12, y);
                        break;
                    case "P3":
                    case "P3'":
                    case "P3''":
                        g2.drawString(nombrePunto, x + zOffset + 5, y );
                        break;
                    case "P4":
                    case "P4'":
                    case "P4''":
                        g2.drawString(nombrePunto, x + zOffset + 5, y - 5);
                        break;
                    case "P5":
                    case "P5'":
                    case "P5''":
                        g2.drawString(nombrePunto, x + zOffset, y - 10);
                        break;
                    case "P6":
                    case "P6'":
                    case "P6''":
                        g2.drawString(nombrePunto, x + zOffset + 5, y );
                        break;
                    case "P7":
                    case "P7'":
                    case "P7''":
                        g2.drawString(nombrePunto, x + zOffset - 10, y - 10);
                        break;
                    case "P8":
                    case "P8'":
                    case "P8''":
                        g2.drawString(nombrePunto, x + zOffset - 15, y - 5);
                        break;
                    default:
                        g2.drawString(nombrePunto, x + zOffset + 2, y - 2);
                        break;
                }

            }
        }
    }

    private void drawLines(Graphics2D g2) {
        g2.setStroke(new BasicStroke(2));
        List<Linea> lineas = Linea.getLineas();

        for (Linea linea : lineas) {
            Punto inicio = linea.getPuntoInicio();
            Punto fin = linea.getPuntoFin();

            // Calcular coordenadas con consideración de Z
            int x1 = inicio.getX() * GRID_SIZE;
            int y1 = -inicio.getY() * GRID_SIZE;
            int z1Offset = (int) (-inicio.getZ() * GRID_SIZE);
            int z1Height = (int) (-inicio.getZ() * GRID_SIZE);

            int x2 = fin.getX() * GRID_SIZE;
            int y2 = -fin.getY() * GRID_SIZE;
            int z2Offset = (int) (-fin.getZ() * GRID_SIZE);
            int z2Height = (int) (-fin.getZ() * GRID_SIZE);

            // Ajustar coordenadas con desplazamientos de Z
            y1 -= z1Height;
            y2 -= z2Height;

            Color[] sectionColors = {
                    new Color(0, 0, 255, 255),   // Azul
                    new Color(255, 0, 0, 255),   // Rojo

                    new Color(0, 255, 0, 255),   // Verde
                    new Color(139, 69, 19, 255),  // Marrón


                    new Color(255, 0, 255, 180),
                    new Color(0, 255, 255, 180)
            };

            // Determinar el color basado en la secuencia de puntos
            if (inicio.getNombrePunto() != null) {
                // Secciones sin comillas y con comillas vacías
                String[] firstSectionWithoutQuotes = {"P1", "P2", "P3", "P4", "P5", "P6", "P7", "P8", "P7"};
                String[] firstSectionWithQuotes = {"P1'", "P2'", "P3'", "P4'", "P5'", "P6'", "P7'", "P8'", "P7'"};

                String[] secondSectionWithoutQuotes = {"P8", "P1", "P4", "P5", "P8", "P7", "P2", "P3", "P6"};
                String[] secondSectionWithQuotes = {"P8'", "P1'", "P4'", "P5'", "P8'", "P7'", "P2'", "P3'", "P6'"};

                // Verificar en qué sección se encuentra la línea actual
                boolean isFirstSectionWithoutQuotes = false;
                boolean isFirstSectionWithQuotes = false;
                boolean isSecondSectionWithoutQuotes = false;
                boolean isSecondSectionWithQuotes = false;

                // Verificar primera sección sin comillas
                for (int i = 0; i < firstSectionWithoutQuotes.length - 1; i++) {
                    if (inicio.getNombrePunto().equals(firstSectionWithoutQuotes[i]) &&
                            fin.getNombrePunto().equals(firstSectionWithoutQuotes[i+1])) {
                        isFirstSectionWithoutQuotes = true;
                        break;
                    }
                }

                // Verificar primera sección con comillas
                for (int i = 0; i < firstSectionWithQuotes.length - 1; i++) {
                    if (inicio.getNombrePunto().equals(firstSectionWithQuotes[i]) &&
                            fin.getNombrePunto().equals(firstSectionWithQuotes[i+1])) {
                        isFirstSectionWithQuotes = true;
                        break;
                    }
                }

                // Verificar segunda sección sin comillas
                for (int i = 0; i < secondSectionWithoutQuotes.length - 1; i++) {
                    if (inicio.getNombrePunto().equals(secondSectionWithoutQuotes[i]) &&
                            fin.getNombrePunto().equals(secondSectionWithoutQuotes[i+1])) {
                        isSecondSectionWithoutQuotes = true;
                        break;
                    }
                }

                // Verificar segunda sección con comillas
                for (int i = 0; i < secondSectionWithQuotes.length - 1; i++) {
                    if (inicio.getNombrePunto().equals(secondSectionWithQuotes[i]) &&
                            fin.getNombrePunto().equals(secondSectionWithQuotes[i+1])) {
                        isSecondSectionWithQuotes = true;
                        break;
                    }
                }

                // Asignar color según la sección
                if (isFirstSectionWithoutQuotes) {
                    g2.setColor(sectionColors[0]);
                } else if (isFirstSectionWithQuotes) {
                    g2.setColor(sectionColors[2]);
                } else if (isSecondSectionWithoutQuotes) {
                    g2.setColor(sectionColors[1]);
                } else if (isSecondSectionWithQuotes) {
                    g2.setColor(sectionColors[3]);
                } else {
                    // Color por defecto para otras líneas
                    g2.setColor(COLOR_LINEA_ORIGINAL);
                }
            } else {
                g2.setColor(COLOR_LINEA_ORIGINAL);
            }

            // Dibujar la línea
            g2.drawLine(x1 + z1Offset, y1, x2 + z2Offset, y2);
        }
    }



    // Método para agregar un nuevo punto y repintar
    public void addPunto(Punto punto) {
        Punto.getPuntos().add(punto);
        repaint(); // Redibujar el plano
    }


    public void addLinea(Linea linea) {
        Linea.getLineas().add(linea);
        repaint(); // Redibujar el plano para reflejar los cambios
    }


    public void clear() {
        Punto.getPuntos().clear();
        Linea.getLineas().clear();
        repaint(); // Redibujar el plano para reflejar los cambios
    }




    public CoordinateSystem.Type getCurrentCoordSystem() {
        return currentCoordSystem;
    }

    // Asegúrate de que este método esté actualizado para manejar el cambio de sistema de coordenadas
    public void setCurrentCoordSystem(CoordinateSystem.Type coordSystem) {
        if (this.currentCoordSystem != coordSystem) {
            this.currentCoordSystem = coordSystem;
            System.out.println("Cambiando sistema de coordenadas a: " + coordSystem); // Debug
            repaint();
        }
    }

}

