
import javax.swing.*;
import java.awt.*;

public class GraficoGoles extends JPanel {
    private Equipo equipo;

    public GraficoGoles(Equipo equipo) {
        this.equipo = equipo;
        setPreferredSize(new Dimension(250, 250));
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createTitledBorder("Balance de Goles"));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int totalGoles = equipo.getGolesFavor() + equipo.getGolesContra();
        int x = 50, y = 60, ancho = 150, alto = 150;

        if (totalGoles > 0) {
            int anguloFavor = (equipo.getGolesFavor() * 360) / totalGoles;

            // Arco Goles a Favor (Verde)
            g2d.setColor(new Color(39, 174, 96));
            g2d.fillArc(x, y, ancho, alto, 0, anguloFavor);

            // Arco Goles en Contra (Rojo)
            g2d.setColor(new Color(192, 57, 43));
            g2d.fillArc(x, y, ancho, alto, anguloFavor, 360 - anguloFavor);

            // Referencias
            g2d.setColor(Color.BLACK);
            g2d.drawString("Favor: " + equipo.getGolesFavor(), 10, 20);
            g2d.drawString("Contra: " + equipo.getGolesContra(), 10, 35);
        } else {
            g2d.setColor(Color.LIGHT_GRAY);
            g2d.fillOval(x, y, ancho, alto);
            g2d.setColor(Color.DARK_GRAY);
            g2d.drawString("Sin datos de goles", x + 35, y + 80);
        }
    }
}