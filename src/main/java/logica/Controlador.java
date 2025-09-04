package logica;

import interfaces.IControlador;
import excepciones.UsuarioRepetidoException;
import excepciones.PrestamoDuplicadoException;
import datatypes.DtBibliotecario;
import datatypes.DtLector;
import javax.swing.JOptionPane;
import datatypes.DtLibro;
import datatypes.DtPrestamo;
import datatypes.DtArticulo;
import persistencia.Conexion;
import javax.persistence.EntityManager;
import java.util.List;

public class Controlador implements IControlador {
    
    private ManejadorUsuario manejadorUsuario;
    private ManejadorMaterial manejadorMaterial;
    
    public Controlador() {
        this.manejadorUsuario = ManejadorUsuario.getInstancia();
        this.manejadorMaterial = ManejadorMaterial.getInstancia();
    }

    // Métodos específicos requeridos por la UI (usuarios)
    public void agregarLector(DtLector dtLector) throws UsuarioRepetidoException {
        if (manejadorUsuario.existeUsuario(dtLector.getCorreo())) {
            throw new UsuarioRepetidoException("Ya existe un usuario con el correo: " + dtLector.getCorreo());
        }
        manejadorUsuario.agregarLector(dtLector);
    }

    public void agregarBibliotecario(DtBibliotecario dtBibliotecario) throws UsuarioRepetidoException {
        if (manejadorUsuario.existeUsuario(dtBibliotecario.getCorreo())) {
            throw new UsuarioRepetidoException("Ya existe un usuario con el correo: " + dtBibliotecario.getCorreo());
        }
        manejadorUsuario.agregarBibliotecario(dtBibliotecario);
    }

    public void suspenderUsuario(String correo) {
        if (manejadorUsuario.existeUsuario(correo)) {
            if (manejadorUsuario.getUsuario(correo).tipoUsuario().equals("Lector")) {
                manejadorUsuario.suspenderUsuario(correo);
                JOptionPane.showMessageDialog(null, "Usuario suspendido correctamente.");
            }
            else {
                JOptionPane.showMessageDialog(null, "Usuario no es un lector.");
            }
        }
        else {
            JOptionPane.showMessageDialog(null, "Usuario no existe.");
        }
    }

    public void cambiarZona(String correo, String zona){
        if (manejadorUsuario.existeUsuario(correo)) {
            if (manejadorUsuario.getUsuario(correo).tipoUsuario().equals("Lector")) {
                manejadorUsuario.cambiarZona(correo, zona);
                JOptionPane.showMessageDialog(null, "Zona cambiada correctamente.");
            }
            else {
                JOptionPane.showMessageDialog(null, "Usuario no es un lector.");
            }
        }
        else {
            JOptionPane.showMessageDialog(null, "Usuario no existe.");
        }
    }

        
       
       
    // Métodos para materiales
    public void agregarLibro(DtLibro dtLibro) {
        manejadorMaterial.agregarLibro(dtLibro);
    }

    public void agregarArticulo(DtArticulo dtArticulo) {
        manejadorMaterial.agregarArticulo(dtArticulo);
    }

    public void agregarPrestamo(DtPrestamo dtPrestamo) throws PrestamoDuplicadoException {
        ManejadorUsuario mUsuario = ManejadorUsuario.getInstancia();
        ManejadorMaterial mMat = ManejadorMaterial.getInstancia();

        EntityManager em = Conexion.getInstancia().getEntityManager();

        Lector lector = mUsuario.buscarLector(dtPrestamo.getLectorCorreo());
        if (lector == null) {
            throw new IllegalArgumentException("Lector no encontrado: " + dtPrestamo.getLectorCorreo());
        }

        Bibliotecario bibliotecario = mUsuario.buscarBibliotecario(dtPrestamo.getBibliotecarioCorreo());
        if (bibliotecario == null) {
            throw new IllegalArgumentException("Bibliotecario no encontrado: " + dtPrestamo.getBibliotecarioCorreo());
        }

        Material material = mMat.buscarMaterial(dtPrestamo.getMaterialId());
        if (material == null) {
            throw new IllegalArgumentException("Material no encontrado: id=" + dtPrestamo.getMaterialId());
        }

        // Verificar si ya existe un préstamo con la misma combinación
        javax.persistence.Query query = em.createQuery(
            "SELECT p FROM Prestamo p WHERE p.material.id = :materialId AND p.lector.correo = :lectorCorreo AND p.bibliotecario.correo = :bibliotecarioCorreo"
        );
        query.setParameter("materialId", dtPrestamo.getMaterialId());
        query.setParameter("lectorCorreo", dtPrestamo.getLectorCorreo());
        query.setParameter("bibliotecarioCorreo", dtPrestamo.getBibliotecarioCorreo());
        
        if (!query.getResultList().isEmpty()) {
            throw new PrestamoDuplicadoException("Ya existe un préstamo para este material, lector y bibliotecario");
        }

        em.getTransaction().begin();
        try {
            Prestamo prestamo = new Prestamo(material, lector, bibliotecario, dtPrestamo.getFechaSolicitud(), dtPrestamo.getFechaDevolucion());
            prestamo.setEstado(dtPrestamo.getEstado());
            // Mantener relaciones bidireccionales
            lector.addPrestamo(prestamo);
            bibliotecario.addPrestamo(prestamo);
            material.addPrestamo(prestamo);
            em.persist(prestamo);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new RuntimeException("Error al registrar préstamo: " + e.getMessage(), e);
        }
    }
    
    // Métodos para obtener listas de datos
    public List<String> listarLectores() {
        return manejadorUsuario.listarLectores();
    }
    
    public List<String> listarBibliotecarios() {
        return manejadorUsuario.listarBibliotecarios();
    }
    
    public List<String> listarMateriales() {
        return manejadorMaterial.listarMateriales();
    }
}


