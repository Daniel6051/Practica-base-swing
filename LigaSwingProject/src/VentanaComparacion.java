import javax.swing.*;
import java.awt.*;

public class VentanaComparacion extends JFrame {
    private PanelEquipo panelA, panelB;
    private JTextArea txtResultado;

    public VentanaComparacion() {
        super("Comparador de Posiciones - Liga");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel superior con los dos equipos (GridLayout de 1 fila, 2 columnas)
        JPanel panelCentral = new JPanel(new GridLayout(1, 2));
        panelA = new PanelEquipo("Equipo A");
        panelB = new PanelEquipo("Equipo B");
        panelCentral.add(panelA);
        panelCentral.add(panelB);
        add(panelCentral, BorderLayout.CENTER);

        // Panel inferior para el botón y el resultado
        JPanel panelInferior = new JPanel(new BorderLayout());
        JButton btnComparar = new JButton("¿Quién queda más arriba?");
        btnComparar.setFont(new Font("Arial", Font.BOLD, 14));
        
        txtResultado = new JTextArea(5, 30);
        txtResultado.setEditable(false);
        txtResultado.setBackground(new Color(240, 240, 240));

        btnComparar.addActionListener(e -> realizarComparacion());

        panelInferior.add(btnComparar, BorderLayout.NORTH);
        panelInferior.add(new JScrollPane(txtResultado), BorderLayout.CENTER);
        add(panelInferior, BorderLayout.SOUTH);
    }

    private void realizarComparacion() {
        Equipo e1 = panelA.getEquipo();
        Equipo e2 = panelB.getEquipo();

        String ganador;
        String razon;

        // Lógica de desempate
        if (e1.getPuntos() > e2.getPuntos()) {
            ganador = e1.getNombre();
            razon = "más puntos.";
        } else if (e2.getPuntos() > e1.getPuntos()) {
            ganador = e2.getNombre();
            razon = "más puntos.";
        } else {
            // Empate en puntos, comparamos Diferencia de Gol (DG)
            if (e1.getDiferenciaGol() > e2.getDiferenciaGol()) {
                ganador = e1.getNombre();
                razon = "mejor diferencia de gol (mismos puntos).";
            } else if (e2.getDiferenciaGol() > e1.getDiferenciaGol()) {
                ganador = e2.getNombre();
                razon = "mejor diferencia de gol (mismos puntos).";
            } else {
                ganador = "Empate Total";
                razon = "mismos puntos y misma diferencia de gol.";
            }
        }

        txtResultado.setText("--- RESULTADO DE POSICIÓN ---\n" +
                "Ganador: " + ganador + "\n" +
                "Motivo: " + razon + "\n" +
                "------------------------------\n" +
                e1.getNombre() + ": " + e1.getPuntos() + " pts (DG: " + e1.getDiferenciaGol() + ")\n" +
                e2.getNombre() + ": " + e2.getPuntos() + " pts (DG: " + e2.getDiferenciaGol() + ")");
    }
}