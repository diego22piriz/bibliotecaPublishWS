package datatypes;

public class DtLector extends DtUsuario {

    // Atributos
    private String direccion;
    private DtFecha fechaRegistro;
    private boolean activo;
    private RedBiblioteca redBiblioteca;

    // Constructor con parámetros
    public DtLector(String nombre, String correo, String password, String direccion, DtFecha fechaRegistro, boolean activo, RedBiblioteca redBiblioteca) {
		super(nombre, correo, password);
        this.direccion = direccion;
        this.fechaRegistro = fechaRegistro;
        this.activo = activo;
        this.redBiblioteca = redBiblioteca;
	}

    // Getters
    public String getDireccion() {
        return this.direccion;
    }

    public DtFecha getFechaRegistro() {
        return this.fechaRegistro;
    }
    
    public boolean getActivo() {
        return this.activo;
    }

    public RedBiblioteca getRedBiblioteca() {
        return this.redBiblioteca;
    }
}
