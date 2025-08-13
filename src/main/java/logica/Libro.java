package logica;

public class Libro extends Material {
    private String titulo;
    private int cantPaginas;

    public Libro() {
        super();
    }

    public Libro(String titulo, int cantPaginas) {
        super();
        this.titulo = titulo;
        this.cantPaginas = cantPaginas;
    }

    public String getTitulo() {
        return this.titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getCantPaginas() {
        return this.cantPaginas;
    }

    public void setCantPaginas(int cantPaginas) {
        this.cantPaginas = cantPaginas;
    }
}
