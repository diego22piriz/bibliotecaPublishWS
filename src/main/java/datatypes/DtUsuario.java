package datatypes;

public abstract class DtUsuario {

    private String correo;
    private String nombre;
    private String password;

    // Constructor con parámetros
    protected DtUsuario(String nombre, String correo, String password) {
        super();
        this.nombre = nombre;
        this.correo = correo;
        this.password = password;
    }

    // Getters
    public String getCorreo() {
        return correo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getPassword() {
        return password;
    }
}
