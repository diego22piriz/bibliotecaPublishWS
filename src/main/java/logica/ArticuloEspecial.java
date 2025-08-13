package logica;

import datatypes.DtDimension;

public class ArticuloEspecial extends Material {
    private String descripcion;
    private float peso;
    private DtDimension dimension;

    public ArticuloEspecial() {
        super();
    }

    public ArticuloEspecial(String descripcion, float peso, DtDimension dimension) {
        super();
        this.descripcion = descripcion;
        this.peso = peso;
        this.dimension = new DtDimension(dimension.getLength(), dimension.getWidth(), dimension.getDepth());
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public float getPeso() {
        return this.peso;
    }

    public DtDimension getDimension() {
        return new DtDimension(this.dimension.getLength(), this.dimension.getWidth(), this.dimension.getDepth());
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public void setDimension(DtDimension dimension) {
        this.dimension = new DtDimension(dimension.getLength(), dimension.getWidth(), dimension.getDepth());
    }
}
