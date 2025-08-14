package logica;

import datatypes.DtDimension;
import datatypes.DtFecha;
import javax.persistence.*;

@Entity
@Table(name = "articulos_especiales")
@PrimaryKeyJoinColumn(name = "material_id")
public class ArticuloEspecial extends Material {

    // Atributos
    @Column(name = "descripcion", length = 500)
    private String descripcion;
    
    @Column(name = "peso")
    private Float peso;
    
    @Embedded
    private DtDimension dimension;
    
    @Column(name = "ubicacion", length = 100)
    private String ubicacion;

    // Constructor por defecto
    public ArticuloEspecial() {
        super();
    }

    // Constructor con parámetros
    public ArticuloEspecial(String titulo, String autor, String descripcion, Float peso, DtDimension dimension, String ubicacion) {
        super(new DtFecha(1, 1, 2024), titulo, autor);
        this.descripcion = descripcion;
        this.peso = peso;
        this.dimension = new DtDimension(dimension.getLength(), dimension.getWidth(), dimension.getDepth());
        this.ubicacion = ubicacion;
    }

    // Getters
    public String getDescripcion() {
        return this.descripcion;
    }

    public Float getPeso() {
        return this.peso;
    }

    public DtDimension getDimension() {
        return new DtDimension(this.dimension.getLength(), this.dimension.getWidth(), this.dimension.getDepth());
    }
    
    public String getUbicacion() {
        return this.ubicacion;
    }

    // Setters
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setPeso(Float peso) {
        this.peso = peso;
    }

    public void setDimension(DtDimension dimension) {
        this.dimension = new DtDimension(dimension.getLength(), dimension.getWidth(), dimension.getDepth());
    }
    
    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }
}
