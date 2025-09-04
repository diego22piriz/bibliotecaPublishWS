package logica;

import datatypes.EstadoPrestamo;
import datatypes.DtFecha;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "prestamos")
public class Prestamo implements Serializable {

    // Clave compuesta con múltiples @Id
    @Id
    @Column(name = "lector_correo")
    private String lectorCorreo;
    
    @Id
    @Column(name = "bibliotecario_correo")
    private String bibliotecarioCorreo;
    
    @Id
    @Column(name = "material_id")
    private Long materialId;
    
    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "day", column = @Column(name = "fecha_solicitud_dia")),
        @AttributeOverride(name = "month", column = @Column(name = "fecha_solicitud_mes")),
        @AttributeOverride(name = "year", column = @Column(name = "fecha_solicitud_anio"))
    })
    private DtFecha fechaSolicitud;
    
    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "day", column = @Column(name = "fecha_est_dev_dia")),
        @AttributeOverride(name = "month", column = @Column(name = "fecha_est_dev_mes")),
        @AttributeOverride(name = "year", column = @Column(name = "fecha_est_dev_anio"))
    })
    private DtFecha fechaEstDev;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false)
    private EstadoPrestamo estado;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lector_correo", nullable = false, insertable = false, updatable = false)
    private Lector lector;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bibliotecario_correo", nullable = false, insertable = false, updatable = false)
    private Bibliotecario bibliotecario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "material_id", nullable = false, insertable = false, updatable = false)
    private Material material;

    // Constructor por defecto
    public Prestamo() {
        super();
    }

    // Constructor con parámetros
    public Prestamo(DtFecha fechaSolicitud, DtFecha fechaEstDev, EstadoPrestamo estado) {
        this.fechaSolicitud = fechaSolicitud;
        this.fechaEstDev = fechaEstDev;
        this.estado = estado;
    }

    public Prestamo(Material material, Lector usuario, Bibliotecario bibliotecario, DtFecha fechaInicio, DtFecha fechaFin) {
        this.material = material;
        this.lector = usuario;
        this.bibliotecario = bibliotecario;
        this.fechaSolicitud = fechaInicio;
        this.fechaEstDev = fechaFin;
        this.estado = EstadoPrestamo.PENDIENTE;
        
        // Asignar la clave compuesta (me parece medio al pedo)
        this.lectorCorreo = usuario.getCorreo();
        this.bibliotecarioCorreo = bibliotecario.getCorreo();
        this.materialId = material.getId();
    }

    // Getters para la clave compuesta
    public String getLectorCorreo() {
        return lectorCorreo;
    }
    
    public String getBibliotecarioCorreo() {
        return bibliotecarioCorreo;
    }
    
    public Long getMaterialId() {
        return materialId;
    }
    
    public DtFecha getFechaSolicitud() {
        return this.fechaSolicitud;
    }

    public DtFecha getFechaEstDev() {
        return this.fechaEstDev;
    }

    public EstadoPrestamo getEstado() {
        return this.estado;
    }
    
    public Lector getLector() {
        return this.lector;
    }
    
    public Material getMaterial() {
        return this.material;
    }

    // Setters para la clave compuesta
    public void setLectorCorreo(String lectorCorreo) {
        this.lectorCorreo = lectorCorreo;
    }
    
    public void setBibliotecarioCorreo(String bibliotecarioCorreo) {
        this.bibliotecarioCorreo = bibliotecarioCorreo;
    }
    
    public void setMaterialId(Long materialId) {
        this.materialId = materialId;
    }
    
    public void setFechaEstDev(DtFecha fechaEstDev) {
        this.fechaEstDev = fechaEstDev;
    }

    public void setFechaSolicitud(DtFecha fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    public void setEstado(EstadoPrestamo estado) {
        this.estado = estado;
    }
    
    public void setLector(Lector lector) {
        this.lector = lector;
    }
    
    public void setMaterial(Material material) {
        this.material = material;
    }

    public void setBibliotecario(Bibliotecario bibliotecario) {
        this.bibliotecario = bibliotecario;
    }
    
    // equals y hashCode son obligatorios para claves compuestas
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Prestamo prestamo = (Prestamo) o;
        return Objects.equals(lectorCorreo, prestamo.lectorCorreo) &&
               Objects.equals(bibliotecarioCorreo, prestamo.bibliotecarioCorreo) &&
               Objects.equals(materialId, prestamo.materialId);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(lectorCorreo, bibliotecarioCorreo, materialId);
    }
}