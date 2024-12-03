package DrawingClasses.Transformaciones.Basicas;

import PaginaPrincipalFolder.Transformaciones.Componentes.TransformacionesBasicas;
import PaginaPrincipalFolder.Transformaciones.PaginasImport.FormulasTBasicas.FormulaEscalacion;
import PaginaPrincipalFolder.Transformaciones.PaginasImport.FormulasTBasicas.FormulaTraslacion;
import Plano.Transformaciones.Basicas.PlanoCartesianoEscalacion;
import formasADibujar.Linea;
import formasADibujar.Punto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PolilineasEscalacion extends JFrame {
    private PlanoCartesianoEscalacion planoCartesiano;
    private JTable originalTable;
    private JTable scaledTable;
    private DefaultTableModel originalTableModel;
    private DefaultTableModel scaledTableModel;
    private JButton backButton, formulaButton;
    private JTextField xInicialField, yInicialField, ZInicialField;
    public JTextField sxField, syField, szField;
    private JLabel sxLabel;
    private JLabel syLabel;
    public JComboBox<String> aumentoComboBox;
    private JButton regenerarFigura;
    private JButton escalarButton;
    private List<Punto> puntosList;
    private List<Punto> puntosEscaladosList;
    public int sx = 2;
    public int sy = 2;

    public PolilineasEscalacion() {
        setTitle("Transformaciones Geométricas 3D Básica: Escalación");
        setSize(1800, 960);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        createComponents();
        configureLayout();
        addActionListeners();
        setVisible(true);
    }

    private void createComponents() {
        planoCartesiano = new PlanoCartesianoEscalacion();
        planoCartesiano.setPreferredSize(new Dimension(600, 400));

        xInicialField = new JTextField("2", 5);
        yInicialField = new JTextField("0", 5);
        ZInicialField = new JTextField("1", 5);


        sxField = new JTextField("2", 5);
        syField = new JTextField("2", 5);
        szField = new JTextField("2", 5);

        backButton = new JButton("Menu");
        formulaButton = new JButton("Formula");
        regenerarFigura = new JButton("Graficar");
        escalarButton = new JButton("Escalar");


        String[] columnNames = {"Punto", "X", "Y", "Z"};
        String[] columnNamesEdi = {"P'", "X'", "Y'", "Z'"};
        originalTableModel = new DefaultTableModel(columnNames, 0);
        scaledTableModel = new DefaultTableModel(columnNamesEdi, 0);

        originalTable = new JTable(originalTableModel);
        scaledTable = new JTable(scaledTableModel);
    }

    private void configureLayout() {
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new BorderLayout());
        JLabel titleLabel1 = new JLabel("Transformaciones Geométricas 3D Básica:", SwingConstants.CENTER);
        titleLabel1.setFont(new Font("Arial", Font.BOLD, 20));

        JLabel titleLabel2 = new JLabel("Escalación", SwingConstants.CENTER);
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
        rightPanel.setPreferredSize(new Dimension(250, getHeight())); // Ajusta el tamaño preferido

        JScrollPane rightScrollPane = new JScrollPane(rightPanel);
        rightScrollPane.setPreferredSize(new Dimension(250, getHeight())); // Ajusta el tamaño preferido del JScrollPane
        rightScrollPane.setBorder(BorderFactory.createEmptyBorder());


        JPanel tablesPanel = new JPanel(new GridLayout(3, 1, 5, 5));

        JPanel originalTablePanel = new JPanel(new BorderLayout());
        JLabel originalLabel = new JLabel("Puntos Originales", SwingConstants.CENTER);
        originalLabel.setFont(new Font("Arial", Font.BOLD, 12)); // Set font to Arial, bold, size 18
        originalTablePanel.add(originalLabel, BorderLayout.NORTH);


        JScrollPane originalScrollPane = new JScrollPane(originalTable);
        originalScrollPane.setPreferredSize(new Dimension(300, 150));
        originalTablePanel.add(originalScrollPane, BorderLayout.CENTER);

        JPanel scaledTablePanel = new JPanel(new BorderLayout());
        JLabel scaledLabel = new JLabel("Puntos Escalados (Sx: 0, Sy: 0, Sz: 0)", SwingConstants.CENTER);

        scaledLabel.setFont(new Font("Arial", Font.BOLD, 12)); // Set font to Arial, bold, size 18
        scaledTablePanel.add(scaledLabel, BorderLayout.NORTH);

        JScrollPane scaledScrollPane = new JScrollPane(scaledTable);
        scaledScrollPane.setPreferredSize(new Dimension(300, 150));
        scaledTablePanel.add(scaledScrollPane, BorderLayout.CENTER);

        tablesPanel.add(originalTablePanel);
        tablesPanel.add(scaledTablePanel);

        rightPanel.add(tablesPanel, BorderLayout.CENTER);

        JPanel controlPanel = new JPanel(new GridLayout(10, 2, 5, 5));
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

        controlPanel.add(new JLabel("Sx:"));
        controlPanel.add(sxField);
        controlPanel.add(new JLabel("Sy:"));
        controlPanel.add(syField);
        controlPanel.add(new JLabel("Sz:"));
        controlPanel.add(szField);
        controlPanel.add(new JLabel(""));
        controlPanel.add(escalarButton);


        rightPanel.add(controlPanel, BorderLayout.NORTH);
        add(rightScrollPane, BorderLayout.EAST);
    }

    private void addActionListeners() {
        backButton.addActionListener(e -> {
            clearPlanoAndData();
            new TransformacionesBasicas().setVisible(true);
            dispose();
        });
        formulaButton.addActionListener(e -> {
            new FormulaEscalacion().setVisible(true);

        });

        regenerarFigura.addActionListener(e -> {
            int xInicio = Integer.parseInt(xInicialField.getText());
            int yInicio = Integer.parseInt(yInicialField.getText());
            int zInicio = Integer.parseInt(ZInicialField.getText());
            drawFiguraOriginal(xInicio, yInicio, zInicio);
        });

        escalarButton.addActionListener(e -> realizarEscalacion());
    }

    public void drawFiguraOriginal(int xInicio, int yInicio, int zInicio) {
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
            updateLabels("0", "0", "0");

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese valores numéricos válidos.");
        }
    }

    private void realizarEscalacion() {

        if (puntosList == null || puntosList.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Primero debe generar la figura original");
            return;
        }

        planoCartesiano.clear();
        int xInicio = Integer.parseInt(xInicialField.getText());
        int yInicio = Integer.parseInt(yInicialField.getText());
        int zInicio = Integer.parseInt(ZInicialField.getText());
        drawFiguraOriginal(xInicio, yInicio, zInicio);

        try {
            int sx = Integer.parseInt(sxField.getText());
            int sy = Integer.parseInt(syField.getText());
            int sz = Integer.parseInt(szField.getText());


            // Crear y dibujar los puntos trasladados
            Punto[] puntosEscaladosArray = new Punto[puntosList.size()];

            // Crear puntos trasladados con las nuevas coordenadas
            for (int i = 0; i < 8; i++) {
                Punto puntoOriginal = puntosList.get(i);
                Punto puntoEscalado = new Punto(
                        puntoOriginal.getX() * sx,  // Mover en X
                        puntoOriginal.getY() * sy,  // Mover en Y
                        puntoOriginal.getZ() * sz   // Mover en Z
                );

                // Asignar nombres a los puntos originales
                puntoEscalado.setNombrePunto("P" + (i + 1) + "'");
                puntosEscaladosArray[i] = puntoEscalado;
            }

            // Asignar nombres a los puntos repetidos basándose en su referencia
            int[] referencias = {1, 4, 5, 8, 7, 2, 3, 6}; // Secuencia específica
            for (int i = 8; i < puntosList.size(); i++) {
                Punto puntoOriginal = puntosList.get(i);
                Punto puntoEscalado = new Punto(
                        puntoOriginal.getX() * sx,
                        puntoOriginal.getY() * sy,
                        puntoOriginal.getZ() * sz
                );
                puntoEscalado.setNombrePunto("P" + referencias[i - 8] + "'");
                puntosEscaladosArray[i] = puntoEscalado;
            }

            // Convertir a lista
            puntosEscaladosList = Arrays.asList(puntosEscaladosArray);

            // Dibujar la figura trasladada
            Punto puntoInicio = puntosEscaladosArray[0];
            planoCartesiano.addPunto(puntoInicio);

            Punto puntoAnterior = puntoInicio;
            for (int i = 1; i < puntosEscaladosArray.length; i++) {
                Punto punto = puntosEscaladosArray[i];
                planoCartesiano.addPunto(punto);
                planoCartesiano.addLinea(new Linea(puntoAnterior, punto, true, i));
                puntoAnterior = punto;
            }

            // Actualiza la tabla de puntos trasladados
            updateScaledTable(puntosEscaladosList);
            planoCartesiano.repaint();

            updateLabels(sxField.getText(), syField.getText(), szField.getText());

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese valores numéricos válidos para Tx, Ty y Tz");
        }
    }


    private void updateLabels(String sx, String sy, String sz) {
        // Actualizar la etiqueta de la tabla escalada
        Component parent = scaledTable.getParent().getParent().getParent();
        if (parent instanceof JPanel) {
            ((JLabel) ((JPanel) parent).getComponent(0)).setText("Puntos Escalados " +
                    "(Sx: "+sx+
                    ", Sy: "+sy+
                    ", Sz: "+sz+")");
        }
    }

    private void updateOriginalTable(List<Punto> puntos) {
        originalTableModel.setRowCount(0);
        for (Punto punto : puntos) {
            originalTableModel.addRow(new Object[]{punto.getNombrePunto(), punto.getX(), punto.getY(), punto.getZ()});
        }
    }

    private void updateScaledTable(List<Punto> puntosEscalados) {
        scaledTableModel.setRowCount(0);
        int[] referencias = {1, 4, 5, 8, 7, 2, 3, 6}; // Orden específico

        for (int i = 0; i < puntosEscalados.size(); i++) {
            Punto puntoEscalado = puntosEscalados.get(i);
            if (i < 8) {
                scaledTableModel.addRow(new Object[]{"P" + referencias[i] + "'", puntoEscalado.getX(), puntoEscalado.getY(), puntoEscalado.getZ()});
            } else {
                scaledTableModel.addRow(new Object[]{"P" + referencias[i - 8] + "'", puntoEscalado.getX(), puntoEscalado.getY(), puntoEscalado.getZ()});
            }
        }
    }

    private void clearPlanoAndData() {
        planoCartesiano.clear();
        originalTableModel.setRowCount(0);
        scaledTableModel.setRowCount(0);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(PolilineasEscalacion::new);
    }
}
