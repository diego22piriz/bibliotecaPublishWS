package logica;

import datatypes.DtFecha;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "material", cascade = CascadeType.ALL)
    private List<Prestamo> prestamos = new ArrayList<>();

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

    public List<Prestamo> getPrestamos() {
        return this.prestamos;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setFechaIngreso(DtFecha fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public void addPrestamo(Prestamo prestamo) {
        this.prestamos.add(prestamo);
        prestamo.setMaterial(this);
    }

    public void removePrestamo(Prestamo prestamo) {
        this.prestamos.remove(prestamo);
        prestamo.setMaterial(null);
    }
}
