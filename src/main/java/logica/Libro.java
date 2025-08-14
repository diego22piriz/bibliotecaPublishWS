package logica;

import datatypes.DtFecha;
import javax.persistence.*;

@Entity
@Table(name = "libros")
@PrimaryKeyJoinColumn(name = "material_id")
public class Libro extends Material {

    // Atributos
    @Column(name = "isbn", unique = true, length = 13)
    private String isbn;
    
    @Column(name = "editorial", length = 100)
    private String editorial;
    
    @Column(name = "anio_publicacion")
    private Integer anioPublicacion;
    
    @Column(name = "cant_paginas")
    private Integer cantPaginas;

    // Constructor por defecto
    public Libro() {
        super();
    }

    // Constructor con parámetros
    public Libro(String titulo, String autor, String isbn, String editorial, Integer anioPublicacion, Integer cantPaginas) {
        super(new DtFecha(1, 1, 2024), titulo, autor);
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

    // Setters
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
    
    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }
    
    public void setAnioPublicacion(Integer anioPublicacion) {
        this.anioPublicacion = anioPublicacion;
    }

    public void setCantPaginas(Integer cantPaginas) {
        this.cantPaginas = cantPaginas;
    }
}
