package logica;

import datatypes.DtFecha;

public class Material {

    // Atributos
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

    // Getters
    public int getId() {
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
    public void setId(int id) {
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
