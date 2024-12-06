package PaginaPrincipalFolder.Transformaciones.PaginasImport.FormulasTBasicas;

import PaginaPrincipalFolder.Settings.AjustesVentanaFormula;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class FormulaRotacion extends JFrame {

    AjustesVentanaFormula ajustesVentana = new AjustesVentanaFormula();

    public FormulaRotacion() {
        setTitle("FÓRMULAS DE LA ROTACIÓN 3D BÁSICA");
        setSize(ajustesVentana.getWindowSize());
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createCompoundBorder(
                new EmptyBorder(20, 30, 20, 30),
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
                        new EmptyBorder(20, 20, 20, 20)
                )
        ));
        mainPanel.setBackground(new Color(252, 252, 252));

        JPanel centeredPanel = new JPanel();
        centeredPanel.setLayout(new BoxLayout(centeredPanel, BoxLayout.Y_AXIS));
        centeredPanel.setBackground(mainPanel.getBackground());
        centeredPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Título principal
        JLabel titleLabel = new JLabel("FÓRMULAS DE LA ROTACIÓN 3D BÁSICA");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(33, 33, 33));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Separador
        JSeparator separator = new JSeparator();
        separator.setMaximumSize(new Dimension(400, 1));
        separator.setForeground(new Color(200, 200, 200));

        // Procedimiento General (Eje X)
        JLabel procGeneralLabel = createSectionLabel("Rotación 3D sobre el eje X: Rx(θ)");
        JLabel pxyGeneralLabel = createContentLabel("La X permanece constante:");

        JLabel matrizGeneral = new JLabel("<html><div style='text-align: center; font-family: Courier New; font-size: 12px;'>"
                + "P(X, Y, Z): <br>"
                + "[X' Y' Z' 1] = [X Y Z 1] · "
                + "<table align='center' style='margin-top: 10px;'>"
                + "<tr><td>[</td><td>&nbsp;1&nbsp;</td><td>&nbsp;0&nbsp;</td><td>&nbsp;0&nbsp;</td><td>&nbsp;0&nbsp;</td><td>]</td></tr>"
                + "<tr><td>[</td><td>&nbsp;0&nbsp;</td><td>&nbsp;Cos&nbsp;θ&nbsp;</td><td>&nbsp;Sen&nbsp;θ&nbsp;</td><td>&nbsp;0&nbsp;</td><td>]</td></tr>"
                + "<tr><td>[</td><td>&nbsp;0&nbsp;</td><td>&nbsp;-Sen&nbsp;θ&nbsp;</td><td>&nbsp;Cos&nbsp;θ&nbsp;</td><td>&nbsp;0&nbsp;</td><td>]</td></tr>"
                + "<tr><td>[</td><td>&nbsp;0&nbsp;</td><td>&nbsp;0&nbsp;</td><td>&nbsp;0&nbsp;</td><td>&nbsp;1&nbsp;</td><td>]</td></tr>"
                + "</table>"
                + "<div style='margin-top: 10px;'>Operaciones:</div>"
                + "<div style='margin-top: 10px;'>"
                + "X(1) + Y(0) + Z(0) + 1(0) = X<br>"
                + "X(0) + Y(Cos θ) + Z(-Sen θ) + 1(0) = Y * Cos θ - Z * Sen θ<br>"
                + "X(0) + Y(Sen θ) + Z(Cos θ) + 1(0) =  Y * Sen θ + Z * Cos θ<br>"
                + "X(0) + Y(0) + Z(0) + 1(1) = 1"
                + "</div>"
                + "<div style='margin-top: 10px;'>[X' Y' Z' 1] = [(X), (Y * Cos θ - Z * Sen θ), (Y * Sen θ + Z * Cos θ), 1]</div></div></html>");
        matrizGeneral.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Procedimiento Particular (Eje Y)
        JLabel procParticularLabel = createSectionLabel("Rotación 3D sobre el eje Y: Ry(θ)");
        JLabel pxyParticularLabel = createContentLabel("La Y permanece constante:");
        JLabel matrizParticular = new JLabel("<html><div style='text-align: center; font-family: Courier New; font-size: 12px;'>"
                + "P(X, Y, Z): <br>"
                + "[X' Y' Z' 1] = [X Y Z 1] · "
                + "<table align='center' style='margin-top: 10px;'>"
                + "<tr><td>[</td><td>&nbsp;Cos&nbsp;θ&nbsp;</td><td>&nbsp;0&nbsp;</td><td>&nbsp;-Sen&nbsp;θ&nbsp;</td><td>&nbsp;0&nbsp;</td><td>]</td></tr>"
                + "<tr><td>[</td><td>&nbsp;0&nbsp;</td><td>&nbsp;1&nbsp;</td><td>&nbsp;0&nbsp;</td><td>&nbsp;0&nbsp;</td><td>]</td></tr>"
                + "<tr><td>[</td><td>&nbsp;Sen&nbsp;θ&nbsp;</td><td>&nbsp;0&nbsp;</td><td>&nbsp;Cos&nbsp;θ&nbsp;</td><td>&nbsp;0&nbsp;</td><td>]</td></tr>"
                + "<tr><td>[</td><td>&nbsp;0&nbsp;</td><td>&nbsp;0&nbsp;</td><td>&nbsp;0&nbsp;</td><td>&nbsp;1&nbsp;</td><td>]</td></tr>"
                + "</table>"
                + "<div style='margin-top: 10px;'>Operaciones:</div>"
                + "<div style='margin-top: 10px;'>"
                + "X(Cos θ) + Y(0) + Z(Sen θ) + 1(0) = X * Cos θ + Z * Sen θ<br>"
                + "X(0) + Y(1) + Z(0) + 1(0) = Y<br>"
                + "X(-Sen θ) + Y(0) + Z(Cos θ) + 1(0) = -X * Sen θ + Z * Cos θ<br>"
                + "X(0) + Y(0) + Z(0) + 1(1) = 1"
                + "</div>"
                + "<div style='margin-top: 10px;'>[X' Y' Z' 1] = [(X * Cos θ + Z * Sen θ), (Y), (-X * Sen θ + Z * Cos θ), 1]</div></div></html>");
        matrizParticular.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Procedimiento Particular (Eje Z)
        JLabel procParticularLabelZ = createSectionLabel("Rotación 3D sobre el eje Z: Rz(θ)");
        JLabel pxyParticularLabelZ = createContentLabel("La Z permanece constante:");
        JLabel matrizParticularZ = new JLabel("<html><div style='text-align: center; font-family: Courier New; font-size: 12px;'>"
                + "P(X, Y, Z): <br>"
                + "[X' Y' Z' 1] = [X Y Z 1] · "
                + "<table align='center' style='margin-top: 10px;'>"
                + "<tr><td>[</td><td>&nbsp;Cos&nbsp;θ&nbsp;</td><td>&nbsp;Sen&nbsp;θ&nbsp;</td><td>&nbsp;0&nbsp;</td><td>&nbsp;0&nbsp;</td><td>]</td></tr>"
                + "<tr><td>[</td><td>&nbsp;-Sen&nbsp;θ&nbsp;</td><td>&nbsp;Cos&nbsp;θ&nbsp;</td><td>&nbsp;0&nbsp;</td><td>&nbsp;0&nbsp;</td><td>]</td></tr>"
                + "<tr><td>[</td><td>&nbsp;0&nbsp;</td><td>&nbsp;0&nbsp;</td><td>&nbsp;1&nbsp;</td><td>&nbsp;0&nbsp;</td><td>]</td></tr>"
                + "<tr><td>[</td><td>&nbsp;0&nbsp;</td><td>&nbsp;0&nbsp;</td><td>&nbsp;0&nbsp;</td><td>&nbsp;1&nbsp;</td><td>]</td></tr>"
                + "</table>"
                + "<div style='margin-top: 10px;'>Operaciones:</div>"
                + "<div style='margin-top: 10px;'>"
                + "X(Cos θ) + Y(-Sen θ) + Z(0) + 1(0) = X * Cos θ - Y * Sen θ<br>"
                + "X(Sen θ) + Y(Cos θ) + Z(0) + 1(0) =  X * Sen θ + Y * Cos θ<br>"
                + "X(0) + Y(0) + Z(1) + 1(0) = Z<br>"
                + "X(0) + Y(0) + Z(0) + 1(1) = 1"
                + "</div>"
                + "<div style='margin-top: 10px;'>[X' Y' Z' 1] = [(X * Cos θ - Y * Sen θ), (X * Sen θ + Y * Cos θ), (Z), 1]</div></div></html>");
        matrizParticularZ.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Botón Aceptar
        JButton aceptarButton = new JButton("Aceptar");
        aceptarButton.setFont(new Font("Arial", Font.BOLD, 14));
        aceptarButton.setPreferredSize(new Dimension(120, 35));
        aceptarButton.setMaximumSize(new Dimension(120, 35));
        aceptarButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        aceptarButton.setBackground(new Color(70, 130, 180));
        aceptarButton.setForeground(Color.BLUE);
        aceptarButton.setFocusPainted(false);
        aceptarButton.addActionListener(e -> dispose());

        // Agregar componentes al panel
        centeredPanel.add(titleLabel);
        centeredPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        centeredPanel.add(separator);
        centeredPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        // Agregar Procedimiento General
        centeredPanel.add(procGeneralLabel);
        centeredPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        centeredPanel.add(pxyGeneralLabel);
        centeredPanel.add(matrizGeneral);
        centeredPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        // Agregar Procedimiento Particular para Y
        centeredPanel.add(procParticularLabel);
        centeredPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        centeredPanel.add(pxyParticularLabel);
        centeredPanel.add(matrizParticular);
        centeredPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        // Agregar Procedimiento Particular para Z
        centeredPanel.add(procParticularLabelZ);
        centeredPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        centeredPanel.add(pxyParticularLabelZ);
        centeredPanel.add(matrizParticularZ);
        centeredPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        centeredPanel.add(aceptarButton);

        // Agregar panel centrado al panel principal
        mainPanel.add(centeredPanel);

        // Agregar scroll
        JScrollPane scrollPane = new JScrollPane(mainPanel);
        scrollPane.setBorder(null);
        scrollPane.setBackground(new Color(252, 252, 252));
        add(scrollPane);

        // Hacer la ventana visible
        setVisible(true);
    }

    private JLabel createSectionLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.BOLD, 16));
        label.setForeground(new Color(30, 30, 30));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        return label;
    }

    private JLabel createContentLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.PLAIN, 14));
        label.setForeground(new Color(50, 50, 50));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        return label;
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> {
            FormulaRotacion frame = new FormulaRotacion();
            frame.setVisible(true);
        });
    }
}