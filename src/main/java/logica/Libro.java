package logica;

public class Libro extends Material {

    // Atributos
    private String titulo;
    private int cantPaginas;

    // Constructor por defecto
    public Libro() {
        super();
    }

    // Constructor con parámetros
    public Libro(String titulo, int cantPaginas) {
        super();
        this.titulo = titulo;
        this.cantPaginas = cantPaginas;
    }

    // Getters
    public String getTitulo() {
        return this.titulo;
    }

    public int getCantPaginas() {
        return this.cantPaginas;
    }

    // Setters
    public void setCantPaginas(int cantPaginas) {
        this.cantPaginas = cantPaginas;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
}
