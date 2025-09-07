package logica;
import datatypes.DtPrestamo;
import javax.persistence.*;
import persistencia.Conexion;
import java.util.List;

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
    
    // Actualizar préstamo
    public void actualizarPrestamo(Prestamo prestamo, DtPrestamo dtPrestamo) {
        // Actualizar fechas
        prestamo.setFechaSolicitud(dtPrestamo.getFechaSolicitud());
        prestamo.setFechaEstDev(dtPrestamo.getFechaDevolucion());
        prestamo.setEstado(dtPrestamo.getEstado());
    }
    
}
