package DrawingClasses.Transformaciones.Compuestas;

import PaginaPrincipalFolder.Transformaciones.Componentes.TransformacionesCompuestas;
import PaginaPrincipalFolder.Transformaciones.PaginasImport.FormulasTCompuestas.FormulaRotacionSuc;
import Plano.Transformaciones.Basicas.PlanoCartesianoRotacion;
import formasADibujar.Rotacion.Linea;
import formasADibujar.Rotacion.Punto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PolilineasRotacionSuc extends JFrame {
    private PlanoCartesianoRotacion planoCartesiano;
    private JTable originalTable;
    private JTable firstRotationTable;
    private JTable secondRotationTable;
    private DefaultTableModel originalTableModel;
    private DefaultTableModel rotatedTableModel;
    private DefaultTableModel rotatedTableModel2;
    private JButton backButton, formulaButton;
    private JTextField xInicialField, yInicialField, ZInicialField;
    private JTextField primerAnguloField;
    private JTextField segundoAnguloField;
    private JButton regenerarFigura;
    private JButton rotarButton;
    private List<Punto> puntosList;
    private List<Punto> primeraRotacionList;
    private List<Punto> segundaRotacionList;
    public JComboBox<String> rotacionesComboBox, rotacionesComboBox2;
    private JButton primerRotacionButton;
    private JButton segundaRotacionButton;
    private boolean primeraRotacionCompletada = false;
    private JLabel rotationTable1Label;
    private JLabel rotationTable2Label;
    private JLabel titleLabel3, titleLabel4;
    double primerAngulo;
    double segundoAngulo;
    private String  ejeRotadoLabel = "x",ejeRotadoLabel2 = "x";

    public PolilineasRotacionSuc() {
        setTitle("Transformaciones Geométricas 2D Compuestas: Rotaciones Sucesivas");
        setSize(1850, 960);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setMinimumSize(new Dimension(800, 600)); // Establecer tamaño mínimo
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


        primerAnguloField = new JTextField("90", 5);
        segundoAnguloField = new JTextField("90", 5);

        backButton = new JButton("Menu");
        formulaButton = new JButton("Formula");

        regenerarFigura = new JButton("Graficar");
        primerRotacionButton = new JButton("Rotar");
        segundaRotacionButton = new JButton("Rotar");
        segundaRotacionButton.setEnabled(false);

        String[] rotacionComboLabels = {"sobre el eje x", "sobre el eje y", "sobre el eje z"};
        rotacionesComboBox = new JComboBox<>(rotacionComboLabels);
        rotacionesComboBox.setSelectedIndex(0);

        rotacionesComboBox2 = new JComboBox<>(rotacionComboLabels);
        rotacionesComboBox2.setSelectedIndex(2);

        String[] columnNames = {"Punto", "X", "Y", "Z", "Cod"};
        String[] columnNamesFirst = {"P'", "X'", "Y'", "Z'", "Cod"};
        String[] columnNamesSecond = {"P''", "X''", "Y''", "Z''","Cod"};

        originalTableModel = new DefaultTableModel(columnNames, 0);
        rotatedTableModel = new DefaultTableModel(columnNamesFirst, 0);
        rotatedTableModel2 = new DefaultTableModel(columnNamesSecond, 0);

        originalTable = new JTable(originalTableModel);
        firstRotationTable = new JTable(rotatedTableModel);
        secondRotationTable = new JTable(rotatedTableModel2);

        originalTable.setRowHeight(20);
        firstRotationTable.setRowHeight(20);
        secondRotationTable.setRowHeight(20);

        originalTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        firstRotationTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        secondRotationTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

    }

    private void configureLayout() {
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new BorderLayout());
        JLabel titleLabel1 = new JLabel("Transformaciones Geométricas 3D Compuestas:", SwingConstants.CENTER);
        titleLabel1.setFont(new Font("Arial", Font.BOLD, 20));

        JLabel titleLabel2 = new JLabel("Prisma Cuadrangular", SwingConstants.CENTER);
        titleLabel2.setFont(new Font("Arial", Font.BOLD, 18));

        titleLabel3 = new JLabel("Rotación 3D "+ (String) rotacionesComboBox.getSelectedItem()+": R"+ejeRotadoLabel+"(" + 0 + "°) ", SwingConstants.CENTER);
        titleLabel3.setFont(new Font("Arial", Font.BOLD, 16));

        titleLabel4 = new JLabel("Rotación 3D "+ (String) rotacionesComboBox.getSelectedItem()+": R"+ejeRotadoLabel2+"(" + 0 + "°) ", SwingConstants.CENTER);
        titleLabel4.setFont(new Font("Arial", Font.BOLD, 16));

        JPanel titlePanel = new JPanel(new GridLayout(3, 1));
        titlePanel.add(titleLabel2);
        titlePanel.add(titleLabel3);
        titlePanel.add(titleLabel4);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttonPanel.add(backButton);
        buttonPanel.add(formulaButton);

        topPanel.add(buttonPanel, BorderLayout.SOUTH);
        topPanel.add(titleLabel1, BorderLayout.NORTH);
        topPanel.add(titlePanel, BorderLayout.CENTER);

        add(topPanel, BorderLayout.NORTH);

        add(planoCartesiano, BorderLayout.CENTER);

        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.setPreferredSize(new Dimension(0, getHeight())); // Ajusta el tamaño preferido

        JScrollPane rightScrollPane = new JScrollPane(rightPanel);
        rightScrollPane.setPreferredSize(new Dimension(270, getHeight())); // Ajusta el tamaño preferido del JScrollPane


        JPanel tablesPanel = new JPanel(new GridLayout(3, 1, 5, 5));

        // Panel para tabla original
        JPanel originalTablePanel = new JPanel(new BorderLayout());
        JLabel originalLabel = new JLabel("Puntos Originales", SwingConstants.CENTER);
        originalLabel.setFont(new Font("Arial", Font.BOLD, 12));
        originalTablePanel.add(originalLabel, BorderLayout.NORTH);
        JScrollPane originalScrollPane = new JScrollPane(originalTable);
        originalScrollPane.setPreferredSize(new Dimension(300, 150));
        originalTablePanel.add(originalScrollPane, BorderLayout.CENTER);

        // Panel para primera rotación
        JPanel firstRotationPanel = new JPanel(new BorderLayout());
        rotationTable1Label = new JLabel("Primera rotación: "+"R" +
                ejeRotadoLabel+"(" +0 + "°)", SwingConstants.CENTER);
        rotationTable1Label.setFont(new Font("Arial", Font.BOLD, 12));
        firstRotationPanel.add(rotationTable1Label, BorderLayout.NORTH);
        JScrollPane firstRotationScrollPane = new JScrollPane(firstRotationTable);
        firstRotationScrollPane.setPreferredSize(new Dimension(300, 150));
        firstRotationPanel.add(firstRotationScrollPane, BorderLayout.CENTER);

        // Panel para segunda rotación
        JPanel secondRotationPanel = new JPanel(new BorderLayout());
        rotationTable2Label = new JLabel("Segunda rotación: "+"R" +
                ejeRotadoLabel2+"(" +0 + "°)", SwingConstants.CENTER);
        rotationTable2Label.setFont(new Font("Arial", Font.BOLD, 12));
        secondRotationPanel.add(rotationTable2Label, BorderLayout.NORTH);
        JScrollPane secondRotationScrollPane = new JScrollPane(secondRotationTable);
        secondRotationScrollPane.setPreferredSize(new Dimension(300, 150));
        secondRotationPanel.add(secondRotationScrollPane, BorderLayout.CENTER);

        tablesPanel.add(originalTablePanel);
        tablesPanel.add(firstRotationPanel);
        tablesPanel.add(secondRotationPanel);

        rightPanel.add(tablesPanel, BorderLayout.CENTER);

        JPanel controlPanel = new JPanel(new GridLayout(13, 2, 5, 5));
        controlPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

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
        controlPanel.add(new JLabel("R1(θ1):"));
        controlPanel.add(primerAnguloField);
        controlPanel.add(rotacionesComboBox);
        controlPanel.add(primerRotacionButton);
        controlPanel.add(new JSeparator());
        controlPanel.add(new JSeparator());
        controlPanel.add(new JLabel("R2(θ2):"));
        controlPanel.add(segundoAnguloField);
        controlPanel.add(rotacionesComboBox2);
        controlPanel.add(segundaRotacionButton);


        rightPanel.add(controlPanel, BorderLayout.NORTH);
        add(rightScrollPane, BorderLayout.EAST);
    }

    private void addActionListeners() {
        backButton.addActionListener(e -> {
            new TransformacionesCompuestas().setVisible(true);
            clearPlanoAndData();
            dispose();
        });
        formulaButton.addActionListener(e -> {
            new FormulaRotacionSuc().setVisible(true);
        });

        primerRotacionButton.addActionListener(e -> realizarPrimeraRotacion());
        segundaRotacionButton.addActionListener(e -> realizarSegundaRotacion());

        regenerarFigura.addActionListener(e -> {
            primeraRotacionCompletada = false;
            segundaRotacionButton.setEnabled(false);
            clearPlanoAndData();
            int xInicio = Integer.parseInt(xInicialField.getText());
            int yInicio = Integer.parseInt(yInicialField.getText());
            int ZInicio = Integer.parseInt(ZInicialField.getText());

            drawFiguraOriginal(xInicio, yInicio, ZInicio);
        });


    }


    private void realizarPrimeraRotacion() {
        rotatedTableModel2.setRowCount(0);
        try {
            double angulo = Math.toRadians(Double.parseDouble(primerAnguloField.getText()));
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

            primeraRotacionList = new ArrayList<>();

            // Rotación según el eje seleccionado
            for (int i = 0; i < puntosList.size(); i++) {
                Punto puntoOriginal = puntosList.get(i);
                Punto puntoRotado;

                switch (ejeRotacion) {
                    case "sobre el eje x":
                        ejeRotadoLabel = "x";
                        double yX = puntoOriginal.getY();
                        double zX = puntoOriginal.getZ();
                        puntoRotado = new Punto(
                                puntoOriginal.getX(),
                                (yX * Math.cos(angulo)) - (zX * Math.sin(angulo)),
                                (yX * Math.sin(angulo)) + (zX * Math.cos(angulo))
                        );
                        break;

                    case "sobre el eje y":
                        ejeRotadoLabel= "y";
                        double xY = puntoOriginal.getX();
                        double zY = puntoOriginal.getZ();
                        puntoRotado = new Punto(
                                xY * Math.cos(angulo) + zY * Math.sin(angulo),
                                puntoOriginal.getY(),
                                -xY * Math.sin(angulo) + zY * Math.cos(angulo)
                        );
                        break;

                    case "sobre el eje z":
                        ejeRotadoLabel = "z";
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

                primeraRotacionList.add(puntoRotado);
            }

            // Asignar nombres a los puntos rotados usando el mismo patrón de referencias
            int[] referencias = {1, 4, 5, 8, 7, 2, 3, 6};
            for (int i = 0; i < primeraRotacionList.size(); i++) {
                if (i < 8) {
                    primeraRotacionList.get(i).setNombrePunto("P" + (i + 1) + "'");
                } else {
                    primeraRotacionList.get(i).setNombrePunto("P" + referencias[i - 8] + "'");
                }
            }

            // Dibujar figura rotada
            Punto puntoAnterior = primeraRotacionList.get(0);
            planoCartesiano.addPunto(puntoAnterior);

            for (int i = 1; i < primeraRotacionList.size(); i++) {
                Punto punto = primeraRotacionList.get(i);
                planoCartesiano.addPunto(punto);
                Linea linea = new Linea(puntoAnterior, punto, true, i);
                planoCartesiano.addLinea(linea);
                puntoAnterior = punto;
            }

            updateRotatedTable(primeraRotacionList);
            updateTitleLabePrimera();
            planoCartesiano.repaint();

            // Update label with angle and axis
            Component parent = firstRotationTable.getParent().getParent().getParent();
            if (parent instanceof JPanel) {
                ((JLabel) ((JPanel) parent).getComponent(0)).setText
                        ("Primera rotación: "+"R" +
                                ejeRotadoLabel+"(" +primerAnguloField.getText() + "°)");
            }

            segundaRotacionButton.setEnabled(true);

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese un valor numérico válido para el ángulo.");
        }
    }


    private void realizarSegundaRotacion() {
        try {
            double angulo = Math.toRadians(Double.parseDouble(segundoAnguloField.getText()));
            String ejeRotacion = (String) rotacionesComboBox2.getSelectedItem();

            if (primeraRotacionList == null || primeraRotacionList.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Primero debe generar la primera rotación");
                return;
            }

            planoCartesiano.clear();
            limpiar();
            // Redibujar la figura de la primera rotación
            Punto puntoAnteriorOriginal = primeraRotacionList.get(0);
            planoCartesiano.addPunto(puntoAnteriorOriginal);

            for (int i = 1; i < primeraRotacionList.size(); i++) {
                Punto puntoOriginal = primeraRotacionList.get(i);
                planoCartesiano.addPunto(puntoOriginal);
                planoCartesiano.addLinea(new Linea(puntoAnteriorOriginal, puntoOriginal, true, i));
                puntoAnteriorOriginal = puntoOriginal;
            }

            segundaRotacionList = new ArrayList<>();

            // Rotación según el eje seleccionado
            for (int i = 0; i < primeraRotacionList.size(); i++) {
                Punto puntoOriginal = primeraRotacionList.get(i);
                Punto puntoRotado;

                switch (ejeRotacion) {
                    case "sobre el eje x":
                        ejeRotadoLabel2 = "x";
                        double yX = puntoOriginal.getY();
                        double zX = puntoOriginal.getZ();
                        puntoRotado = new Punto(
                                puntoOriginal.getX(),
                                (yX * Math.cos(angulo)) - (zX * Math.sin(angulo)),
                                (yX * Math.sin(angulo)) + (zX * Math.cos(angulo))
                        );
                        break;

                    case "sobre el eje y":
                        ejeRotadoLabel2 = "y";
                        double xY = puntoOriginal.getX();
                        double zY = puntoOriginal.getZ();
                        puntoRotado = new Punto(
                                xY * Math.cos(angulo) + zY * Math.sin(angulo),
                                puntoOriginal.getY(),
                                -xY * Math.sin(angulo) + zY * Math.cos(angulo)
                        );
                        break;

                    case "sobre el eje z":
                        ejeRotadoLabel2 = "z";
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

                segundaRotacionList.add(puntoRotado);
            }

            // Asignar nombres a los puntos rotados usando el mismo patrón de referencias
            int[] referencias = {1, 4, 5, 8, 7, 2, 3, 6};
            for (int i = 0; i < segundaRotacionList.size(); i++) {
                if (i < 8) {
                    segundaRotacionList.get(i).setNombrePunto("P" + (i + 1) + "''");
                } else {
                    segundaRotacionList.get(i).setNombrePunto("P" + referencias[i - 8] + "''");
                }
            }

            // Dibujar figura rotada
            Punto puntoAnterior = segundaRotacionList.get(0);
            planoCartesiano.addPunto(puntoAnterior);

            for (int i = 1; i < segundaRotacionList.size(); i++) {
                Punto punto = segundaRotacionList.get(i);
                planoCartesiano.addPunto(punto);
                Linea linea = new Linea(puntoAnterior, punto, true, i);
                planoCartesiano.addLinea(linea);
                puntoAnterior = punto;
            }

            updateRotatedTable2(segundaRotacionList);
            updateTitleLabelSegunda();
            planoCartesiano.repaint();

            // Update label with angle and axis
            Component parent = secondRotationTable.getParent().getParent().getParent();
            if (parent instanceof JPanel) {
                ((JLabel) ((JPanel) parent).getComponent(0)).setText
                        ("Segunda rotación: "+"R" +
                                ejeRotadoLabel2+"(" +segundoAnguloField.getText() + "°)");
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese un valor numérico válido para el ángulo.");
        }
    }




    public void drawFiguraOriginal(double xInicio, double yInicio, double zInicio) {

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
            titleLabel3.setText("Rotación 3D " + (String) rotacionesComboBox.getSelectedItem() + ": R" + ejeRotadoLabel + "(" + 0 + "°) ");
            titleLabel4.setText("Rotación 3D " + (String) rotacionesComboBox2.getSelectedItem() + ": R" + ejeRotadoLabel2 + "(" + 0 + "°) ");
            planoCartesiano.repaint();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese valores numéricos válidos.");
        }
    }

    private void updateTitleLabePrimera() {
        String angulo = primerAnguloField.getText();
        String ejeRotacion = (String) rotacionesComboBox.getSelectedItem();
        titleLabel3.setText("Rotación 3D " + ejeRotacion + ": R" + ejeRotadoLabel + "(" + angulo + "°) ");
        titleLabel4.setText("Rotación 3D " + (String) rotacionesComboBox2.getSelectedItem() + ": R" + ejeRotadoLabel2 + "(" + 0 + "°) ");

    }

    private void updateTitleLabelSegunda() {
        String angulo = primerAnguloField.getText();
        String ejeRotacion = (String) rotacionesComboBox.getSelectedItem();
        String angulo2 = segundoAnguloField.getText();
        String ejeRotacion2 = (String) rotacionesComboBox2.getSelectedItem();

        titleLabel3.setText("Rotación 3D " + ejeRotacion + ": R" + ejeRotadoLabel + "(" + angulo + "°) ");
        titleLabel4.setText("Rotación 3D " + ejeRotacion2 + ": R" + ejeRotadoLabel2 + "(" + angulo2 + "°) ");
    }

    private void clearPlanoAndData() {
        planoCartesiano.clear();
        originalTableModel.setRowCount(0);
        rotatedTableModel.setRowCount(0);
        rotatedTableModel2.setRowCount(0);
    }

    private void updateOriginalTable(List<Punto> puntos) {
        originalTableModel.setRowCount(0);
        boolean[] puntosConCodigo = {false, true, true, true, true, true, true, true, true, true, false, true, false, true, false, true};
        int i = 0;

        for (Punto punto : puntos) {
            int cod = puntosConCodigo[i] ? 1 : 0;
            originalTableModel.addRow(new Object[]{
                    punto.getNombrePunto(),
                    punto.getX(),
                    punto.getY(),
                    punto.getZ(),
                    cod
            });
            i++;
        }
    }

    private void updateRotatedTable(List<Punto> puntos) {
        rotatedTableModel.setRowCount(0);
        boolean[] puntosConCodigo = {false, true, true, true, true, true, true, true, true, true, false, true, false, true, false, true};
        int i = 0;
        for (Punto punto : puntos) {
            int cod = puntosConCodigo[i] ? 1 : 0;
            rotatedTableModel.addRow(new Object[]{
                    punto.getNombrePunto(),
                    String.format("%.2f", punto.getX()),
                    String.format("%.2f", punto.getY()),
                    String.format("%.2f", punto.getZ()),
                    cod
            });
            i++;
        }
        rotationTable1Label.setText(String.format("Primera rotación: %s°", primerAnguloField.getText()));    }

    private void updateRotatedTable2(List<Punto> puntos) {
        rotatedTableModel2.setRowCount(0);
        boolean[] puntosConCodigo = {false, true, true, true, true, true, true, true, true, true, false, true, false, true, false, true};
        int i = 0;
        for (Punto punto : puntos) {
            int cod = puntosConCodigo[i] ? 1 : 0;
            rotatedTableModel2.addRow(new Object[]{
                    punto.getNombrePunto(),
                    String.format("%.2f", punto.getX()),
                    String.format("%.2f", punto.getY()),
                    String.format("%.2f", punto.getZ()),
                    cod
            });
            i++;
        }
        rotationTable2Label.setText(String.format("Segunda rotación: %s°", segundoAnguloField.getText()));
    }
    private void limpiar() {
        int xInicio = Integer.parseInt(xInicialField.getText());
        int yInicio = Integer.parseInt(yInicialField.getText());
        int zInicio = Integer.parseInt(ZInicialField.getText());
        drawFiguraOriginal(xInicio, yInicio, zInicio);
    }

    private void clearTableAll(int index) {
        if(index == 1){
            rotationTable1Label.setText("Primera rotación: 0°");
            rotationTable2Label.setText("Segunda rotación: 0°");


        } else if (index == 2){
            rotationTable2Label.setText("Segunda rotación: 0°");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PolilineasRotacionSuc());
    }
}