package PaginaPrincipalFolder.Settings;

import java.awt.*;

public class AjustesVentanaFormula {
    private Dimension windowSize;

    public AjustesVentanaFormula() {
        this.windowSize = new Dimension(850, 600);
    }
    public Dimension getWindowSize() {
        return windowSize;
    }

    public void setWindowSize(Dimension windowSize) {
        this.windowSize = windowSize;
    }
}