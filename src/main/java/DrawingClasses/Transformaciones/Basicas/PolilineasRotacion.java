package DrawingClasses.Transformaciones.Basicas;

import PaginaPrincipalFolder.Transformaciones.Componentes.TransformacionesBasicas;
import PaginaPrincipalFolder.Transformaciones.PaginasImport.FormulasTBasicas.FormulaRotacion;
import Plano.Transformaciones.Basicas.PlanoCartesianoRotacion;
import formasADibujar.Rotacion.Linea;
import formasADibujar.Rotacion.Punto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PolilineasRotacion extends JFrame {
    private PlanoCartesianoRotacion planoCartesiano;
    private JTable originalTable;
    private JTable rotatedTable;
    private DefaultTableModel originalTableModel;
    private DefaultTableModel rotatedTableModel;
    private JButton backButton, formulaButton;
    private JTextField xInicialField, yInicialField, ZInicialField;
    public JTextField anguloField;
    private JLabel anguloLabel;
    private JButton regenerarFigura;
    private JButton rotarButton;
    private List<Punto> puntosList;
    private List<Punto> puntosRotadosList;
    public JComboBox<String> rotacionesComboBox;
    public int anguloText = 0;

    public PolilineasRotacion() {
        setTitle("Transformaciones Geométricas 3D Básica: Rotacion");
        setSize(1800, 960);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        createComponents();
        configureLayout();
        addActionListeners();
        setVisible(true);
    }

    private void createComponents() {
        planoCartesiano = new PlanoCartesianoRotacion();
        planoCartesiano.setPreferredSize(new Dimension(600, 400));

        xInicialField = new JTextField("2", 5);
        yInicialField = new JTextField("0", 5);
        ZInicialField = new JTextField("1", 5);

        anguloField = new JTextField("0", 5);

        backButton = new JButton("Menu");
        formulaButton = new JButton("Formula");
        regenerarFigura = new JButton("Graficar");
        rotarButton = new JButton("Rotar");


        String[] rotacionComboLabels = {"Rotacion eje x", "Rotacion eje y", "Rotacion eje z"};
        rotacionesComboBox = new JComboBox<>(rotacionComboLabels);
        rotacionesComboBox.setSelectedIndex(0); // Valor por defecto: x1

        String[] columnNames = {"Punto", "X", "Y", "Z"};
        String[] columnNamesEdi = {"P'", "X'", "Y'", "Z'"};

        originalTableModel = new DefaultTableModel(columnNames, 0);
        rotatedTableModel = new DefaultTableModel(columnNamesEdi, 0);
        originalTable = new JTable(originalTableModel);
        rotatedTable = new JTable(rotatedTableModel);


    }

    private void configureLayout() {
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new BorderLayout());
        JLabel titleLabel1 = new JLabel("Transformaciones Geométricas 3D Básica:", SwingConstants.CENTER);
        titleLabel1.setFont(new Font("Arial", Font.BOLD, 20));

        JLabel titleLabel2 = new JLabel("Rotación", SwingConstants.CENTER);
        titleLabel2.setFont(new Font("Arial", Font.BOLD, 18));

        // Create a panel to hold the Menu and Formulas buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttonPanel.add(backButton);
        buttonPanel.add(formulaButton);

        topPanel.add(buttonPanel, BorderLayout.SOUTH);
        topPanel.add(titleLabel1, BorderLayout.NORTH);
        topPanel.add(titleLabel2, BorderLayout.CENTER);

        add(topPanel, BorderLayout.NORTH);

        add(planoCartesiano, BorderLayout.CENTER);

        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.setPreferredSize(new Dimension(270, getHeight())); // Ajusta el tamaño preferido

        JScrollPane rightScrollPane = new JScrollPane(rightPanel);
        rightScrollPane.setPreferredSize(new Dimension(270, getHeight())); // Ajusta el tamaño preferido del JScrollPane

        JPanel tablesPanel = new JPanel(new GridLayout(3, 1, 5, 5));

        JPanel originalTablePanel = new JPanel(new BorderLayout());

        JLabel originalLabel = new JLabel("Puntos Originales", SwingConstants.CENTER);
        originalLabel.setFont(new Font("Arial", Font.BOLD, 12)); // Set font to Arial, bold, size 18
        originalTablePanel.add(originalLabel, BorderLayout.NORTH);
        JScrollPane originalScrollPane = new JScrollPane(originalTable);

        originalScrollPane.setPreferredSize(new Dimension(300, 200));
        originalTablePanel.add(originalScrollPane, BorderLayout.CENTER);

        JPanel rotatedTablePanel = new JPanel(new BorderLayout());

        JLabel scaledLabel = new JLabel("Puntos Rotados (θ: 0°)", SwingConstants.CENTER);
        scaledLabel.setFont(new Font("Arial", Font.BOLD, 12)); // Set font to Arial, bold, size 18
        rotatedTablePanel.add(scaledLabel, BorderLayout.NORTH);

        JScrollPane rotatedScrollPane = new JScrollPane(rotatedTable);


        rotatedScrollPane.setPreferredSize(new Dimension(300, 200));
        rotatedTablePanel.add(rotatedScrollPane, BorderLayout.CENTER);

        tablesPanel.add(originalTablePanel);
        tablesPanel.add(rotatedTablePanel);

        rightPanel.add(tablesPanel, BorderLayout.CENTER);

        JPanel controlPanel = new JPanel(new GridLayout(10, 2, 5, 5));
        controlPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Initial configuration inputs
        controlPanel.add(new JLabel("X inicial:"));
        controlPanel.add(xInicialField);
        controlPanel.add(new JLabel("Y inicial:"));
        controlPanel.add(yInicialField);
        controlPanel.add(new JLabel("Z inicial:"));
        controlPanel.add(ZInicialField);
        controlPanel.add(new JLabel(""));
        controlPanel.add(regenerarFigura);

        controlPanel.add(new JSeparator());
        controlPanel.add(new JSeparator());


        controlPanel.add(new JLabel ("R(θ):"));
        controlPanel.add(anguloField);
        controlPanel.add(rotacionesComboBox);
        controlPanel.add(rotarButton);

        rightPanel.add(controlPanel, BorderLayout.NORTH);
        add(rightScrollPane, BorderLayout.EAST);
    }

    private void addActionListeners() {
        backButton.addActionListener(e -> {
            new TransformacionesBasicas().setVisible(true);
            clearPlanoAndData();
            dispose();
        });

        formulaButton.addActionListener(e -> {
            new FormulaRotacion().setVisible(true);
        });

        regenerarFigura.addActionListener(e -> {
            int xInicio = Integer.parseInt(xInicialField.getText());
            int yInicio = Integer.parseInt(yInicialField.getText());
            int zInicio = Integer.parseInt(ZInicialField.getText());
            drawFiguraOriginal(xInicio, yInicio, zInicio);
        });


        rotarButton.addActionListener(e -> realizarRotacion());
    }

    public void drawFiguraOriginal(double xInicio, double yInicio, double zInicio) {
        clearPlanoAndData();
        try {
            // Define los puntos iniciales
            Punto punto1 = new Punto(xInicio, yInicio, zInicio);        // P1
            Punto punto2 = new Punto(xInicio + 4, yInicio, zInicio + 1); // P2
            Punto punto3 = new Punto(xInicio + 4, yInicio, zInicio - 1); // P3
            Punto punto4 = new Punto(xInicio, yInicio, zInicio - 2);    // P4
            Punto punto5 = new Punto(xInicio, yInicio + 1, zInicio - 2); // P5
            Punto punto6 = new Punto(xInicio + 3, yInicio, zInicio - 2); // P6
            Punto punto7 = new Punto(xInicio + 3, yInicio, zInicio);    // P7
            Punto punto8 = new Punto(xInicio - 1, yInicio, zInicio - 1); // P8

            // Utiliza las referencias de los puntos ya creados para evitar duplicados
            Punto[] puntosArray = {
                    punto1, // P1
                    punto2, // P2
                    punto3, // P3
                    punto4, // P4
                    punto5, // P5
                    punto6, // P6
                    punto7, // P7
                    punto8, // P8

                    // Segunda parte (referencia a puntos existentes)
                    punto1, // P1
                    punto4, // P4
                    punto5, // P5
                    punto8, // P8
                    punto7, // P7
                    punto2, // P2
                    punto3, // P3
                    punto6  // P6
            };

            puntosList = Arrays.asList(puntosArray);

            // Asignar nombres a los puntos
            for (int i = 0; i < 8; i++) {
                puntosList.get(i).setNombrePunto("P" + (i + 1));
            }


            int[] referencias = {1, 4, 5, 8, 7, 2, 3, 6};
            for (int i = 8; i < puntosList.size(); i++) {
                puntosList.get(i).setNombrePunto("P" + referencias[i - 8]);
            }

            // Dibuja la figura
            Punto puntoAnterior = punto1;  // Punto de inicio
            planoCartesiano.addPunto(punto1);  // Agrega el primer punto

            for (int i = 0; i < puntosList.size(); i++) {
                Punto punto = puntosList.get(i);
                planoCartesiano.addPunto(punto);
                planoCartesiano.addLinea(new Linea(puntoAnterior, punto, true, i + 1));
                puntoAnterior = punto;
            }

            // Actualiza la tabla de puntos originales
            updateOriginalTable(puntosList);

            planoCartesiano.repaint();
            updateLabels("0");

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese valores numéricos válidos.");
        }
    }

    private void realizarRotacion() {
        try {
            double angulo = Math.toRadians(Double.parseDouble(anguloField.getText()));
            String ejeRotacion = (String) rotacionesComboBox.getSelectedItem();

            if (puntosList == null || puntosList.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Primero debe generar la figura original");
                return;
            }

            planoCartesiano.clear();

            // Redibujar la figura original
            Punto puntoAnteriorOriginal = puntosList.get(0);
            planoCartesiano.addPunto(puntoAnteriorOriginal);

            for (int i = 1; i < puntosList.size(); i++) {
                Punto puntoOriginal = puntosList.get(i);
                planoCartesiano.addPunto(puntoOriginal);
                planoCartesiano.addLinea(new Linea(puntoAnteriorOriginal, puntoOriginal, true, i));
                puntoAnteriorOriginal = puntoOriginal;
            }

            puntosRotadosList = new ArrayList<>();

            // Rotación según el eje seleccionado
            for (int i = 0; i < puntosList.size(); i++) {
                Punto puntoOriginal = puntosList.get(i);
                Punto puntoRotado;

                switch (ejeRotacion) {
                    case "Rotacion eje x":
                        double yX = puntoOriginal.getY();
                        double zX = puntoOriginal.getZ();
                        puntoRotado = new Punto(
                                puntoOriginal.getX(),
                                (yX * Math.cos(angulo)) - (zX * Math.sin(angulo)),
                                (yX * Math.sin(angulo)) + (zX * Math.cos(angulo))
                        );
                        break;

                    case "Rotacion eje y":
                        double xY = puntoOriginal.getX();
                        double zY = puntoOriginal.getZ();
                        puntoRotado = new Punto(
                                xY * Math.cos(angulo) + zY * Math.sin(angulo),
                                puntoOriginal.getY(),
                                -xY * Math.sin(angulo) + zY * Math.cos(angulo)
                        );
                        break;

                    case "Rotacion eje z":
                        double xZ = puntoOriginal.getX();
                        double yZ = puntoOriginal.getY();
                        puntoRotado = new Punto(
                                xZ * Math.cos(angulo) - yZ * Math.sin(angulo),
                                xZ * Math.sin(angulo) + yZ * Math.cos(angulo),
                                puntoOriginal.getZ()
                        );
                        break;

                    default:
                        puntoRotado = puntoOriginal;
                        break;
                }

                puntosRotadosList.add(puntoRotado);
            }

            // Asignar nombres a los puntos rotados usando el mismo patrón de referencias
            int[] referencias = {1, 4, 5, 8, 7, 2, 3, 6};
            for (int i = 0; i < puntosRotadosList.size(); i++) {
                if (i < 8) {
                    puntosRotadosList.get(i).setNombrePunto("P" + (i + 1) + "'");
                } else {
                    puntosRotadosList.get(i).setNombrePunto("P" + referencias[i - 8] + "'");
                }
            }

            // Dibujar figura rotada
            Punto puntoAnterior = puntosRotadosList.get(0);
            planoCartesiano.addPunto(puntoAnterior);

            for (int i = 1; i < puntosRotadosList.size(); i++) {
                Punto punto = puntosRotadosList.get(i);
                planoCartesiano.addPunto(punto);
                Linea linea = new Linea(puntoAnterior, punto, true, i);
                planoCartesiano.addLinea(linea);
                puntoAnterior = punto;
            }

            updateRotatedTable(puntosRotadosList);
            planoCartesiano.repaint();

            // Update label with angle and axis
            Component parent = rotatedTable.getParent().getParent().getParent();
            if (parent instanceof JPanel) {
                ((JLabel) ((JPanel) parent).getComponent(0)).setText("Puntos Rotados: " +
                        "Ángulo de rotación (" + ejeRotacion + "): " + anguloField.getText() + "°");
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese un valor numérico válido para el ángulo.");
        }
    }

    private void updateLabels(String r) {
        // Actualizar la etiqueta de la tabla escalada
        Component parent = rotatedTable.getParent().getParent().getParent();
        if (parent instanceof JPanel) {
            ((JLabel) ((JPanel) parent).getComponent(0)).setText("Puntos Rotados " +
                    "(θ: "+r+"°)");
        }
    }

    private void clearPlanoAndData() {
        planoCartesiano.clear();
        originalTableModel.setRowCount(0);
        rotatedTableModel.setRowCount(0);
    }

    private void updateOriginalTable(List<Punto> puntos) {
        originalTableModel.setRowCount(0);
        for (Punto punto : puntos) {
            originalTableModel.addRow(new Object[]{
                    punto.getNombrePunto(),
                    punto.getX(),
                    punto.getY(),
                    punto.getZ()
            });
        }
    }

    private void updateRotatedTable(List<Punto> puntos) {
        rotatedTableModel.setRowCount(0);
        for (Punto punto : puntos) {
            rotatedTableModel.addRow(new Object[]{
                    punto.getNombrePunto(),
                    String.format("%.2f", punto.getX()),
                    String.format("%.2f", punto.getY()),
                    String.format("%.2f", punto.getZ())
            });
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PolilineasRotacion frame = new PolilineasRotacion();

        });
    }
}