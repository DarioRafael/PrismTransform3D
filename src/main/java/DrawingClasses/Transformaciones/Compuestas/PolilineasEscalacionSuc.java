package DrawingClasses.Transformaciones.Compuestas;

import PaginaPrincipalFolder.Transformaciones.Componentes.TransformacionesCompuestas;
import PaginaPrincipalFolder.Transformaciones.PaginasImport.FormulasTCompuestas.FormulaEscalacionSuc;
import PaginaPrincipalFolder.Transformaciones.PaginasImport.FormulasTCompuestas.FormulaTraslacionSuc;
import Plano.Transformaciones.Basicas.PlanoCartesianoEscalacion;
import formasADibujar.Linea;
import formasADibujar.Punto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PolilineasEscalacionSuc extends JFrame {
    private PlanoCartesianoEscalacion planoCartesiano;
    private JTable originalTable;
    private JTable scaledTable1;
    private JTable scaledTable2;
    private DefaultTableModel originalTableModel;
    private DefaultTableModel scaledTableModel1;
    private DefaultTableModel scaledTableModel2;
    private JButton backButton, formulaButton;
    private JTextField xInicialField, yInicialField, ZInicialField;
    private JTextField sx1Field, sy1Field, sx2Field, sy2Field, sz1Field, sz2Field;

    public JComboBox<String> aumentoComboBox;
    private JButton regenerarFigura;
    private JButton escalarButton1;
    private JButton escalarButton2;
    private List<Punto> puntosList;
    private List<Punto> puntosEscalados1List;
    private List<Punto> puntosEscalados2List;
    private JLabel scaletedTable1Label;
    private JLabel scaletedTable2Label;
    private int sx1, sx2, sy1, sy2, sz1, sz2;
    private JLabel titleLabel3, titleLabel4;

    public PolilineasEscalacionSuc() {
        setTitle("Transformaciones Geométricas 3D Compuestas: Escalación Sucesiva");
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


        sx1Field = new JTextField("2", 5);
        sy1Field = new JTextField("2", 5);
        sz1Field = new JTextField("2", 5);

        sx2Field = new JTextField("1", 5);
        sy2Field = new JTextField("1", 5);
        sz2Field = new JTextField("2", 5);

        backButton = new JButton("Menu");
        formulaButton = new JButton("Formula");

        regenerarFigura = new JButton("Graficar");
        escalarButton1 = new JButton("Escalar");
        escalarButton2 = new JButton("Escalar");


        String[] columnNames = {"Punto", "X", "Y", "Z", "Cod"};
        String[] columnNames1 = {"P'", "X'", "Y'", "Z'", "Cod"};
        String[] columnNames2 = {"P''", "X''", "Y''", "Z''","Cod"};
        originalTableModel = new DefaultTableModel(columnNames, 0);
        scaledTableModel1 = new DefaultTableModel(columnNames1, 0);
        scaledTableModel2 = new DefaultTableModel(columnNames2, 0);
        originalTable = new JTable(originalTableModel);
        scaledTable1 = new JTable(scaledTableModel1);
        scaledTable2 = new JTable(scaledTableModel2);


    }

    private void configureLayout() {
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new BorderLayout());
        JLabel titleLabel1 = new JLabel("Transformaciones Geométricas 3D Compuestas:", SwingConstants.CENTER);
        titleLabel1.setFont(new Font("Arial", Font.BOLD, 20));

        JLabel titleLabel2pre = new JLabel("Modelo de alambre: Método de Líneas", SwingConstants.CENTER);
        titleLabel2pre.setFont(new Font("Arial", Font.BOLD, 18));

        JLabel titleLabel2 = new JLabel("Poliedro: Prisma Cuadrangular", SwingConstants.CENTER);
        titleLabel2.setFont(new Font("Arial", Font.BOLD, 16));

        titleLabel3 = new JLabel("Escalación Sucesiva 3D: S1(Sx1: 0, Sy1: 0, Sz1: 0)", SwingConstants.CENTER);
        titleLabel3.setFont(new Font("Arial", Font.BOLD, 14));

        titleLabel4 = new JLabel("Escalación Sucesiva 3D: S2(Sx2: 0, Sy2: 0, Sz2: 0)", SwingConstants.CENTER);
        titleLabel4.setFont(new Font("Arial", Font.BOLD, 14));


        JPanel titlePanel = new JPanel(new GridLayout(4, 1));
        titlePanel.add(titleLabel2pre);
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

        JPanel scaledTable1Panel = new JPanel(new BorderLayout());
        scaletedTable1Label = new JLabel("Primera Escalación (Sx1: 1, Sy1: 1, Sz1: 1)", SwingConstants.CENTER);
        scaledTable1Panel.add(scaletedTable1Label, BorderLayout.NORTH);

        JScrollPane scaledScrollPane1 = new JScrollPane(scaledTable1);
        scaledScrollPane1.setPreferredSize(new Dimension(300, 150));
        scaledTable1Panel.add(scaledScrollPane1, BorderLayout.CENTER);

        JPanel scaledTable2Panel = new JPanel(new BorderLayout());
        scaletedTable2Label = new JLabel("Segunda Escalación (Sx2: 1, Sy2: 1, Sz2: 1)", SwingConstants.CENTER);
        scaledTable2Panel.add(scaletedTable2Label, BorderLayout.NORTH);


        JScrollPane scaledScrollPane2 = new JScrollPane(scaledTable2);
        scaledScrollPane2.setPreferredSize(new Dimension(300, 150));
        scaledTable2Panel.add(scaledScrollPane2, BorderLayout.CENTER);

        tablesPanel.add(originalTablePanel);
        tablesPanel.add(scaledTable1Panel);
        tablesPanel.add(scaledTable2Panel);

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

        // Primera escalación
        controlPanel.add(new JLabel("Sx1:"));
        controlPanel.add(sx1Field);
        controlPanel.add(new JLabel("Sy1:"));
        controlPanel.add(sy1Field);
        controlPanel.add(new JLabel("Sz1:"));
        controlPanel.add(sz1Field);
        controlPanel.add(new JLabel(""));
        controlPanel.add(escalarButton1);


        // Segunda escalación
        controlPanel.add(new JLabel("Sx2:"));
        controlPanel.add(sx2Field);
        controlPanel.add(new JLabel("Sy2:"));
        controlPanel.add(sy2Field);
        controlPanel.add(new JLabel("Sz2:"));
        controlPanel.add(sz2Field);
        controlPanel.add(new JLabel(""));
        controlPanel.add(escalarButton2);


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
            new FormulaEscalacionSuc().setVisible(true);
        });

        regenerarFigura.addActionListener(e -> {
            int xInicio = Integer.parseInt(xInicialField.getText());
            int yInicio = Integer.parseInt(yInicialField.getText());
            int zInicio = Integer.parseInt(ZInicialField.getText());
            drawFiguraOriginal(xInicio, yInicio, zInicio);
        });

        escalarButton1.addActionListener(e -> realizarPrimeraEscalacion());
        escalarButton2.addActionListener(e -> realizarSegundaEscalacion());
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
            titleLabel3.setText("Escalación Sucesiva 3D: S1(Sx1: 0, Sy1: 0, Sz1: 0)");
            titleLabel4.setText("Escalación Sucesiva 3D: S2(Sx2: 0, Sy2: 0, Sz2: 0)");

            planoCartesiano.repaint();
            updateLabels("0", "0", "0");

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese valores numéricos válidos.");
        }
    }

    private void realizarPrimeraEscalacion() {
        scaledTableModel2.setRowCount(0);

        planoCartesiano.clear();
        int xInicio = Integer.parseInt(xInicialField.getText());
        int yInicio = Integer.parseInt(yInicialField.getText());
        int zInicio = Integer.parseInt(ZInicialField.getText());
        drawFiguraOriginal(xInicio, yInicio, zInicio);



        try {
            sx1 = Integer.parseInt(sx1Field.getText());
            sy1 = Integer.parseInt(sy1Field.getText());
            sz1 = Integer.parseInt(sz1Field.getText());

            if (puntosList == null || puntosList.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Primero debe generar la figura original");
                return;
            }

            // Crear un array de puntos escalados
            Punto[] puntosEscaladosArray = new Punto[puntosList.size()];
            for (int i = 0; i < 8; i++) {
                Punto puntoOriginal = puntosList.get(i);
                int nuevoX = puntoOriginal.getX() * sx1;
                int nuevoY = puntoOriginal.getY() * sy1;
                int nuevoZ = puntoOriginal.getZ() * sz1;
                Punto puntoEscalado = new Punto(nuevoX, nuevoY, nuevoZ);
                puntoEscalado.setNombrePunto(puntoOriginal.getNombrePunto() + "'");
                puntosEscaladosArray[i] = puntoEscalado;
            }

            // Agregar los puntos de referencia (segunda parte)
            int[] referencias = {1, 4, 5, 8, 7, 2, 3, 6};
            for (int i = 8; i < puntosList.size(); i++) {
                Punto puntoOriginal = puntosList.get(i);
                int nuevoX = puntoOriginal.getX() * sx1;
                int nuevoY = puntoOriginal.getY() * sy1;
                int nuevoZ = puntoOriginal.getZ() * sz1;
                Punto puntoEscalado = new Punto(nuevoX, nuevoY, nuevoZ);
                puntoEscalado.setNombrePunto("P" + referencias[i - 8] + "'");
                puntosEscaladosArray[i] = puntoEscalado;
            }

            // Convertir a lista
            puntosEscalados1List = Arrays.asList(puntosEscaladosArray);



            // Dibujar la figura escalada
            Punto puntoAnterior = puntosEscalados1List.get(0);  // Punto de inicio
            planoCartesiano.addPunto(puntoAnterior);  // Agrega el primer punto

            for (int i = 0; i < puntosEscalados1List.size(); i++) {
                Punto punto = puntosEscalados1List.get(i);
                planoCartesiano.addPunto(punto);
                planoCartesiano.addLinea(new Linea(puntoAnterior, punto, true, i + 1));
                puntoAnterior = punto;
            }

            // Limpiar y actualizar la tabla
            clearTableAll(1);
            updateScaledTable1(puntosEscalados1List);
            updateTitleLabelPrimera();
            // Redibujar el plano
            planoCartesiano.repaint();



        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese valores numéricos válidos.");
        }
    }

    private void realizarSegundaEscalacion() {
        scaledTableModel2.setRowCount(0);
        limpiar();
        try {
            sx2 = Integer.parseInt(sx2Field.getText());
            sy2 = Integer.parseInt(sy2Field.getText());
            sz2 = Integer.parseInt(sz2Field.getText()); // Agrego sz2 para ser consistente con la primera escalación

            if (puntosEscalados1List == null || puntosEscalados1List.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Primero debe realizar la primera escalación");
                return;
            }

            // Crear un array de puntos escalados
            Punto[] puntosEscalados2Array = new Punto[puntosEscalados1List.size()];
            for (int i = 0; i < 8; i++) {
                Punto puntoEscalado1 = puntosEscalados1List.get(i);
                int nuevoX = puntoEscalado1.getX() * sx2;
                int nuevoY = puntoEscalado1.getY() * sy2;
                int nuevoZ = puntoEscalado1.getZ() * sz2; // Escalar también la coordenada Z
                Punto puntoEscalado2 = new Punto(nuevoX, nuevoY, nuevoZ);
                puntoEscalado2.setNombrePunto(puntoEscalado1.getNombrePunto().replace("'", "") + "''");
                puntosEscalados2Array[i] = puntoEscalado2;
            }

            // Agregar los puntos de referencia (segunda parte)
            int[] referencias = {1, 4, 5, 8, 7, 2, 3, 6};
            for (int i = 8; i < puntosEscalados1List.size(); i++) {
                Punto puntoEscalado1 = puntosEscalados1List.get(i);
                int nuevoX = puntoEscalado1.getX() * sx2;
                int nuevoY = puntoEscalado1.getY() * sy2;
                int nuevoZ = puntoEscalado1.getZ() * sz2; // Escalar también la coordenada Z
                Punto puntoEscalado2 = new Punto(nuevoX, nuevoY, nuevoZ);
                puntoEscalado2.setNombrePunto("P" + referencias[i - 8] + "''");
                puntosEscalados2Array[i] = puntoEscalado2;
            }

            // Convertir a lista
            puntosEscalados2List = Arrays.asList(puntosEscalados2Array);

            // Dibujar la figura escalada
            Punto puntoAnterior = puntosEscalados2List.get(0);  // Punto de inicio
            planoCartesiano.addPunto(puntoAnterior);  // Agrega el primer punto

            for (int i = 0; i < puntosEscalados2List.size(); i++) {
                Punto punto = puntosEscalados2List.get(i);
                planoCartesiano.addPunto(punto);
                planoCartesiano.addLinea(new Linea(puntoAnterior, punto, true, i + 1));
                puntoAnterior = punto;
            }

            // Limpiar y actualizar la tabla
            clearTableAll(2);
            updateScaledTable2(puntosEscalados2List);
            updateTitleLabelSegunda();
            // Redibujar el plano
            planoCartesiano.repaint();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese valores numéricos válidos.");
        }
    }

    private void limpiar() {
        int xInicio = Integer.parseInt(xInicialField.getText());
        int yInicio = Integer.parseInt(yInicialField.getText());
        int zInicio = Integer.parseInt(ZInicialField.getText());
        drawFiguraOriginal(xInicio, yInicio, zInicio);

        if (puntosList != null && !puntosList.isEmpty()) {
            realizarPrimeraEscalacion();
        }
    }

    private void updateTitleLabelPrimera() {
        String sx1 = sx1Field.getText();
        String sy1 = sy1Field.getText();
        String sz1 = sz1Field.getText();
        titleLabel3.setText("Escalación Sucesiva 3D: S1(Sx1: "+sx1+", Sy1: "+sy1+", Sz1: "+sz1+")");
    }
    private void updateTitleLabelSegunda() {
        String sx1 = sx1Field.getText();
        String sy1 = sy1Field.getText();
        String sz1 = sz1Field.getText();
        String sx2 = sx2Field.getText();
        String sy2 = sy2Field.getText();
        String sz2 = sz2Field.getText();
        titleLabel3.setText("Escalación Sucesiva 3D: S1(Sx1: "+sx1+", Sy1: "+sy1+", Sz1: "+sz1+")");
        titleLabel4.setText("Escalación Sucesiva 3D: S2(Sx2: "+sx2+", Sy2: "+sy2+", Sz2: "+sz2+")");
    }

    private void updateLabels(String tx, String ty, String tz) {
        // Actualizar la etiqueta de la tabla escalada
        Component parent = scaledTable1.getParent().getParent().getParent();
        if (parent instanceof JPanel) {
            ((JLabel) ((JPanel) parent).getComponent(0)).setText("Puntos Trasladados " +
                    "(Sx: "+tx+
                    ", Sy: "+ty+
                    ", Sz: "+tz+")");
        }
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

    private void updateScaledTable1(List<Punto> puntos) {
        scaledTableModel1.setRowCount(0);
        boolean[] puntosConCodigo = {false, true, true, true, true, true, true, true, true, true, false, true, false, true, false, true};
        int i = 0;

        for (Punto punto : puntos) {
            int cod = puntosConCodigo[i] ? 1 : 0;

            scaledTableModel1.addRow(new Object[]{
                    punto.getNombrePunto(),
                    punto.getX(),
                    punto.getY(),
                    punto.getZ(),
                    cod
            });
            i++;
        }
        scaletedTable1Label.setText(String.format("Primera Escalación (Sx1: %d, Sy1: %d, Sz1: %d)", sx1, sy1,sz1));

    }

    private void updateScaledTable2(List<Punto> puntos) {
        scaledTableModel2.setRowCount(0);
        boolean[] puntosConCodigo = {false, true, true, true, true, true, true, true, true, true, false, true, false, true, false, true};
        int i = 0;

        for (Punto punto : puntos) {
            int cod = puntosConCodigo[i] ? 1 : 0;

            scaledTableModel2.addRow(new Object[]{
                    punto.getNombrePunto(),
                    punto.getX(),
                    punto.getY(),
                    punto.getZ(),
                    cod
            });
            i++;
        }
        scaletedTable2Label.setText(String.format("Segunda Escalación (Sx2: %d, Sy2: %d, Sz2: %d)", sx2, sy2,sz2));
    }

    private void clearTableAll(int index) {
        if(index == 1){
            scaletedTable1Label.setText("Primera Escalación (Sx1: 1, Sy1: 1, Sz1: 1)");
            scaletedTable2Label.setText("Segunda Escalación (Sx2: 1, Sy2: 1, Sz2: 1)");
        } else if (index == 2){
            scaletedTable2Label.setText("Segunda Escalación (Sx2: 1, Sy2: 1, Sz2: 1)");
        }
    }

    private void clearPlanoAndData() {
        planoCartesiano.clear();
        originalTableModel.setRowCount(0);
        scaledTableModel1.setRowCount(0);
        scaledTableModel2.setRowCount(0);
        puntosList = null;
        puntosEscalados1List = null;
        puntosEscalados2List = null;
        planoCartesiano.repaint();

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PolilineasEscalacionSuc());
    }
}