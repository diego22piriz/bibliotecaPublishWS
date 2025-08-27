package logica;

import javax.persistence.*;

@Entity
@Table(name = "bibliotecarios")
@PrimaryKeyJoinColumn(name = "usuario_id")
public class Bibliotecario extends Usuario {

    // Autogenerado por la BD (serial) y cargado al insertar
    @org.hibernate.annotations.Generated(org.hibernate.annotations.GenerationTime.INSERT)
    @Column(name = "numero_empleado", nullable = false, unique = true, columnDefinition = "serial", insertable = true, updatable = false)
    private Integer numeroEmpleado;

    public Bibliotecario() { 
        super(); 
    }

    public Bibliotecario(String nombre, String correo) {
        super(nombre, correo);
    }

    public Integer getNumeroEmpleado() { 
        return numeroEmpleado; 
    }
    
    public void setNumeroEmpleado(Integer numeroEmpleado) { 
        this.numeroEmpleado = numeroEmpleado; 
    }
}