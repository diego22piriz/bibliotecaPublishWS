package datatypes;

public class DtMaterial {

    // Atributos
    private Long id;
    private DtFecha fechaIngreso;
    private String titulo;
    private String autor;
    private String estado;

    // Constructor con parámetros
    public DtMaterial(DtFecha fechaIngreso, String titulo, String autor) {
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
}
