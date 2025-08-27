package datatypes;

public class DtArticuloEspecial extends DtMaterial{

    // Atributos
    private String descripcion;
    private Float peso;
    private DtDimension dimension;
    private String ubicacion;

    // Constructor con parámetros
    public DtArticuloEspecial(String titulo, String autor, String descripcion, Float peso, DtDimension dimension, String ubicacion) {
        super(new DtFecha(), titulo, autor);
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
}
