package logica;

import datatypes.DtFecha;
import javax.persistence.*;

@Entity
@Table(name = "libros")
public class Libro extends Material {
    
    // Atributos específicos de Libro según el diagrama UML
    @Column(name = "titulo", nullable = false, length = 200)
    private String titulo;
    
    @Column(name = "cantidad_paginas", nullable = false, length = 10)
    private String cantidadPaginas;

    // Constructor por defecto requerido por JPA
    public Libro() {
        super();
    }

    // Constructor con parámetros
    public Libro(String id, DtFecha fechaIngreso, String titulo, String cantidadPaginas) {
        super(id, fechaIngreso);
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

    // Setters
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setCantidadPaginas(String cantidadPaginas) {
        this.cantidadPaginas = cantidadPaginas;
    }
}
