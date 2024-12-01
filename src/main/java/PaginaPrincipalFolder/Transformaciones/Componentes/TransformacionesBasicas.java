package PaginaPrincipalFolder.Transformaciones.Componentes;

import DrawingClasses.Transformaciones.Basicas.PolilineasEscalacion;
import DrawingClasses.Transformaciones.Basicas.PolilineasRotacion;
import DrawingClasses.Transformaciones.Basicas.PolilineasTraslacion;
import PaginaPrincipalFolder.Settings.AjustesVariables;
import PaginaPrincipalFolder.Settings.AjustesVentana;
import PaginaPrincipalFolder.Transformaciones.PaginasImport.MenuTransformaciones;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TransformacionesBasicas extends JFrame {
    private static final Color BACKGROUND_COLOR = new Color(245, 245, 250);
    private static final Color BUTTON_COLOR = new Color(70, 130, 180);
    private static final Color HOVER_COLOR = new Color(100, 149, 237);
    public AjustesVentana ajustesVentana = new AjustesVentana();
    public AjustesVariables ajustesVariables = new AjustesVariables();

    public TransformacionesBasicas() {
        setTitle("Transformaciones Geométricas 2D Básicas");
        setSize(ajustesVentana.getWindowSize());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout(20, 20));
        mainPanel.setBackground(BACKGROUND_COLOR);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));

        // Título
        JLabel titleLabel = new JLabel("Transformaciones Geométricas 2D Básicas", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        // Panel de botones
        JPanel buttonPanel = new JPanel(new GridLayout(3, 1, 15, 15));
        buttonPanel.setBackground(BACKGROUND_COLOR);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 100, 20, 100));

        JButton traslacionButton = createTransformationButton("Traslación", "Mover figuras en el plano");
        JButton escalacionButton = createTransformationButton("Escalación", "Cambiar el tamaño de las figuras");
        JButton rotacionButton = createTransformationButton("Rotación", "Girar figuras alrededor de un punto");

        traslacionButton.addActionListener(e -> {
            dispose();
            PolilineasTraslacion frame = new PolilineasTraslacion();
            frame.setSize(ajustesVentana.getWindowSize());
            frame.setLocationRelativeTo(null);
            frame.drawFiguraOriginal(ajustesVariables.getX(), ajustesVariables.getY(), 1);
            frame.aumentoComboBox.setSelectedIndex(0);
            frame.txField.setText("1");
            frame.tyField.setText("2");
        });

        escalacionButton.addActionListener(e -> {
            dispose();
            PolilineasEscalacion frame = new PolilineasEscalacion();
            frame.setSize(ajustesVentana.getWindowSize());
            frame.setLocationRelativeTo(null);
            frame.drawFiguraOriginal(ajustesVariables.getX(), ajustesVariables.getY(), 1);
            frame.aumentoComboBox.setSelectedIndex(0);
            frame.sxField.setText("1");
            frame.syField.setText("2");

        });

        rotacionButton.addActionListener(e -> {
            dispose();
            PolilineasRotacion frame = new PolilineasRotacion();
            frame.setSize(ajustesVentana.getWindowSize());
            frame.setLocationRelativeTo(null);
            frame.drawFiguraOriginal(ajustesVariables.getX(), ajustesVariables.getY(), 1);
            frame.aumentoComboBox.setSelectedIndex(0);
            frame.anguloField.setText("90");
        });

        buttonPanel.add(traslacionButton);
        buttonPanel.add(escalacionButton);
        buttonPanel.add(rotacionButton);

        // Botón de regreso
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        bottomPanel.setBackground(BACKGROUND_COLOR);

        JButton backButton = new JButton("← Regresar al Menú Principal");
        backButton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        backButton.setForeground(BUTTON_COLOR);
        backButton.setBackground(BACKGROUND_COLOR);
        backButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        backButton.setFocusPainted(false);
        backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        backButton.addActionListener(e -> {
            dispose();
            MenuTransformaciones.main(new String[]{});
        });

        bottomPanel.add(backButton);

        mainPanel.add(buttonPanel, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        add(mainPanel);
        setVisible(true);
    }

    private JButton createTransformationButton(String title, String description) {
        JButton button = new JButton();
        button.setLayout(new BorderLayout());

        JPanel content = new JPanel(new GridLayout(2, 1, 5, 5));
        content.setOpaque(false);

        JLabel titleLabel = new JLabel(title, SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));

        JLabel descLabel = new JLabel(description, SwingConstants.CENTER);
        descLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        content.add(titleLabel);
        content.add(descLabel);

        button.add(content);
        button.setBackground(BUTTON_COLOR);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(15, 25, 15, 25));

        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                button.setBackground(HOVER_COLOR);
            }
            public void mouseExited(MouseEvent e) {
                button.setBackground(BUTTON_COLOR);
            }
        });

        return button;
    }
}