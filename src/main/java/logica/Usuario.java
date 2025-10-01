package logica;

import javax.persistence.*;

@Entity
@Table(name = "usuarios")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Usuario {
    
    @Id
    @Column(name = "correo", nullable = false, length = 100)
    private String correo;
    
    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "password", nullable = false, length = 100)
    private String password;

    // Constructor por defecto requerido por JPA
    protected Usuario() {
        super();
    }

    // Constructor con parámetros
    protected Usuario(String nombre, String correo, String password) {
        super();
        this.nombre = nombre;
        this.correo = correo;
        this.password = password;
    }

    // Getters
    public String getCorreo() {
        return correo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getPassword() {
        return password;
    }

    // Setters
    public void setCorreo(String correo) {
        this.correo = correo;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String tipoUsuario() {
        return "Usuario";
    }
}