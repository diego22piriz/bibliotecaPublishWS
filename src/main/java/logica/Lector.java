package logica;

import datatypes.DtFecha;
import datatypes.RedBiblioteca;
import logica.Usuario;

public class Lector extends Usuario {

    // Atributos
    private String direccion;
    private DtFecha fechaRegistro;
    private boolean activo;
    private RedBiblioteca redBiblioteca;

    // Constructor por defecto
    public Lector() {
        super();
    }

    // Constructor con parámetros
    public Lector(String nombre, String correo, String direccion, DtFecha fechaRegistro, boolean activo, RedBiblioteca redBiblioteca) {
		super(nombre, correo);
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

    // Setters
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setFechaRegistro(DtFecha fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
    
    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public void setRedBiblioteca(RedBiblioteca redBiblioteca) {
        this.redBiblioteca = redBiblioteca;
    }
}
