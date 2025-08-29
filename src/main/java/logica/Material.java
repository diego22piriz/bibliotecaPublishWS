package logica;

import datatypes.DtFecha;
import javax.persistence.*;

@Entity
@Table(name = "materiales")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Material {

    // Atributos según el diagrama UML
    @Id
    @Column(name = "id", nullable = false, length = 100)
    private String id;
    
    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "day", column = @Column(name = "fecha_ingreso_dia")),
        @AttributeOverride(name = "month", column = @Column(name = "fecha_ingreso_mes")),
        @AttributeOverride(name = "year", column = @Column(name = "fecha_ingreso_anio"))
    })
    private DtFecha fechaIngreso;

    // Constructor por defecto requerido por JPA
    protected Material() {
        super();
    }

    // Constructor con parámetros
    protected Material(String id, DtFecha fechaIngreso) {
        this.id = id;
        this.fechaIngreso = fechaIngreso;
    }

    // Getters
    public String getId() {
        return this.id;
    }

    public DtFecha getFechaIngreso() {
        return this.fechaIngreso;
    }

    // Setters
    public void setId(String id) {
        this.id = id;
    }

    public void setFechaIngreso(DtFecha fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }
}
