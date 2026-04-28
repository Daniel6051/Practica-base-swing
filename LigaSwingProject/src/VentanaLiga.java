import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;

public class VentanaLiga extends JFrame {
    private ArrayList<Equipo> listaEquipos = new ArrayList<>();
    private DefaultTableModel modeloTabla;
    private JTable tabla;

    public VentanaLiga() {
        super("Panel de Control - Liga Profesional");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // --- PANEL SUPERIOR: CARGA (COLORES) ---
        JPanel panelCarga = new JPanel();
        panelCarga.setBackground(new Color(44, 62, 80)); // Gris oscuro
        panelCarga.setLayout(new FlowLayout());

        JTextField txtNombre = new JTextField(10);
        JButton btnAgregar = new JButton("Agregar Equipo");
        btnAgregar.setBackground(new Color(46, 204, 113)); // Verde
        btnAgregar.setForeground(Color.WHITE);

        panelCarga.add(new JLabel("<html><b style='color:white'>Nombre:</b></html>"));
        panelCarga.add(txtNombre);
        panelCarga.add(btnAgregar);
        add(panelCarga, BorderLayout.NORTH);

        // --- TABLA DE POSICIONES ---
        String[] columnas = {"Posición", "Equipo", "Pts", "GF", "GC", "Dif"};
        modeloTabla = new DefaultTableModel(columnas, 0);
        tabla = new JTable(modeloTabla);
        
        // Estilo de la tabla
        tabla.setRowHeight(30);
        tabla.setFont(new Font("Arial", Font.PLAIN, 14));
        tabla.getTableHeader().setBackground(new Color(52, 152, 219)); // Azul
        tabla.getTableHeader().setForeground(Color.WHITE);

        add(new JScrollPane(tabla), BorderLayout.CENTER);

        // --- LÓGICA DE AGREGAR ---
        btnAgregar.addActionListener(e -> {
            String nom = txtNombre.getText();
            if (!nom.isEmpty()) {
                listaEquipos.add(new Equipo(nom));
                actualizarTabla();
                txtNombre.setText("");
            }
        });

        // --- BOTÓN PARA CARGAR RESULTADO ---
        JButton btnPartido = new JButton("Registrar Resultado de Partido");
        btnPartido.setBackground(new Color(231, 76, 60)); // Rojo
        btnPartido.setForeground(Color.WHITE);
        add(btnPartido, BorderLayout.SOUTH);

        btnPartido.addActionListener(e -> cargarPartido());
    }

    private void cargarPartido() {
        if (listaEquipos.size() < 1) return;
        
        String[] nombres = listaEquipos.stream().map(Equipo::getNombre).toArray(String[]::new);
        String sel = (String) JOptionPane.showInputDialog(this, "Seleccione equipo:", "Cargar Goles", 
                                JOptionPane.QUESTION_MESSAGE, null, nombres, nombres[0]);

        if (sel != null) {
            int gf = Integer.parseInt(JOptionPane.showInputDialog("Goles a favor:"));
            int gc = Integer.parseInt(JOptionPane.showInputDialog("Goles en contra:"));
            
            for (Equipo eq : listaEquipos) {
                if (eq.getNombre().equals(sel)) eq.registrarPartido(gf, gc);
            }
            actualizarTabla();
        }
    }

    private void actualizarTabla() {
        // Ordenar por puntos, luego por Diferencia de Gol
        listaEquipos.sort((e1, e2) -> {
            if (e2.getPuntos() != e1.getPuntos()) 
                return e2.getPuntos() - e1.getPuntos();
            return e2.getDif() - e1.getDif();
        });

        modeloTabla.setRowCount(0);
        for (int i = 0; i < listaEquipos.size(); i++) {
            Equipo eq = listaEquipos.get(i);
            modeloTabla.addRow(new Object[]{
                (i + 1) + "°", eq.getNombre(), eq.getPuntos(), eq.getGolesFavor(), eq.getGolesContra(), eq.getDif()
            });
        }
    }
}