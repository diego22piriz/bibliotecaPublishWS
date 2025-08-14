package logica;

import datatypes.RedBiblioteca;
import datatypes.DtFecha;
import javax.persistence.*;

@Entity
@Table(name = "lectores")
@PrimaryKeyJoinColumn(name = "usuario_id")
public class Lector extends Usuario {

    // Atributos
    @Column(name = "direccion", nullable = false, length = 200)
    private String direccion;
    
    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "day", column = @Column(name = "fecha_registro_dia")),
        @AttributeOverride(name = "month", column = @Column(name = "fecha_registro_mes")),
        @AttributeOverride(name = "year", column = @Column(name = "fecha_registro_anio"))
    })
    private DtFecha fechaRegistro;
    
    @Column(name = "activo", nullable = false)
    private boolean activo;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "red_biblioteca", nullable = false)
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
