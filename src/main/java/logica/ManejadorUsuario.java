package logica;

import javax.persistence.EntityManager;
import persistencia.Conexion;
import datatypes.DtFecha;
import datatypes.RedBiblioteca;
import datatypes.DtBibliotecario;
import datatypes.DtLector;

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

    public void agregarLector(DtLector dtLector) {
        Conexion conexion = Conexion.getInstancia();
        EntityManager em = conexion.getEntityManager();
        em.getTransaction().begin();
        Usuario lector = UsuarioFactory.crearLector(dtLector.getNombre(), dtLector.getCorreo(), dtLector.getFechaRegistro(), dtLector.getDireccion(), dtLector.getActivo(), dtLector.getRedBiblioteca());
        em.persist(lector);
        em.getTransaction().commit();
    }

    public void agregarBibliotecario(DtBibliotecario dtBibliotecario) {
        Conexion cx = Conexion.getInstancia();
        EntityManager em = cx.getEntityManager();
        em.getTransaction().begin();
        Usuario bibliotecario = UsuarioFactory.crearBibliotecario(dtBibliotecario.getNombre(), dtBibliotecario.getCorreo());
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