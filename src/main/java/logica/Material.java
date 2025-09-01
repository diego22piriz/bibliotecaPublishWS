package logica;

import datatypes.DtFecha;
import javax.persistence.*;

@Entity
@Table(name = "materiales")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Material {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    @Embedded
    private DtFecha fechaIngreso;

    // Constructor por defecto requerido por JPA
    protected Material() {
        super();
    }

    // Constructor con parámetros (sin ID)
    protected Material(DtFecha fechaIngreso) {
        super();
        this.fechaIngreso = fechaIngreso;
    }

    // Getters
    public Long getId() {
        return this.id;
    }

    public DtFecha getFechaIngreso() {
        return this.fechaIngreso;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setFechaIngreso(DtFecha fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }
}
