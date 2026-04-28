import javax.swing.*;
import java.awt.*;

public class PanelEquipo extends JPanel {
    private JTextField txtFavor, txtContra;
    private Equipo equipo;

    public PanelEquipo(String nombreEquipo) {
        this.equipo = new Equipo(nombreEquipo);
        setLayout(new GridLayout(3, 2, 5, 5));
        setBorder(BorderFactory.createTitledBorder("Datos de " + nombreEquipo));

        add(new JLabel("Goles Favor:"));
        txtFavor = new JTextField("0");
        add(txtFavor);

        add(new JLabel("Goles Contra:"));
        txtContra = new JTextField("0");
        add(txtContra);

        JButton btnCargar = new JButton("Cargar");
        btnCargar.addActionListener(e -> {
            try {
                int f = Integer.parseInt(txtFavor.getText());
                int c = Integer.parseInt(txtContra.getText());
                equipo.registrarResultado(f, c);
                JOptionPane.showMessageDialog(this, "Resultado cargado para " + nombreEquipo);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Error: Ingrese números válidos.");
            }
        });
        add(btnCargar);
    }

    public Equipo getEquipo() {
        return equipo;
    }
}