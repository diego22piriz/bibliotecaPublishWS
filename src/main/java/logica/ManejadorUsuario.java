package logica;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import persistencia.Conexion;
import datatypes.DtFecha;
import datatypes.RedBiblioteca;

public class ManejadorUsuario {
    
    private static ManejadorUsuario instancia = null;
    
    private ManejadorUsuario() {}
    
    public static ManejadorUsuario getInstancia() {
        if (instancia == null) {
            instancia = new ManejadorUsuario();
        }
        return instancia;
    }
    
    public void agregarUsuario(String nombre, String correo, String tipo) {
        Conexion conexion = Conexion.getInstancia();
        EntityManager em = conexion.getEntityManager();
        
        em.getTransaction().begin();
        
        Usuario usuario = UsuarioFactory.crearUsuario(tipo, nombre, correo);
        
        if (usuario == null) {
            em.getTransaction().rollback();
            throw new IllegalArgumentException("Tipo de usuario inválido. Solo 'lector' o 'bibliotecario'.");
        }
        em.persist(usuario); // GUARDA EN LA BD
        
        em.getTransaction().commit();
    }

    public void agregarLector(String nombre,
                              String correo,
                              String direccion,
                              DtFecha fechaRegistro,
                              boolean activo,
                              RedBiblioteca redBiblioteca) {
        Conexion conexion = Conexion.getInstancia();
        EntityManager em = conexion.getEntityManager();
        em.getTransaction().begin();
        Usuario lector = UsuarioFactory.crearLector(nombre, correo, fechaRegistro, direccion, activo, redBiblioteca);
        em.persist(lector);
        em.getTransaction().commit();
    }

    public void agregarBibliotecario(String nombre, String correo) {
        Conexion cx = Conexion.getInstancia();
        EntityManager em = cx.getEntityManager();
        em.getTransaction().begin();
        Usuario bibliotecario = UsuarioFactory.crearBibliotecario(nombre, correo);
        em.persist(bibliotecario);
        em.getTransaction().commit();
    }
    
    public boolean existeUsuario(String correo) {
        Conexion conexion = Conexion.getInstancia();
        EntityManager em = conexion.getEntityManager();
        Usuario usuario = em.find(Usuario.class, correo);
        return usuario != null;
    }
}