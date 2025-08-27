package datatypes;

import logica.Lector;
import logica.Material;

public class DtPrestamo {

    // Atributos
    private Long id;
    private DtFecha fechaSolicitud;
    private DtFecha fechaEstDev;
    private EstadoPrestamo estado;
    private Lector lector;
    private Material material;

    // Constructor con parámetros
    public DtPrestamo(DtFecha fechaSolicitud, DtFecha fechaEstDev, EstadoPrestamo estado) {
        this.fechaSolicitud = fechaSolicitud;
        this.fechaEstDev = fechaEstDev;
        this.estado = estado;
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
}
