package datatypes;

// DT para el login de usuarios (bibliotecarios y lectores)
public class DtLogin {

    // Atributos
    private String correo;
    private String password;

    // Constructor con parámetros
    public DtLogin(String correo, String password) {
		super();
        this.correo = correo;
        this.password = password;
	}

    // Getters
    public String getCorreo() {
        return this.correo;
    }

    public String getPassword() {
        return this.password;
    }

}


