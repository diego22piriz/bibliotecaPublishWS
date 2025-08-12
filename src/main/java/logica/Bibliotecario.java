import logica.Usuario;

public class Bibliotecario extends Usuario {
    private int numEmpleado;

    public Bibliotecario() {
        super();
    }

    public Bibliotecario(String nombre, String correo, int numEmpleado) {
		super(nombre, correo);
        this.numEmpleado = numEmpleado;
	}

    public int getNumEmpleado() {
        return this.numEmpleado;
    }

    public void setNumEmpleado(int numEmpleado) {
        this.numEmpleado = numEmpleado;
    }
}
