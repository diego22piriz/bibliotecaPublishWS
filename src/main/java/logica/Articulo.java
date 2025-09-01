package logica;

import datatypes.DtFecha;
import javax.persistence.*;

@Entity
@Table(name = "articulos")
public class Articulo extends Material {
    
    // Atributos específicos de Articulo según el diagrama UML
    @Column(name = "descripcion", nullable = false, length = 500)
    private String descripcion;
    
    @Column(name = "peso_kg", nullable = false)
    private float pesoKg;
    
    @Column(name = "dimensiones", nullable = false, length = 100)
    private String dimensiones;

    // Constructor por defecto requerido por JPA
    public Articulo() {
        super();
    }

    // Constructor con parámetros (sin ID)
    public Articulo(DtFecha fechaIngreso, String descripcion, float pesoKg, String dimensiones) {
        super(fechaIngreso);
        this.descripcion = descripcion;
        this.pesoKg = pesoKg;
        this.dimensiones = dimensiones;
    }

    // Getters
    public String getDescripcion() {
        return this.descripcion;
    }

    public float getPesoKg() {
        return this.pesoKg;
    }

    public String getDimensiones() {
        return this.dimensiones;
    }

    // Setters
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setPesoKg(float pesoKg) {
        this.pesoKg = pesoKg;
    }

    public void setDimensiones(String dimensiones) {
        this.dimensiones = dimensiones;
    }
}
