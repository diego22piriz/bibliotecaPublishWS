package logica;

import javax.persistence.*;

@Entity
@Table(name = "usuarios")
@Inheritance(strategy = InheritanceType.JOINED)
public class Usuario {
    
    @Id
    @Column(name = "correo", nullable = false, length = 100)
    private String correo;
    
    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

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
    public String getCorreo() {
        return correo;
    }
    
    public String getNombre() {
        return nombre;
    }

    // Setters
    public void setCorreo(String correo) {
        this.correo = correo;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}