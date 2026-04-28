public class Equipo {
    private String nombre;
    private int gf; // Goles a favor
    private int gc; // Goles en contra
    private int puntos;

    public Equipo(String nombre) {
        this.nombre = nombre;
        this.gf = 0;
        this.gc = 0;
        this.puntos = 0;
    }

    // Mantenemos los dos nombres por si las moscas
    public void registrarResultado(int favor, int contra) {
        actualizar(favor, contra);
    }

    public void registrarPartido(int favor, int contra) {
        actualizar(favor, contra);
    }

    private void actualizar(int favor, int contra) {
        this.gf += favor;
        this.gc += contra;
        if (favor > contra) {
            this.puntos += 3;
        } else if (favor == contra) {
            this.puntos += 1;
        }
    }

    // Getters para que la Tabla y el Gráfico funcionen
    public String getNombre() { return nombre; }
    public int getPuntos() { return puntos; }
    public int getGolesFavor() { return gf; }
    public int getGolesContra() { return gc; }
    public int getDif() { return gf - gc; }
    public int getDiferenciaGol() { return gf - gc; }
}