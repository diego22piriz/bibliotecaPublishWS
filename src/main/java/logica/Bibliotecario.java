package logica;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "bibliotecario", cascade = CascadeType.ALL)
    private List<Prestamo> prestamos = new ArrayList<>();

    public List<Prestamo> getPrestamos() {
        return this.prestamos;
    }

    public void addPrestamo(Prestamo prestamo) {
        this.prestamos.add(prestamo);
        prestamo.setBibliotecario(this);
    }

    public void removePrestamo(Prestamo prestamo) {
        this.prestamos.remove(prestamo);
        prestamo.setBibliotecario(null);
    }

    public Integer getNumeroEmpleado() { 
        return numeroEmpleado; 
    }
    
    public void setNumeroEmpleado(Integer numeroEmpleado) { 
        this.numeroEmpleado = numeroEmpleado; 
    }
}