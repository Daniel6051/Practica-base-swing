import javax.swing.*;
import java.awt.*;

public class FormularioPartido extends JPanel {
    private JTextField txtGolesLocal, txtGolesVisitante;
    private Equipo equipoLocal;
    private Runnable alActualizar; // Callback para refrescar la UI

    public FormularioPartido(Equipo local, Runnable alActualizar) {
        this.equipoLocal = local;
        this.alActualizar = alActualizar;
        
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Título del formulario
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        JLabel lblTitulo = new JLabel("Cargar Partido: " + local.getNombre());
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 14));
        add(lblTitulo, gbc);

        // Campos de entrada
        gbc.gridwidth = 1;
        gbc.gridy = 1; gbc.gridx = 0;
        add(new JLabel("Goles a favor:"), gbc);
        
        txtGolesLocal = new JTextField(5);
        gbc.gridx = 1;
        add(txtGolesLocal, gbc);

        gbc.gridy = 2; gbc.gridx = 0;
        add(new JLabel("Goles en contra:"), gbc);
        
        txtGolesVisitante = new JTextField(5);
        gbc.gridx = 1;
        add(txtGolesVisitante, gbc);

        // Botón de acción
        JButton btnGuardar = new JButton("Registrar Goles");
        gbc.gridy = 3; gbc.gridx = 0; gbc.gridwidth = 2;
        add(btnGuardar, gbc);

        // Evento del botón
        btnGuardar.addActionListener(e -> {
            try {
                int gf = Integer.parseInt(txtGolesLocal.getText());
                int gc = Integer.parseInt(txtGolesVisitante.getText());
                
                equipoLocal.registrarResultado(gf, gc);
                alActualizar.run(); // Refresca el gráfico y puntos
                
                JOptionPane.showMessageDialog(this, "¡Tabla actualizada!");
                txtGolesLocal.setText("");
                txtGolesVisitante.setText("");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Error: Ingresá números enteros.", "Error de entrada", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}