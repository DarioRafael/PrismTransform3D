package PaginaPrincipalFolder.Transformaciones.PaginasImport;

import PaginaPrincipalFolder.Settings.AjustesVentana;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class PortadaTransformaciones extends JFrame {
    private static final Color BACKGROUND_COLOR = new Color(245, 245, 255);
    private static final Color ACCENT_COLOR = new Color(70, 130, 180);
    private static final Color TEXT_COLOR = new Color(25, 25, 25);
    private static final Color SECONDARY_COLOR = new Color(100, 100, 100);
    private static final Color BUTTON_HOVER_COLOR = new Color(60, 110, 160);
    AjustesVentana ajustesVentana = new AjustesVentana();
    private JButton entrarButton;
    public PortadaTransformaciones() {
        configurarVentana();
        inicializarComponentes();
        addActionListener();
    }

    private void configurarVentana() {
        setTitle("Transformaciones Geométricas en 2D");
        setMinimumSize(new Dimension(800, 600)); // Reducido significativamente
        setPreferredSize(ajustesVentana.getWindowSize()); // Tamaño preferido más pequeño
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setBackground(BACKGROUND_COLOR);
    }
    private void addActionListener(){
        entrarButton.addActionListener(e -> {
            dispose();
            new MenuTransformaciones().main(new String[]{});
        });

    }

    private void inicializarComponentes() {
        JPanel mainPanel = createMainPanel();
        JPanel bannerPanel = createBannerPanel();
        JPanel contentPanel = createContentPanel();
        JPanel footerPanel = createFooterPanel();

        mainPanel.add(bannerPanel, BorderLayout.NORTH);
        mainPanel.add(contentPanel, BorderLayout.CENTER);
        mainPanel.add(footerPanel, BorderLayout.SOUTH);

        add(mainPanel);
        pack();
    }

    private JPanel createMainPanel() {
        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                GradientPaint gradient = new GradientPaint(
                        0, 0, new Color(245, 245, 255),
                        0, getHeight(), new Color(230, 230, 250)
                );
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        mainPanel.setLayout(new BorderLayout(0, 5)); // Espaciado vertical mínimo
        mainPanel.setBorder(new EmptyBorder(0, 0, 5, 0)); // Padding mínimo
        return mainPanel;
    }

    private JPanel createBannerPanel() {
        JPanel bannerPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon banner = new ImageIcon(getClass().getResource("/images/bannerTec.jpeg"));
                Image img = banner.getImage();
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
                g2d.drawImage(img, 0, 0, getWidth(), getHeight(), this);

                GradientPaint overlay = new GradientPaint(
                        0, 0, new Color(0, 0, 0, 0),
                        0, getHeight(), new Color(0, 0, 0, 50)
                );
                g2d.setPaint(overlay);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }

            @Override
            public Dimension getPreferredSize() {
                return new Dimension(super.getPreferredSize().width, 60); // Altura fija pequeña
            }
        };
        return bannerPanel;
    }

    private JPanel createContentPanel() {
        JPanel contentPanel = new JPanel(new GridBagLayout());
        contentPanel.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();

        JPanel cardPanel = createCardPanel();
        cardPanel.setLayout(new GridBagLayout());

        GridBagConstraints cardGbc = new GridBagConstraints();
        cardGbc.gridwidth = GridBagConstraints.REMAINDER;
        cardGbc.anchor = GridBagConstraints.CENTER;
        cardGbc.insets = new Insets(4, 8, 4, 8); // Márgenes mínimos

        // Textos con fuentes más pequeñas
        addStyledLabel(cardPanel, "Instituto Tecnológico de Cd. Victoria",
                new Font("Segoe UI", Font.BOLD, 16), TEXT_COLOR, cardGbc);
        addStyledLabel(cardPanel, "Ing. en Sistemas Computacionales",
                new Font("Segoe UI Light", Font.PLAIN, 14), SECONDARY_COLOR, cardGbc);
        addStyledLabel(cardPanel, "Graficación",
                new Font("Segoe UI", Font.BOLD, 15), ACCENT_COLOR, cardGbc);

        JSeparator separator = new JSeparator();
        separator.setForeground(ACCENT_COLOR);
        separator.setPreferredSize(new Dimension(100, 1)); // Separador más pequeño
        cardGbc.insets = new Insets(8, 0, 8, 0);
        cardPanel.add(separator, cardGbc);

        // Imagen mucho más pequeña
        try {
            ImageIcon figuraIcon = new ImageIcon(getClass().getResource("/images/figuraPolilineas.png"));
            Image figuraImg = figuraIcon.getImage();
            // Tamaño fijo pequeño para la imagen
            int newWidth = 400; // Ancho fijo de 200px
            int newHeight = (newWidth * figuraIcon.getIconHeight()) / figuraIcon.getIconWidth();

            Image scaledImage = figuraImg.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
            JLabel figuraLabel = new JLabel(new ImageIcon(scaledImage));
            figuraLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
            cardGbc.insets = new Insets(4, 0, 8, 0);
            cardPanel.add(figuraLabel, cardGbc);
        } catch (Exception e) {
            System.err.println("Error al cargar la imagen: " + e.getMessage());
        }

        cardGbc.insets = new Insets(4, 0, 4, 0);
        addStyledLabel(cardPanel, "Transformaciones Geométricas 2D",
                new Font("Segoe UI", Font.BOLD, 18), TEXT_COLOR, cardGbc);
        addStyledLabel(cardPanel, "Polilíneas",
                new Font("Segoe UI Light", Font.PLAIN, 16), SECONDARY_COLOR, cardGbc);

        entrarButton = createStyledButton("Entrar");
        cardGbc.insets = new Insets(10, 0, 8, 0);
        cardPanel.add(entrarButton, cardGbc);

        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(10, 20, 10, 20); // Márgenes reducidos
        contentPanel.add(cardPanel, gbc);

        return contentPanel;
    }

    private JPanel createCardPanel() {
        return new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                g2d.setColor(Color.WHITE);
                RoundRectangle2D roundedRectangle = new RoundRectangle2D.Float(
                        5, 5, getWidth()-10, getHeight()-10, 20, 20
                );
                g2d.fill(roundedRectangle);

                g2d.setColor(new Color(0, 0, 0, 20));
                g2d.setStroke(new BasicStroke(1.0f));
                g2d.draw(roundedRectangle);
            }
        };
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                if (getModel().isPressed()) {
                    g2d.setColor(BUTTON_HOVER_COLOR);
                } else if (getModel().isRollover()) {
                    g2d.setColor(ACCENT_COLOR);
                } else {
                    g2d.setColor(ACCENT_COLOR);
                }

                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 12, 12);

                g2d.setColor(Color.WHITE);
                g2d.setFont(getFont());
                FontMetrics metrics = g2d.getFontMetrics();
                int x = (getWidth() - metrics.stringWidth(getText())) / 2;
                int y = ((getHeight() - metrics.getHeight()) / 2) + metrics.getAscent();
                g2d.drawString(getText(), x, y);
            }
        };

        button.setFont(new Font("Segoe UI", Font.BOLD, 13));
        button.setForeground(Color.WHITE);
        button.setPreferredSize(new Dimension(100, 30)); // Botón más pequeño
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        return button;
    }

    private JPanel createFooterPanel() {
        JPanel footerPanel = new JPanel(new GridBagLayout());
        footerPanel.setOpaque(false);

        JLabel creditosLabel = new JLabel(
                "<html><center>Asesor: <b>ING. José Lino Hernández Omaña</b><br>" +
                        "Cd. Victoria, Tamaulipas - Septiembre 2024</center></html>"
        );
        creditosLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        creditosLabel.setForeground(SECONDARY_COLOR);
        footerPanel.add(creditosLabel);

        return footerPanel;
    }

    private void addStyledLabel(JPanel panel, String text, Font font, Color color, GridBagConstraints gbc) {
        JLabel label = new JLabel(text);
        label.setFont(font);
        label.setForeground(color);
        panel.add(label, gbc);
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> {
            new PortadaTransformaciones().setVisible(true);
        });
    }
}