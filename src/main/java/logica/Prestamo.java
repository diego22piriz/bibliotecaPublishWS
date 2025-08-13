package logica;

import datatypes.DtFecha;
import datatypes.EstadoPrestamo;

public class Prestamo {

    // Atributos
    private DtFecha fechaSolicitud;
    private DtFecha fechaEstDev;
    private EstadoPrestamo estado;

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

    // Getters
    public DtFecha getFechaSolicitud() {
        return this.fechaSolicitud;
    }

    public DtFecha getFechaEstDev() {
        return this.fechaEstDev;
    }

    public EstadoPrestamo getEstado() {
        return this.estado;
    }

    // Setters
    public void setFechaEstDev(DtFecha fechaEstDev) {
        this.fechaEstDev = fechaEstDev;
    }


    public void setFechaSolicitud(DtFecha fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    public void setEstado(EstadoPrestamo estado) {
        this.estado = estado;
    }

}