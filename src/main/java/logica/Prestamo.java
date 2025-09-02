package logica;

import datatypes.EstadoPrestamo;
import datatypes.DtFecha;
import javax.persistence.*;

@Entity
@Table(name = "prestamos")
public class Prestamo {

    // Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
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
    @JoinColumn(name = "lector_id", nullable = false)
    private Lector lector;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bibliotecario_id", nullable = false)
    private Bibliotecario bibliotecario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "material_id", nullable = false)
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
    }

    // Getters
    public Long getId() {
        return id;
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

    // Setters
    public void setId(Long id) {
        this.id = id;
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
}