package src.Negocio;

public class TVideojuego {

    private int id;
    private String nombre;
    private String descripcion;
    private String consola;

    public TVideojuego() {
    }

    public TVideojuego(String nombre, String descripcion, String consola) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.consola = consola;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getConsola() {
        return consola;
    }

    public void setConsola(String consola) {
        this.consola = consola;
    }
}
