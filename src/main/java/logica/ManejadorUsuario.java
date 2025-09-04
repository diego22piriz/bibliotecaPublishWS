package logica;

import javax.persistence.EntityManager;
import persistencia.Conexion;
import datatypes.DtFecha;
import datatypes.RedBiblioteca;
import datatypes.DtBibliotecario;
import datatypes.DtLector;
import datatypes.DtFecha;

public class ManejadorUsuario {
    
    private static ManejadorUsuario instancia = null;
    private EntityManager em;

    private ManejadorUsuario() {
        em = Conexion.getInstancia().getEntityManager();
    }

    public static ManejadorUsuario getInstancia() {
        if (instancia == null) {
            instancia = new ManejadorUsuario();
        }
        return instancia;
    }

    public void agregarLector(DtLector dtLector) {
        em.getTransaction().begin();
        Lector lector = new Lector(
            dtLector.getNombre(),
            dtLector.getCorreo(),
            dtLector.getDireccion(),
            dtLector.getFechaRegistro(),
            dtLector.getActivo(),
            dtLector.getRedBiblioteca()
        );
        em.persist(lector);
        em.getTransaction().commit();
    }

    public void agregarBibliotecario(DtBibliotecario dtBibliotecario) {
        em.getTransaction().begin();
        Bibliotecario bibliotecario = new Bibliotecario(
            dtBibliotecario.getNombre(),
            dtBibliotecario.getCorreo()
        );
        em.persist(bibliotecario);
        em.getTransaction().commit();
    }

    public boolean existeUsuario(String correo) {
        Usuario usuario = em.find(Usuario.class, correo);
        return usuario != null;
    }


    public void suspenderUsuario(String correo) {
        Conexion conexion = Conexion.getInstancia();
        EntityManager em = conexion.getEntityManager();
        em.getTransaction().begin();
        Usuario lector = em.find(Usuario.class, correo);
        ((Lector)lector).setActivo(false);
        em.getTransaction().commit();
        

    }
    public Usuario getUsuario(String correo) {
        Conexion conexion = Conexion.getInstancia();
        EntityManager em = conexion.getEntityManager();
        return em.find(Usuario.class, correo);
    }

    public Lector buscarLector(String correo) {
        return em.find(Lector.class, correo);
    }

    public Bibliotecario buscarBibliotecario(String correo) {
        return em.find(Bibliotecario.class, correo);
    }

    public void cambiarZona(String correo, String zona){
        Conexion conexion = Conexion.getInstancia();
        EntityManager em = conexion.getEntityManager();
        em.getTransaction().begin();
        Usuario usr = em.find(Usuario.class, correo);
        RedBiblioteca bib = RedBiblioteca.valueOf(zona);
        ((Lector)usr).setRedBiblioteca(bib);
        em.getTransaction().commit();
    }
}
