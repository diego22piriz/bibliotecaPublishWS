package logica;

public class Usuario {

    // Atributos
	private String nombre;
	private String correo;

    // Constructor por defecto
    public Usuario() {
		super();
	}

    // Constructor con parámetros
    public Usuario(String nombre, String correo) {
		super();
        this.nombre = nombre;
        this.correo = correo;
	}

    // Getters
    public String getNombre() {
        return nombre;
    }

    public String getCorreo() {
        return correo;
    }

    // Setters
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
}