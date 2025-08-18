package logica;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import persistencia.Conexion;

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
        
        if (usuario != null) {
            em.persist(usuario); // GUARDA EN LA BD
        }
        
        em.getTransaction().commit();
    }
    
    public boolean existeUsuario(String correo) {
        Conexion conexion = Conexion.getInstancia();
        EntityManager em = conexion.getEntityManager();
        Usuario usuario = em.find(Usuario.class, correo);
        return usuario != null;
    }
}