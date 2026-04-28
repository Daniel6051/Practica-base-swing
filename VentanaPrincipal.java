import javax.swing.*;
import java.awt.*;

public class VentanaPrincipal extends JFrame {
    private Equipo equipo;
    private JLabel lblEstadisticas;
    private GraficoGoles grafico;

    public VentanaPrincipal(Equipo eq) {
        super("Gestor de Liga v1.0");
        this.equipo = eq;
        
        setSize(700, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centra la ventana
        setLayout(new BorderLayout());

        // Cabecera con estadísticas dinámicas
        lblEstadisticas = new JLabel("", SwingConstants.CENTER);
        actualizarEtiqueta();
        lblEstadisticas.setFont(new Font("SansSerif", Font.BOLD, 18));
        lblEstadisticas.setOpaque(true);
        lblEstadisticas.setBackground(new Color(44, 62, 80));
        lblEstadisticas.setForeground(Color.WHITE);
        lblEstadisticas.setBorder(BorderFactory.createEmptyBorder(15, 10, 15, 10));
        add(lblEstadisticas, BorderLayout.NORTH);

        // Panel central que combina el formulario y el gráfico
        grafico = new GraficoGoles(equipo);
        FormularioPartido formulario = new FormularioPartido(equipo, () -> {
            actualizarEtiqueta();
            grafico.repaint(); // Redibuja el gráfico circular
        });

        add(formulario, BorderLayout.WEST);
        add(grafico, BorderLayout.CENTER);

        // Pie de página
        add(new JLabel(" Sistema de Gestión Académica - Proyecto Swing", SwingConstants.LEFT), BorderLayout.SOUTH);
    }

    private void actualizarEtiqueta() {
        lblEstadisticas.setText(String.format("Equipo: %s | Puntos: %d | Dif. Gol: %d", 
                                equipo.getNombre(), equipo.getPuntos(), equipo.getDiferenciaGol()));
    }
}