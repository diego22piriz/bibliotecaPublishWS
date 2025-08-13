package logica;

import datatypes.DtFecha;
import javax.persistence.*;

@Entity
@Table(name = "bibliotecarios")
@PrimaryKeyJoinColumn(name = "usuario_id")
public class Bibliotecario extends Usuario {

    // Atributos
    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "day", column = @Column(name = "fecha_contratacion_dia")),
        @AttributeOverride(name = "month", column = @Column(name = "fecha_contratacion_mes")),
        @AttributeOverride(name = "year", column = @Column(name = "fecha_contratacion_anio"))
    })
    private DtFecha fechaContratacion;
    
    @Column(name = "especialidad", length = 100)
    private String especialidad;

    // Constructor por defecto
    public Bibliotecario() {
        super();
    }

    // Constructor con parámetros
    public Bibliotecario(String nombre, String correo, DtFecha fechaContratacion, String especialidad) {
		super(nombre, correo);
        this.fechaContratacion = fechaContratacion;
        this.especialidad = especialidad;
	}

    // Getters
    public DtFecha getFechaContratacion() {
        return this.fechaContratacion;
    }
    
    public String getEspecialidad() {
        return this.especialidad;
    }

    // Setters
    public void setFechaContratacion(DtFecha fechaContratacion) {
        this.fechaContratacion = fechaContratacion;
    }
    
    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }
}
