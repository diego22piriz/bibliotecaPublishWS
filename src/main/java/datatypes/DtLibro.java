package datatypes;

public class DtLibro extends DtMaterial {

    // Atributos
    private String isbn;
    private String editorial;
    private Integer anioPublicacion;
    private Integer cantPaginas;

    // Constructor con parámetros
    public DtLibro(String titulo, String autor, String isbn, String editorial, Integer anioPublicacion, Integer cantPaginas) {
        super(new DtFecha(), titulo, autor);
        this.isbn = isbn;
        this.editorial = editorial;
        this.anioPublicacion = anioPublicacion;
        this.cantPaginas = cantPaginas;
    }

    // Getters
    public String getIsbn() {
        return this.isbn;
    }
    
    public String getEditorial() {
        return this.editorial;
    }
    
    public Integer getAnioPublicacion() {
        return this.anioPublicacion;
    }

    public Integer getCantPaginas() {
        return this.cantPaginas;
    }
}
