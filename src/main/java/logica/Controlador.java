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
import java.util.ArrayList;

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
            // Crear el préstamo
            Prestamo prestamo = ManejadorPrestamo.getInstancia().CrearPrestamo(dtPrestamo);
            
            // Establecer la clave compuesta (OBLIGATORIO para entidades con clave compuesta)
            prestamo.setLectorCorreo(lector.getCorreo());
            prestamo.setBibliotecarioCorreo(bibliotecario.getCorreo());
            prestamo.setMaterialId(material.getId());
            
            // Establecer las relaciones
            prestamo.setLector(lector);
            prestamo.setBibliotecario(bibliotecario);
            prestamo.setMaterial(material);
            
            // Agregar el préstamo a las entidades relacionadas
            lector.addPrestamo(prestamo);
            bibliotecario.addPrestamo(prestamo);
            material.addPrestamo(prestamo);
            
            // Persistir el préstamo
            em.persist(prestamo);
            em.getTransaction().commit();
            JOptionPane.showMessageDialog(null, "Préstamo registrado correctamente.");
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
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
    
    public ArrayList<String> listarMateriales() {
        return manejadorMaterial.listarMateriales();
    }
    
    public ArrayList<String> listarIdsMateriales() {
        return manejadorMaterial.listarIdsMateriales();
    }
    
    // Métodos para gestión de préstamos
    public List<Prestamo> listarPrestamos() {
        return ManejadorPrestamo.getInstancia().listarPrestamos();
    }
    
    public List<Prestamo> listarPrestamosLector(String correo) {
        return ManejadorPrestamo.getInstancia().listarPrestamosLector(correo);
    }

    public List<Prestamo> listarPrestamosBibliotecario(String correo) {
        return ManejadorPrestamo.getInstancia().listarPrestamosBibliotecario(correo);
    }

    public Prestamo buscarPrestamo(String lectorCorreo, String bibliotecarioCorreo, Long materialId) {
        return ManejadorPrestamo.getInstancia().buscarPrestamo(lectorCorreo, bibliotecarioCorreo, materialId);
    }
    
    public void actualizarPrestamo(DtPrestamo dtPrestamo) {
        EntityManager em = Conexion.getInstancia().getEntityManager();
        
        // Buscar el préstamo existente
        Prestamo prestamo = ManejadorPrestamo.getInstancia().buscarPrestamo(
            dtPrestamo.getLectorCorreo(), 
            dtPrestamo.getBibliotecarioCorreo(), 
            dtPrestamo.getMaterialId()
        );
        
        if (prestamo == null) {
            throw new IllegalArgumentException("Préstamo no encontrado");
        }
        
        em.getTransaction().begin();
        try {
            // Actualizar el préstamo
            ManejadorPrestamo.getInstancia().actualizarPrestamo(prestamo, dtPrestamo);
            
            // Hacer merge para sincronizar los cambios con la base de datos
            em.merge(prestamo);
            
            em.getTransaction().commit();
            JOptionPane.showMessageDialog(null, "Préstamo actualizado correctamente.");
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Error al actualizar préstamo: " + e.getMessage(), e);
        }
    }
    
}


