package datatypes;

public class DtMaterial {

    // Atributos
    private String id;
    private DtFecha fechaIngreso;

    // Constructor con parámetros (sin ID)
    public DtMaterial(DtFecha fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    // Getters
    public String getId() {
        return this.id;
    }

    public DtFecha getFechaIngreso() {
        return this.fechaIngreso;
    }

}
