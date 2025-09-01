package datatypes;

public class DtLibro extends DtMaterial {

    // Atributos específicos de Libro según el diagrama UML
    private String titulo;
    private String cantidadPaginas;

    // Constructor con parámetros (siguiendo el patrón de DtArticulo)
    public DtLibro(String id, DtFecha fechaIngreso, String titulo, String cantidadPaginas) {
        super(id, fechaIngreso); // Llama al constructor de DtMaterial con id y fechaIngreso
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
}
