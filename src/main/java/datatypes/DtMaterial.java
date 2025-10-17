package datatypes;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

@XmlRootElement(name = "dtMaterial")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlSeeAlso({DtLibro.class, DtArticulo.class})
public class DtMaterial {

    // Atributos
    @XmlElement(name = "id")
    private String id;
    
    @XmlElement(name = "fechaIngreso")
    private DtFecha fechaIngreso;

    // Constructor sin parámetros (requerido por JAXB)
    public DtMaterial() {
    }

    // Constructor con parámetros (sin ID)
    public DtMaterial(DtFecha fechaIngreso) {
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
