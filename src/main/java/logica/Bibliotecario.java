package logica;

import logica.Usuario;

public class Bibliotecario extends Usuario {

    // Atributos
    private int numEmpleado;

    // Constructor por defecto
    public Bibliotecario() {
        super();
    }

    // Constructor con parámetros
    public Bibliotecario(String nombre, String correo, int numEmpleado) {
		super(nombre, correo);
        this.numEmpleado = numEmpleado;
	}

    // Getters
    public int getNumEmpleado() {
        return this.numEmpleado;
    }

    // Setters
    public void setNumEmpleado(int numEmpleado) {
        this.numEmpleado = numEmpleado;
    }
}
