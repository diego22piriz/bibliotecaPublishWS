package logica;

import datatypes.DtFecha;
import javax.persistence.*;

@Entity
@Table(name = "materiales")
@Inheritance(strategy = InheritanceType.JOINED)
public class Material {

    // Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "day", column = @Column(name = "fecha_ingreso_dia")),
        @AttributeOverride(name = "month", column = @Column(name = "fecha_ingreso_mes")),
        @AttributeOverride(name = "year", column = @Column(name = "fecha_ingreso_anio"))
    })
    private DtFecha fechaIngreso;
    
    @Column(name = "titulo", nullable = false, length = 200)
    private String titulo;
    
    @Column(name = "autor", nullable = false, length = 100)
    private String autor;
    
    @Column(name = "estado", nullable = false, length = 20)
    private String estado;

    // Constructor por defecto
    public Material() {
        super();
    }

    // Constructor con parámetros
    public Material(DtFecha fechaIngreso, String titulo, String autor) {
        this.fechaIngreso = fechaIngreso;
        this.titulo = titulo;
        this.autor = autor;
        this.estado = "DISPONIBLE";
    }

    // Getters
    public Long getId() {
        return this.id;
    }

    public DtFecha getFechaIngreso() {
        return this.fechaIngreso;
    }

    public String getTitulo() {
        return this.titulo;
    }

    public String getAutor() {
        return this.autor;
    }

    public String getEstado() {
        return this.estado;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setFechaIngreso(DtFecha fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
