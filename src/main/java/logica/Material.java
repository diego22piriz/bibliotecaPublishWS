package logica;

import datatypes.DtFecha;

public class Material {
    private int id;
    private DtFecha fechaIngreso;
    private String titulo;
    private String autor;
    private String estado;

    // Constructor por defecto
    public Material() {
        super();
    }

    // Constructor con parámetros
    public Material(int id, DtFecha fechaIngreso, String titulo, String autor) {
        this.id = id;
        this.fechaIngreso = fechaIngreso;
        this.titulo = titulo;
        this.autor = autor;
        this.estado = "DISPONIBLE";
    }

    // Getters y Setters
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public DtFecha getFechaIngreso() {
        return this.fechaIngreso;
    }

    public void setFechaIngreso(DtFecha fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public String getTitulo() {
        return this.titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return this.autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getEstado() {
        return this.estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
