package datatypes;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "dtArticulo")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"descripcion", "pesoKg", "dimensiones"})
public class DtArticulo extends DtMaterial {

    // Atributos específicos de Articulo según el diagrama UML
    @XmlElement(name = "descripcion")
    private String descripcion;
    
    @XmlElement(name = "pesoKg")
    private float pesoKg;
    
    @XmlElement(name = "dimensiones")
    private String dimensiones;

    // Constructor sin parámetros (requerido por JAXB)
    public DtArticulo() {
    }

    // Constructor con parámetros (siguiendo el patrón de DtLector)
    public DtArticulo(String id, DtFecha fechaIngreso, String descripcion, float pesoKg, String dimensiones) {
        super(fechaIngreso); // Llama al constructor de DtMaterial con id y fechaIngreso
        this.descripcion = descripcion;
        this.pesoKg = pesoKg;
        this.dimensiones = dimensiones;
    }

    // Constructor con parámetros (sin ID)
    public DtArticulo(DtFecha fechaIngreso, String descripcion, float pesoKg, String dimensiones) {
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
