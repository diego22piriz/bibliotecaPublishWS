package datatypes;

public class DtPrestamo {
    // Clave compuesta (sin ID autogenerado)
    private String lectorCorreo;
    private String bibliotecarioCorreo;
    private Long materialId;
    
    // Fechas y estado
    private DtFecha fechaSolicitud;
    private DtFecha fechaDevolucion;
    private EstadoPrestamo estado;
    
    // Constructor
    public DtPrestamo(String lectorCorreo, String bibliotecarioCorreo, Long materialId, DtFecha fechaSolicitud, DtFecha fechaDevolucion, EstadoPrestamo estado) {
        this.lectorCorreo = lectorCorreo;
        this.bibliotecarioCorreo = bibliotecarioCorreo;
        this.materialId = materialId;
        this.fechaSolicitud = fechaSolicitud;
        this.fechaDevolucion = fechaDevolucion;
        this.estado = estado;
    }

    public DtPrestamo(DtFecha fechaSolicitud, DtFecha fechaEstDev, EstadoPrestamo estado) {
        this.fechaSolicitud = fechaSolicitud;
        this.fechaDevolucion = fechaEstDev;
        this.estado = estado;
    }

    
    // Getters y setters para todos los campos
    public String getLectorCorreo() { return lectorCorreo; }
    public String getBibliotecarioCorreo() { return bibliotecarioCorreo; }
    public Long getMaterialId() { return materialId; }
    public DtFecha getFechaSolicitud() { return fechaSolicitud; }
    public DtFecha getFechaDevolucion() { return fechaDevolucion; }
    public EstadoPrestamo getEstado() { return estado; }
    
    public void setLectorCorreo(String lectorCorreo) { this.lectorCorreo = lectorCorreo; }
    public void setBibliotecarioCorreo(String bibliotecarioCorreo) { this.bibliotecarioCorreo = bibliotecarioCorreo; }
    public void setMaterialId(Long materialId) { this.materialId = materialId; }
    public void setFechaSolicitud(DtFecha fechaSolicitud) { this.fechaSolicitud = fechaSolicitud; }
    public void setFechaDevolucion(DtFecha fechaDevolucion) { this.fechaDevolucion = fechaDevolucion; }
    public void setEstado(EstadoPrestamo estado) { this.estado = estado; }
}
