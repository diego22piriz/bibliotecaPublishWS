package logica;

import datatypes.DtFecha;
import datatypes.RedBiblioteca;
import logica.Usuario;

public class Lector extends Usuario {
    private String direccion;
    private DtFecha fechaRegistro;
    private boolean activo;
    private RedBiblioteca redBiblioteca;

    public Lector() {
        super();
    }

    public Lector(String nombre, String correo, String direccion, DtFecha fechaRegistro, boolean activo, RedBiblioteca redBiblioteca) {
		super(nombre, correo);
        this.direccion = direccion;
        this.fechaRegistro = fechaRegistro;
        this.activo = activo;
        this.redBiblioteca = redBiblioteca;
	}

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
