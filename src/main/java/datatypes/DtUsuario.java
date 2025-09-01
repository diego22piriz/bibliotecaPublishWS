package datatypes;

public abstract class DtUsuario {

    private String correo;
    private String nombre;

    // Constructor con parámetros
    protected DtUsuario(String nombre, String correo) {
        super();
        this.nombre = nombre;
        this.correo = correo;
    }

    // Getters
    public String getCorreo() {
        return correo;
    }

    public String getNombre() {
        return nombre;
    }
}