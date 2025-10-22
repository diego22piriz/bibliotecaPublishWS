package logica;
import datatypes.DtPrestamo;
import datatypes.RedBiblioteca;
import javax.persistence.*;
import persistencia.Conexion;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ManejadorPrestamo {

    private static ManejadorPrestamo instancia = null;
    
    private ManejadorPrestamo() {
        // Constructor privado para singleton
    }
    
    public static ManejadorPrestamo getInstancia() {
        if (instancia == null) {
            instancia = new ManejadorPrestamo();
        }
        return instancia;
    }

    public Prestamo CrearPrestamo(DtPrestamo dtPrestamo) {
        Prestamo prestamo = new Prestamo(dtPrestamo.getFechaSolicitud(), dtPrestamo.getFechaDevolucion(), dtPrestamo.getEstado());
        // No manejamos transacciones aquí, se manejan en el Controlador
        return prestamo;
    }
    
    // Buscar préstamo por clave compuesta
    public Prestamo buscarPrestamo(String lectorCorreo, String bibliotecarioCorreo, Long materialId) {
        EntityManager em = Conexion.getInstancia().getEntityManager();
        
        // Usar consulta manual ya que no tenemos clase PrestamoId
        Query query = em.createQuery(
            "SELECT p FROM Prestamo p WHERE p.lectorCorreo = :lectorCorreo AND p.bibliotecarioCorreo = :bibliotecarioCorreo AND p.materialId = :materialId"
        );
        query.setParameter("lectorCorreo", lectorCorreo);
        query.setParameter("bibliotecarioCorreo", bibliotecarioCorreo);
        query.setParameter("materialId", materialId);
        
        @SuppressWarnings("unchecked")
        List<Prestamo> resultados = query.getResultList();
        return resultados.isEmpty() ? null : resultados.get(0);
    }
    
    // Listar todos los préstamos
    public List<Prestamo> listarPrestamos() {
        EntityManager em = Conexion.getInstancia().getEntityManager();
        Query query = em.createQuery("SELECT p FROM Prestamo p");
        @SuppressWarnings("unchecked")
        List<Prestamo> prestamos = query.getResultList();
        return prestamos;
    }

    // listar prestamos de un lector especifico
    public List<Prestamo> listarPrestamosLector(String correo) {
        EntityManager em = Conexion.getInstancia().getEntityManager();
        Query query = em.createQuery("SELECT p FROM Prestamo p WHERE p.lectorCorreo = :correo");
        query.setParameter("correo", correo);
        @SuppressWarnings("unchecked")
        List<Prestamo> prestamos = query.getResultList();
        return prestamos;
    }

    // listar prestamos de un bibliotecario especifico
    public List<Prestamo> listarPrestamosBibliotecario(String correo) {
        EntityManager em = Conexion.getInstancia().getEntityManager();
        Query query = em.createQuery("SELECT p FROM Prestamo p WHERE p.bibliotecarioCorreo = :correo");
        query.setParameter("correo", correo);
        @SuppressWarnings("unchecked")
        List<Prestamo> prestamos = query.getResultList();
        return prestamos;
    }
    
    // Actualizar préstamo
    public void actualizarPrestamo(Prestamo prestamo, DtPrestamo dtPrestamo) {
        // Actualizar fechas
        prestamo.setFechaSolicitud(dtPrestamo.getFechaSolicitud());
        prestamo.setFechaEstDev(dtPrestamo.getFechaDevolucion());
        prestamo.setEstado(dtPrestamo.getEstado());
    }

    // Obtener préstamos de una zona específica
    public List<Prestamo> obtenerPrestamosDeZona(String zona) {
        EntityManager em = Conexion.getInstancia().getEntityManager();
        RedBiblioteca zonaEnum = RedBiblioteca.valueOf(zona);
        Query query = em.createQuery(
            "SELECT p FROM Prestamo p JOIN p.lector l WHERE l.redBiblioteca = :zona");
        query.setParameter("zona", zonaEnum);
        @SuppressWarnings("unchecked")
        List<Prestamo> prestamos = query.getResultList();
        return prestamos;
    }

    // Obtener todas las zonas (para el selector)
    public List<String> obtenerTodasLasZonas() {
        // Devolver todos los valores del enum RedBiblioteca
        return Arrays.stream(RedBiblioteca.values())
            .map(RedBiblioteca::name)
            .collect(Collectors.toList());
    }
    
    // Cambiar estado de un préstamo
    public void cambiarEstadoPrestamo(String lectorCorreo, String bibliotecarioCorreo, Long materialId, datatypes.EstadoPrestamo nuevoEstado) {
        EntityManager em = Conexion.getInstancia().getEntityManager();
        em.getTransaction().begin();
        try {
            Prestamo prestamo = buscarPrestamo(lectorCorreo, bibliotecarioCorreo, materialId);
            if (prestamo == null) {
                throw new IllegalArgumentException("Préstamo no encontrado");
            }
            
            prestamo.setEstado(nuevoEstado);
            em.merge(prestamo);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Error al cambiar estado del préstamo: " + e.getMessage(), e);
        }
    }
}