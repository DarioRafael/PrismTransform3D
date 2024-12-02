package DrawingClasses.Transformaciones.Basicas;

import PaginaPrincipalFolder.Transformaciones.Componentes.TransformacionesBasicas;
import PaginaPrincipalFolder.Transformaciones.PaginasImport.FormulasTBasicas.FormulaTraslacion;
import Plano.Transformaciones.Basicas.PlanoCartesianoTraslacion;
import formasADibujar.Linea;
import formasADibujar.Punto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PolilineasTraslacion extends JFrame {
    private PlanoCartesianoTraslacion planoCartesiano;
    private JTable originalTable;
    private JTable translatedTable;
    private DefaultTableModel originalTableModel;
    private DefaultTableModel translatedTableModel;
    private JButton backButton, formulaButton;
    private JTextField xInicialField, yInicialField, ZInicialField;
    public JTextField txField, tyField, tzField;

    private JButton regenerarFigura;
    private JButton trasladarButton;
    private List<Punto> puntosList;
    private List<Punto> puntosTrasladadosList;
    private int figuraCounter = 1;
    public int tx = 0;
    public int ty = 0;
    private JLabel translatedTableLabel;

    public PolilineasTraslacion() {
        setTitle("Transformaciones Geométricas 3D Básica: Traslación");
        setSize(1650, 960);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        createComponents();
        configureLayout();
        addActionListeners();
        setVisible(true);
    }

    private void createComponents() {
        planoCartesiano = new PlanoCartesianoTraslacion();
        planoCartesiano.setPreferredSize(new Dimension(600, 400));

        xInicialField = new JTextField("2", 5);
        yInicialField = new JTextField("0", 5);
        ZInicialField = new JTextField("1", 5);

        txField = new JTextField("0", 5);
        tyField = new JTextField("0", 5);
        tzField = new JTextField("0", 5);

        backButton = new JButton("Menu");
        formulaButton = new JButton("Formula");
        regenerarFigura = new JButton("Graficar");
        trasladarButton = new JButton("Trasladar");


        String[] columnNames = {"Punto", "X", "Y", "Z"};
        String[] columnNamesEdi = {"P'", "X'", "Y'", "Z'"};
        originalTableModel = new DefaultTableModel(columnNames, 0);
        translatedTableModel = new DefaultTableModel(columnNamesEdi, 0);

        originalTable = new JTable(originalTableModel);
        translatedTable = new JTable(translatedTableModel);

    }

    private void configureLayout() {
        setLayout(new BorderLayout());

        // Top Panel - Similar structure to original
        JPanel topPanel = new JPanel(new BorderLayout());
        JLabel titleLabel1 = new JLabel("Transformaciones Geométricas 3D Básica:", SwingConstants.CENTER);
        titleLabel1.setFont(new Font("Arial", Font.BOLD, 20));

        JLabel titleLabel2 = new JLabel("Traslación", SwingConstants.CENTER);
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

        // Right Panel with tables and controls
        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.setPreferredSize(new Dimension(250, getHeight()));

        JScrollPane rightScrollPane = new JScrollPane(rightPanel);
        rightScrollPane.setPreferredSize(new Dimension(250, getHeight()));
        rightScrollPane.setBorder(BorderFactory.createEmptyBorder());

        // Tables Panel
        JPanel tablesPanel = new JPanel(new GridLayout(3, 1, 5, 5));

        // Original Table Panel
        JPanel originalTablePanel = new JPanel(new BorderLayout());
        originalTablePanel.setBorder(BorderFactory.createEmptyBorder());

        JLabel originalLabel = new JLabel("Puntos Originales", SwingConstants.CENTER);
        originalLabel.setFont(new Font("Arial", Font.BOLD, 12));
        originalTablePanel.add(originalLabel, BorderLayout.NORTH);

        JScrollPane originalScrollPane = new JScrollPane(originalTable);
        originalScrollPane.setPreferredSize(new Dimension(300, 150));
        originalTablePanel.add(originalScrollPane, BorderLayout.CENTER);

        // Translated Table Panel
        JPanel translatedTablePanel = new JPanel(new BorderLayout());
        translatedTablePanel.setBorder(BorderFactory.createEmptyBorder());

        translatedTableLabel = new JLabel("Puntos Trasladados (Tx: 0, Ty: 0)", SwingConstants.CENTER);
        translatedTablePanel.add(translatedTableLabel, BorderLayout.NORTH);

        JScrollPane translatedScrollPane = new JScrollPane(translatedTable);
        translatedScrollPane.setPreferredSize(new Dimension(300, 150));
        translatedTablePanel.add(translatedScrollPane, BorderLayout.CENTER);

        tablesPanel.add(originalTablePanel);
        tablesPanel.add(translatedTablePanel);

        rightPanel.add(tablesPanel, BorderLayout.CENTER);

        // Control Panel
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

        // Separator
        controlPanel.add(new JSeparator());
        controlPanel.add(new JSeparator());

        // Translation controls
        controlPanel.add(new JLabel("Tx:"));
        controlPanel.add(txField);
        controlPanel.add(new JLabel("Ty:"));
        controlPanel.add(tyField);
        controlPanel.add(new JLabel("Tz:"));
        controlPanel.add(tzField);
        controlPanel.add(new JLabel(""));
        controlPanel.add(trasladarButton);


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
            new FormulaTraslacion().setVisible(true);

        });



        regenerarFigura.addActionListener(e -> {
            int xInicio = Integer.parseInt(xInicialField.getText());
            int yInicio = Integer.parseInt(yInicialField.getText());
            int zInicio = Integer.parseInt(ZInicialField.getText());
            drawFiguraOriginal(xInicio, yInicio, zInicio);
        });

        trasladarButton.addActionListener(e -> realizarTraslacion());
    }

    public void drawFiguraOriginal(int xInicio, int yInicio, int zInicio) {
        clearPlanoAndData();

        try {
            Punto puntoInicio = new Punto(xInicio, yInicio, zInicio);

            // Define los puntos de la figura
            Punto[] puntosArray = {
                    new Punto(xInicio, yInicio, zInicio),
                    new Punto(xInicio+4, yInicio, zInicio+1),
                    new Punto(xInicio+4, yInicio, zInicio-1),
                    new Punto(xInicio, yInicio, zInicio-2),
                    new Punto(xInicio, yInicio+1, zInicio-2),
                    new Punto(xInicio+3, yInicio, zInicio-2),
                    new Punto(xInicio+3, yInicio, zInicio),
                    new Punto(xInicio-1, yInicio, zInicio-1),
            };

            puntosList = Arrays.asList(puntosArray);

            // Asignar nombres a los puntos
            for (int i = 0; i < puntosList.size(); i++) {
                puntosList.get(i).setNombrePunto("P" + (i + 1));
            }

            // Dibuja la figura
            Punto puntoAnterior = puntoInicio;
            planoCartesiano.addPunto(puntoInicio);

            for (int i = 0; i < puntosList.size(); i++) {
                Punto punto = puntosList.get(i);
                planoCartesiano.addPunto(punto);
                planoCartesiano.addLinea(new Linea(puntoAnterior, punto, true, i + 1));
                puntoAnterior = punto;
            }

            // Actualiza la tabla de puntos originales
            updateOriginalTable(puntosList);

            planoCartesiano.repaint();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese valores numéricos válidos.");
        }
    }

    private void realizarTraslacion() {
        try {
            int tx = Integer.parseInt(txField.getText());
            int ty = Integer.parseInt(tyField.getText());
            int tz = Integer.parseInt(tzField.getText());

            if (puntosList == null || puntosList.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Primero debe generar la figura original");
                return;
            }

            // Limpiar el plano pero mantener los datos originales
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

            // Crear y dibujar los puntos trasladados
            puntosTrasladadosList = new ArrayList<>();

            // Primero mover en X (diagonal a la derecha)
            for (int i = 0; i < puntosList.size(); i++) {
                Punto puntoOriginal = puntosList.get(i);
                Punto puntoTransladado = new Punto(
                        puntoOriginal.getX() + tx,  // Mover en X
                        puntoOriginal.getY(),       // Mantener Y original
                        puntoOriginal.getZ()        // Mantener Z original
                );
                puntoTransladado.setNombrePunto("P" + (i + 1) + "'");
                puntosTrasladadosList.add(puntoTransladado);
            }

            // Luego mover en Z (profundidad)
            for (int i = 0; i < puntosTrasladadosList.size(); i++) {
                Punto puntoOriginal = puntosTrasladadosList.get(i);
                Punto puntoTransladado = new Punto(
                        puntoOriginal.getX(),       // Mantener X actual
                        puntoOriginal.getY(),       // Mantener Y actual
                        puntoOriginal.getZ() + tz   // Mover en Z
                );
                puntoTransladado.setNombrePunto("P" + (i + 1) + "'");
                puntosTrasladadosList.set(i, puntoTransladado);
            }

            // Finalmente mover en Y (subir o bajar)
            for (int i = 0; i < puntosTrasladadosList.size(); i++) {
                Punto puntoOriginal = puntosTrasladadosList.get(i);
                Punto puntoTransladado = new Punto(
                        puntoOriginal.getX(),       // Mantener X actual
                        puntoOriginal.getY() + ty,  // Mover en Y
                        puntoOriginal.getZ()        // Mantener Z actual
                );
                puntoTransladado.setNombrePunto("P" + (i + 1) + "'");
                puntosTrasladadosList.set(i, puntoTransladado);
            }

            // Dibuja la figura trasladada
            Punto puntoAnterior = puntosTrasladadosList.get(0);
            planoCartesiano.addPunto(puntoAnterior);

            for (int i = 1; i < puntosTrasladadosList.size(); i++) {
                Punto punto = puntosTrasladadosList.get(i);
                planoCartesiano.addPunto(punto);
                Linea linea = new Linea(puntoAnterior, punto, true, i);
                planoCartesiano.addLinea(linea);
                puntoAnterior = punto;
            }

            // Actualiza la tabla de puntos trasladados
            updateTranslatedTable(puntosTrasladadosList);
            planoCartesiano.repaint();

            // Actualizar la etiqueta de la tabla escalada
            Component parent = translatedTable.getParent().getParent().getParent();
            if (parent instanceof JPanel) {
                ((JLabel) ((JPanel) parent).getComponent(0)).setText("Puntos Trasladados: Tx: " + tx + " Ty: " + ty + " Tz: " + tz);
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese valores numéricos válidos para Tx, Ty y Tz");
        }
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

    private void updateTranslatedTable(List<Punto> puntos) {
        translatedTableModel.setRowCount(0);
        for (Punto punto : puntos) {
            translatedTableModel.addRow(new Object[]{
                    punto.getNombrePunto(),
                    punto.getX(),
                    punto.getY(),
                    punto.getZ()
            });
        }
    }


    private void clearPlanoAndData() {
        planoCartesiano.clear();
        originalTableModel.setRowCount(0);
        translatedTableModel.setRowCount(0);
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PolilineasTraslacion frame = new PolilineasTraslacion();
        });
    }
}