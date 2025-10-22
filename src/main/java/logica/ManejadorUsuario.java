package logica;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import persistencia.Conexion;
import datatypes.RedBiblioteca;
import datatypes.DtBibliotecario;
import datatypes.DtLector;
import java.util.List;
import datatypes.DtUsuario;

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
            dtLector.getPassword(),
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
            dtBibliotecario.getCorreo(),
            dtBibliotecario.getPassword()
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
        Usuario usuario = em.find(Usuario.class, correo);
        if (usuario instanceof Lector) {
            Lector lector = (Lector) usuario;
            lector.setActivo(!lector.getActivo()); // alternar estado
        }
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
    
            // Métodos para obtener listas de usuarios
    @SuppressWarnings("unchecked")
    public List<String> listarLectores() {
        Query query = em.createQuery("SELECT l.correo FROM Lector l");
        return query.getResultList();
    }
    
    @SuppressWarnings("unchecked")
    public List<String> listarLectoresSuspendidos() {
        Query query = em.createQuery("SELECT l.correo FROM Lector l WHERE l.activo = false");
        return query.getResultList();
    }
    
    @SuppressWarnings("unchecked")
    public List<String> listarBibliotecarios() {
        Query query = em.createQuery("SELECT b.correo FROM Bibliotecario b");
        return query.getResultList();
    }

    public DtUsuario login(String correo, String password) {
        System.out.println("DEBUG - Buscando usuario con correo: " + correo);
        Usuario usuario = em.find(Usuario.class, correo);
        System.out.println("DEBUG - Usuario encontrado: " + usuario);
        
        if (usuario != null) {
            System.out.println("DEBUG - Contraseña del usuario: " + usuario.getPassword());
            System.out.println("DEBUG - Contraseña recibida: " + password);
            
            if (!usuario.getPassword().equals(password)) {
                System.out.println("DEBUG - Contraseña incorrecta");
                return null; // Contraseña incorrecta
            }
            else {
                System.out.println("DEBUG - Contraseña correcta, creando DtUsuario");
                // Si es Lector
                if (usuario instanceof Lector) {
                    Lector lector = (Lector) usuario;
                    DtLector dtLector = new DtLector(
                        lector.getNombre(),
                        lector.getCorreo(),
                        lector.getPassword(),
                        lector.getDireccion(),
                        lector.getFechaRegistro(),
                        lector.getActivo(),
                        lector.getRedBiblioteca()
                    );
                    System.out.println("DEBUG - DtLector creado: " + dtLector.getNombre());
                    return dtLector;
                }
                // Si es Bibliotecario
                if (usuario instanceof Bibliotecario) {
                    Bibliotecario bibliotecario = (Bibliotecario) usuario;
                    DtBibliotecario dtBibliotecario = new DtBibliotecario(
                        bibliotecario.getNombre(),
                        bibliotecario.getCorreo(),
                        bibliotecario.getPassword()
                    );
                    System.out.println("DEBUG - DtBibliotecario creado: " + dtBibliotecario.getNombre());
                    return dtBibliotecario;
                }
            }
        }
        System.out.println("DEBUG - Usuario no encontrado o credenciales incorrectas");
        return null; // Usuario no encontrado
    }
}
