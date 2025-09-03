package datatypes;

public class DtArticulo extends DtMaterial {

    // Atributos específicos de Articulo según el diagrama UML
    private String descripcion;
    private float pesoKg;
    private String dimensiones;

    // Constructor con parámetros (siguiendo el patrón de DtLector)
    public DtArticulo(String id, DtFecha fechaIngreso, String descripcion, float pesoKg, String dimensiones) {
        super(fechaIngreso); // Llama al constructor de DtMaterial con id y fechaIngreso
        this.descripcion = descripcion;
        this.pesoKg = pesoKg;
        this.dimensiones = dimensiones;
    }

    // Constructor con parámetros (sin ID)
    public DtArticulo(DtFecha fechaIngreso, String descripcion, float pesoKg, String dimensiones) {
        super(fechaIngreso);
        this.descripcion = descripcion;
        this.pesoKg = pesoKg;
        this.dimensiones = dimensiones;
    }

    // Getters
    public String getDescripcion() {
        return this.descripcion;
    }
    
    public float getPesoKg() {
        return this.pesoKg;
    }
    
    public String getDimensiones() {
        return this.dimensiones;
    }
}
