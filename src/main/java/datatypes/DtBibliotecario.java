package datatypes;

public class DtBibliotecario extends DtUsuario {

    private Integer numeroEmpleado;

    public DtBibliotecario(String nombre, String correo, Integer numeroEmpleado) {
        super(nombre, correo);
        this.numeroEmpleado = numeroEmpleado;
    }

    // Getter
    public Integer getNumeroEmpleado() { 
        return numeroEmpleado; 
    }
}
