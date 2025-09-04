package logica;

import interfaces.IControlador;
import excepciones.UsuarioRepetidoException;
import datatypes.DtBibliotecario;
import datatypes.DtLector;
import datatypes.DtLibro;
import datatypes.DtPrestamo;
import datatypes.DtArticulo;
import persistencia.Conexion;
import javax.persistence.EntityManager;

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

    // Métodos para materiales
    public void agregarLibro(DtLibro dtLibro) {
        manejadorMaterial.agregarLibro(dtLibro);
    }

    public void agregarArticulo(DtArticulo dtArticulo) {
        manejadorMaterial.agregarArticulo(dtArticulo);
    }

    public void agregarPrestamo(DtPrestamo dtPrestamo) {
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

        em.getTransaction().begin();
        Prestamo prestamo = new Prestamo(material, lector, bibliotecario, dtPrestamo.getFechaSolicitud(), dtPrestamo.getFechaDevolucion());
        prestamo.setEstado(dtPrestamo.getEstado());
        // Mantener relaciones bidireccionales
        lector.addPrestamo(prestamo);
        bibliotecario.addPrestamo(prestamo);
        material.addPrestamo(prestamo);
        em.persist(prestamo);
        em.getTransaction().commit();
    }
}
