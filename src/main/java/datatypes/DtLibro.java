package datatypes;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "dtLibro")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"titulo", "cantidadPaginas"})
public class DtLibro extends DtMaterial {

    // Atributos específicos de Libro según el diagrama UML
    @XmlElement(name = "titulo")
    private String titulo;
    
    @XmlElement(name = "cantidadPaginas")
    private String cantidadPaginas;

    // Constructor sin parámetros (requerido por JAXB)
    public DtLibro() {
    }

    // Constructor con parámetros (siguiendo el patrón de DtArticulo)
    public DtLibro(String id, DtFecha fechaIngreso, String titulo, String cantidadPaginas) {
        super(fechaIngreso); // Llama al constructor de DtMaterial con id y fechaIngreso
        this.titulo = titulo;
        this.cantidadPaginas = cantidadPaginas;
    }

    // Constructor con parámetros (sin ID)
    public DtLibro(DtFecha fechaIngreso, String titulo, String cantidadPaginas) {
        super(fechaIngreso);
        this.titulo = titulo;
        this.cantidadPaginas = cantidadPaginas;
    }

    // Getters
    public String getTitulo() {
        return this.titulo;
    }
    
    public String getCantidadPaginas() {
        return this.cantidadPaginas;
    }
    
    // Setters
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    
    public void setCantidadPaginas(String cantidadPaginas) {
        this.cantidadPaginas = cantidadPaginas;
    }
}
