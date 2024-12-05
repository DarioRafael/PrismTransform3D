package DrawingClasses.Transformaciones.Compuestas;


import PaginaPrincipalFolder.Transformaciones.Componentes.TransformacionesCompuestas;
import PaginaPrincipalFolder.Transformaciones.PaginasImport.FormulasTBasicas.FormulaRotacion;
import PaginaPrincipalFolder.Transformaciones.PaginasImport.FormulasTBasicas.FormulaTraslacion;
import PaginaPrincipalFolder.Transformaciones.PaginasImport.FormulasTCompuestas.FormulaTraslacionSuc;
import Plano.Transformaciones.Basicas.PlanoCartesianoTraslacion;
import formasADibujar.Linea;
import formasADibujar.Punto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PolilineasTraslacionSuc extends JFrame {
    private PlanoCartesianoTraslacion planoCartesiano;
    private JTable originalTable;
    private JTable translatedTable1;
    private JTable translatedTable2;
    private DefaultTableModel originalTableModel;
    private DefaultTableModel translatedTableModel1;
    private DefaultTableModel translatedTableModel2;
    private JButton backButton, formulaButton;
    private JTextField xInicialField, yInicialField,ZInicialField;
    private JTextField tx1Field, ty1Field, tz1Field, tx2Field, ty2Field, tz2Field;

    public JComboBox<String> aumentoComboBox;
    private JButton regenerarFigura;
    private JButton trasladar1Button;
    private JButton trasladar2Button;
    private List<Punto> puntosList;
    private List<Punto> puntosTrasladadosList1;
    private List<Punto> puntosTrasladadosList2;
    private int figuraCounter = 1;
    public int tx1 = 0;
    public int ty1 = 0;
    public int tx2 = 0;
    public int ty2 = 0;
    public int tz2 = 0;
    private JLabel translatedTable1Label;
    private JLabel translatedTable2Label;
    private JLabel titleLabel3, titleLabel4;

    public PolilineasTraslacionSuc() {
        setTitle("Transformaciones Geométricas 3D Compuestas: Traslacion Sucesiva");
        setSize(1850, 960);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        createComponents();
        configureLayout();
        addActionListeners();
        setVisible(true);
    }


    //coment
    private void createComponents() {
        planoCartesiano = new PlanoCartesianoTraslacion();
        planoCartesiano.setPreferredSize(new Dimension(600, 400));

        xInicialField = new JTextField("2", 5);
        yInicialField = new JTextField("0", 5);
        ZInicialField = new JTextField("1", 5);

        tx1Field = new JTextField("-2", 5);
        ty1Field = new JTextField("-1", 5);
        tz1Field = new JTextField("1", 5);

        tx2Field = new JTextField("-3", 5);
        ty2Field = new JTextField("4", 5);
        tz2Field = new JTextField("0", 5);

        backButton = new JButton("Menu");
        formulaButton = new JButton("Formula");

        regenerarFigura = new JButton("Graficar");
        trasladar1Button = new JButton("Trasladar");
        trasladar2Button = new JButton("Trasladar");
        trasladar2Button.setEnabled(false);

        String[] aumentoOptions = {"x1", "x2", "x4", "x8", "x16"};
        aumentoComboBox = new JComboBox<>(aumentoOptions);
        aumentoComboBox.setSelectedIndex(0);

        String[] columnNames = {"Punto", "X", "Y", "Z", "Cod"};
        String[] columnNamesEdi = {"P'", "X'", "Y'", "Z'", "Cod"};
        String[] columnNamesEdi2 = {"P''", "X''", "Y''", "Z''","Cod"};
        originalTableModel = new DefaultTableModel(columnNames, 0);
        translatedTableModel1 = new DefaultTableModel(columnNamesEdi, 0);
        translatedTableModel2 = new DefaultTableModel(columnNamesEdi2, 0);

        originalTable = new JTable(originalTableModel);
        translatedTable1 = new JTable(translatedTableModel1);
        translatedTable2 = new JTable(translatedTableModel2);


    }

    private void configureLayout() {
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new BorderLayout());
        JLabel titleLabel1 = new JLabel("Transformaciones Geométricas 3D Compuestas:", SwingConstants.CENTER);
        titleLabel1.setFont(new Font("Arial", Font.BOLD, 20));

        JLabel titleLabel2 = new JLabel("Prisma cuadrangular", SwingConstants.CENTER);
        titleLabel2.setFont(new Font("Arial", Font.BOLD, 18));

        //            titleLabel3.setText("Traslación 3D: T(Tx: 0, Ty: 0, Tz: 0)");
        titleLabel3 = new JLabel("Traslación 3D: T1(Tx1: 0, Ty1: 0, Tz1: 0)", SwingConstants.CENTER);
        titleLabel3.setFont(new Font("Arial", Font.BOLD, 16));

        titleLabel4 = new JLabel("Traslación 3D: T2(Tx2: 0, Ty2: 2, Tz2: 0)", SwingConstants.CENTER);
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

        // Panel derecho con tablas y controles
        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.setPreferredSize(new Dimension(0, getHeight())); // Ajusta el tamaño preferido

        JScrollPane rightScrollPane = new JScrollPane(rightPanel);
        rightScrollPane.setPreferredSize(new Dimension(270, getHeight())); // Ajusta el tamaño preferido del JScrollPane


        // Panel para las tres tablas
        JPanel tablesPanel = new JPanel(new GridLayout(3, 1, 5, 5));

        // Configuración de las tres tablas
        JPanel originalTablePanel = new JPanel(new BorderLayout());
        originalTablePanel.add(new JLabel("Puntos Originales", SwingConstants.CENTER), BorderLayout.NORTH);
        JScrollPane originalScrollPane = new JScrollPane(originalTable);
        originalScrollPane.setPreferredSize(new Dimension(300, 150));
        originalTablePanel.add(originalScrollPane, BorderLayout.CENTER);

        JPanel translatedTable1Panel = new JPanel(new BorderLayout());
        translatedTable1Label = new JLabel("Primera Traslación (Tx1: 0, Ty1: 0, Tz1: 0)", SwingConstants.CENTER);
        translatedTable1Panel.add(translatedTable1Label, BorderLayout.NORTH);

        JScrollPane translatedScrollPane1 = new JScrollPane(translatedTable1);
        translatedScrollPane1.setPreferredSize(new Dimension(300, 150));
        translatedTable1Panel.add(translatedScrollPane1, BorderLayout.CENTER);

        JPanel translatedTable2Panel = new JPanel(new BorderLayout());
        translatedTable2Label = new JLabel("Segunda Traslación (Tx2: 0, Ty2: 0, Tz2: 0)", SwingConstants.CENTER);
        translatedTable2Panel.add(translatedTable2Label, BorderLayout.NORTH);
        JScrollPane translatedScrollPane2 = new JScrollPane(translatedTable2);
        translatedScrollPane2.setPreferredSize(new Dimension(300, 150));
        translatedTable2Panel.add(translatedScrollPane2, BorderLayout.CENTER);

        tablesPanel.add(originalTablePanel);
        tablesPanel.add(translatedTable1Panel);
        tablesPanel.add(translatedTable2Panel);

        rightPanel.add(tablesPanel, BorderLayout.CENTER);

        // Panel de control con campos de entrada y botones
        JPanel controlPanel = new JPanel(new GridLayout(14, 2, 5, 5));
        controlPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Configuración inicial
        controlPanel.add(new JLabel("X inicial:"));
        controlPanel.add(xInicialField);
        controlPanel.add(new JLabel("Y inicial:"));
        controlPanel.add(yInicialField);
        controlPanel.add(new JLabel("Z inicial:"));
        controlPanel.add(ZInicialField);
        controlPanel.add(new JLabel(""));
        controlPanel.add(regenerarFigura);

        // Separador
        controlPanel.add(new JSeparator());
        controlPanel.add(new JSeparator());

        // Primera traslación
        controlPanel.add(new JLabel("Tx1:"));
        controlPanel.add(tx1Field);
        controlPanel.add(new JLabel("Ty1:"));
        controlPanel.add(ty1Field);
        controlPanel.add(new JLabel("Tz1:"));
        controlPanel.add(tz1Field);
        controlPanel.add(new JLabel(""));
        controlPanel.add(trasladar1Button);

        // Segunda traslación
        controlPanel.add(new JLabel("Tx2:"));
        controlPanel.add(tx2Field);
        controlPanel.add(new JLabel("Ty2:"));
        controlPanel.add(ty2Field);
        controlPanel.add(new JLabel("Tz2:"));
        controlPanel.add(tz2Field);
        controlPanel.add(new JLabel(""));
        controlPanel.add(trasladar2Button);


        rightPanel.add(controlPanel, BorderLayout.NORTH);


        add(rightScrollPane, BorderLayout.EAST);
    }
    private void addActionListeners() {
        backButton.addActionListener(e -> {
            clearPlanoAndData();
            new TransformacionesCompuestas().setVisible(true);
            dispose();
        });
        formulaButton.addActionListener(e -> {
            new FormulaTraslacionSuc().setVisible(true);
        });

        regenerarFigura.addActionListener(e -> {
            int xInicio = Integer.parseInt(xInicialField.getText());
            int yInicio = Integer.parseInt(yInicialField.getText());
            int zInicio = Integer.parseInt(ZInicialField.getText());
            drawFiguraOriginal(xInicio, yInicio, zInicio);
            trasladar2Button.setEnabled(false);
        });

        trasladar1Button.addActionListener(e -> {
            realizarPrimeraTraslacion();
            trasladar2Button.setEnabled(true);
        });

        trasladar2Button.addActionListener(e -> realizarSegundaTraslacion());
    }

    private void realizarPrimeraTraslacion() {

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
            int tx = Integer.parseInt(tx1Field.getText());
            int ty = Integer.parseInt(ty1Field.getText());
            int tz = Integer.parseInt(tz1Field.getText());


            // Crear y dibujar los puntos trasladados
            Punto[] puntosTrasladadosArray = new Punto[puntosList.size()];

            // Crear puntos trasladados con las nuevas coordenadas
            for (int i = 0; i < 8; i++) {
                Punto puntoOriginal = puntosList.get(i);
                Punto puntoTrasladado = new Punto(
                        puntoOriginal.getX() + tx,  // Mover en X
                        puntoOriginal.getY() + ty,  // Mover en Y
                        puntoOriginal.getZ() + tz   // Mover en Z
                );

                // Asignar nombres a los puntos originales
                puntoTrasladado.setNombrePunto("P" + (i + 1) + "'");
                puntosTrasladadosArray[i] = puntoTrasladado;
            }

            // Asignar nombres a los puntos repetidos basándose en su referencia
            int[] referencias = {1, 4, 5, 8, 7, 2, 3, 6}; // Secuencia específica
            for (int i = 8; i < puntosList.size(); i++) {
                Punto puntoOriginal = puntosList.get(i);
                Punto puntoTrasladado = new Punto(
                        puntoOriginal.getX() + tx,
                        puntoOriginal.getY() + ty,
                        puntoOriginal.getZ() + tz
                );
                puntoTrasladado.setNombrePunto("P" + referencias[i - 8] + "'");
                puntosTrasladadosArray[i] = puntoTrasladado;
            }

            // Convertir a lista
            puntosTrasladadosList1 = Arrays.asList(puntosTrasladadosArray);

            // Dibujar la figura trasladada
            Punto puntoInicio = puntosTrasladadosArray[0];
            planoCartesiano.addPunto(puntoInicio);

            Punto puntoAnterior = puntoInicio;
            for (int i = 1; i < puntosTrasladadosArray.length; i++) {
                Punto punto = puntosTrasladadosArray[i];
                planoCartesiano.addPunto(punto);
                planoCartesiano.addLinea(new Linea(puntoAnterior, punto, true, i));
                puntoAnterior = punto;
            }

            // Actualiza la tabla de puntos trasladados
            updateTranslatedTable1(puntosTrasladadosList1);
            planoCartesiano.repaint();

            updateLabels(tx1Field.getText(), ty1Field.getText(), tz1Field.getText());
            translatedTable2Label.setText(String.format("Segunda Traslación (Tx2: %d, Ty2: %d, Tz2: %d)", 0, 0, 0));
            updateTitleLabelPrimera();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese valores numéricos válidos para Tx, Ty y Tz");
        }
    }

    private void realizarSegundaTraslacion() {

        if (puntosTrasladadosList1 == null || puntosTrasladadosList1.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Primero debe realizar la primera traslación");
            return;
        }

        limpiar();


        try {
            int tx = Integer.parseInt(tx2Field.getText());
            int ty = Integer.parseInt(ty2Field.getText());
            int tz = Integer.parseInt(tz2Field.getText());

            // Crear y dibujar los puntos trasladados
            Punto[] puntosTrasladadosArray = new Punto[puntosTrasladadosList1.size()];

            // Crear puntos trasladados con las nuevas coordenadas
            for (int i = 0; i < puntosTrasladadosList1.size(); i++) {
                Punto puntoOriginal = puntosTrasladadosList1.get(i);
                Punto puntoTrasladado = new Punto(
                        puntoOriginal.getX() + tx,  // Mover en X
                        puntoOriginal.getY() + ty,  // Mover en Y
                        puntoOriginal.getZ() + tz   // Mover en Z
                );

                // Asignar nombres a los puntos
                int[] referencias = {1, 4, 5, 8, 7, 2, 3, 6};
                if (i < 8) {
                    puntoTrasladado.setNombrePunto("P" + (i + 1) + "''");
                } else {
                    puntoTrasladado.setNombrePunto("P" + referencias[i - 8] + "''");
                }
                puntosTrasladadosArray[i] = puntoTrasladado;
            }

            // Convertir a lista
            puntosTrasladadosList2 = Arrays.asList(puntosTrasladadosArray);

            // Primero, volver a dibujar la primera traslación
            Punto puntoInicio1 = puntosTrasladadosList1.get(0);
            planoCartesiano.addPunto(puntoInicio1);

            Punto puntoAnterior1 = puntoInicio1;
            for (int i = 1; i < puntosTrasladadosList1.size(); i++) {
                Punto punto = puntosTrasladadosList1.get(i);
                planoCartesiano.addPunto(punto);
                planoCartesiano.addLinea(new Linea(puntoAnterior1, punto, true, i));
                puntoAnterior1 = punto;
            }

            // Luego, dibujar la segunda traslación
            Punto puntoInicio = puntosTrasladadosArray[0];
            planoCartesiano.addPunto(puntoInicio);

            Punto puntoAnterior = puntoInicio;
            for (int i = 1; i < puntosTrasladadosArray.length; i++) {
                Punto punto = puntosTrasladadosArray[i];
                planoCartesiano.addPunto(punto);
                planoCartesiano.addLinea(new Linea(puntoAnterior, punto, true, i));
                puntoAnterior = punto;
            }

            // Actualiza la tabla de puntos trasladados
            updateTranslatedTable2(puntosTrasladadosList2);
            planoCartesiano.repaint();

            // Actualizar las etiquetas con los valores de traslación
            translatedTable2Label.setText(String.format("Segunda Traslación (Tx2: %d, Ty2: %d, Tz2: %d)", tx, ty, tz));
            updateTitleLabelSegunda();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese valores numéricos válidos para Tx2, Ty2 y Tz2");
        }
    }
    // Modify the updateTranslatedTable2 method to include Z coordinate


    private void limpiar() {
        int xInicio = Integer.parseInt(xInicialField.getText());
        int yInicio = Integer.parseInt(yInicialField.getText());
        int zInicio = Integer.parseInt(ZInicialField.getText());
        drawFiguraOriginal(xInicio, yInicio, zInicio);

        if (puntosList != null && !puntosList.isEmpty()) {
            realizarPrimeraTraslacion();
        }
    }

    private void updateTitleLabelPrimera() {
        String tx1 = tx1Field.getText();
        String ty1 = ty1Field.getText();
        String tz1 = tz1Field.getText();
        titleLabel3.setText("Traslación 3D: T1(Tx1: "+tx1+", Ty1: "+ty1+", Tz1: "+tz1+")");
        titleLabel4.setText("Traslación 3D: T2(Tx2: 0, Ty2: 0, Tz2: 0)");
    }
    private void updateTitleLabelSegunda() {
        String tx1 = tx1Field.getText();
        String ty1 = ty1Field.getText();
        String tz1 = tz1Field.getText();
        String tx2 = tx2Field.getText();
        String ty2 = ty2Field.getText();
        String tz2 = tz2Field.getText();
        titleLabel3.setText("Traslación 3D: T1(Tx1: "+tx1+", Ty1: "+ty1+", Tz1: "+tz1+")");
        titleLabel4.setText("Traslación 3D: T2(Tx2: "+tx2+", Ty2: "+ty2+", Tz2: "+tz2+")");
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
            titleLabel3.setText("Traslación 3D: T1(Tx1: "+0+", Ty1: "+0+", Tz1: "+0+")");
            titleLabel4.setText("Traslación 3D: T2(Tx2: "+0+", Ty2: "+0+", Tz2: "+0+")");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese valores numéricos válidos.");
        }
    }

    private void updateLabels(String tx, String ty, String tz) {
        // Actualizar la etiqueta de la tabla escalada
        Component parent = translatedTable1.getParent().getParent().getParent();
        if (parent instanceof JPanel) {
            ((JLabel) ((JPanel) parent).getComponent(0)).setText("Puntos Trasladados " +
                    "(Tx: "+tx+
                    ", Ty: "+ty+
                    ", Tz: "+tz+")");
        }
    }


    private void clearPlanoAndData() {
        planoCartesiano.clear();
        originalTableModel.setRowCount(0);
        translatedTableModel1.setRowCount(0);
        translatedTableModel2.setRowCount(0);
        puntosTrasladadosList1 = null;
        puntosTrasladadosList2 = null;
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
    private void updateTranslatedTable2(List<Punto> puntos) {
        translatedTableModel2.setRowCount(0);
        boolean[] puntosConCodigo = {false, true, true, true, true, true, true, true, true, true, false, true, false, true, false, true};

        int i = 0;
        for (Punto punto : puntos) {
            int cod = puntosConCodigo[i] ? 1 : 0;
            translatedTableModel2.addRow(new Object[]{
                    punto.getNombrePunto(),
                    punto.getX(),
                    punto.getY(),
                    punto.getZ(),
                    cod
            });
            i++;
        }
        translatedTable2Label.setText(String.format("Segunda Traslación (Tx2: %d, Ty2: %d, Tz2: %d)", tx2, ty2, tz2));
    }


    private void updateTranslatedTable1(List<Punto> puntos) {
        translatedTableModel1.setRowCount(0);
        boolean[] puntosConCodigo = {false, true, true, true, true, true, true, true, true, true, false, true, false, true, false, true};

        int i = 0;
        for (Punto punto : puntos) {
            int cod = puntosConCodigo[i] ? 1 : 0;
            translatedTableModel1.addRow(new Object[]{
                    punto.getNombrePunto(),
                    punto.getX(),
                    punto.getY(),
                    punto.getZ(),
                    cod
            });
            i++;
        }
        translatedTable1Label.setText(String.format("Primera Traslación (Tx1: %d, Ty1: %d)", tx1, ty1));
    }

    private void clearTableAll(int index) {
        if(index == 1){
            translatedTable1Label.setText("Primera Traslación (Tx1: 0, Ty1: 0)");
            translatedTable2Label.setText("Segunda Traslación (Tx2: 0, Ty2: 0)");
        } else if (index == 2){
            translatedTable2Label.setText("Segunda Traslación (Tx2: 0, Ty2: 0)");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new PolilineasTraslacionSuc();
        });
    }
}
