package datatypes;

public class DtMaterial {

    // Atributos
    private String id;
    private DtFecha fechaIngreso;

    // Constructor con parámetros
    public DtMaterial(DtFecha fechaIngreso, String id) {
        super();
        this.fechaIngreso = fechaIngreso;
        this.id = id;
    }

    // Getters
    public String getId() {
        return this.id;
    }

    public DtFecha getFechaIngreso() {
        return this.fechaIngreso;
    }

}
