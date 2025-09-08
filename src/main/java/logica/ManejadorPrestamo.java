package logica;
import datatypes.DtPrestamo;
import javax.persistence.*;
import persistencia.Conexion;
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

    //---------------------------
    // DTO para el reporte
    public static class ZonaPrestamosDTO {
        private String zona;
        private Long cantidad;

        public ZonaPrestamosDTO(String zona, Long cantidad) {
            this.zona = zona;
            this.cantidad = cantidad;
        }

        public String getZona() { return zona; }
        public Long getCantidad() { return cantidad; }
    }

    // Obtener todas las zonas y cantidad de préstamos
    public List<ZonaPrestamosDTO> obtenerPrestamosPorZona() {
        EntityManager em = Conexion.getInstancia().getEntityManager();
        TypedQuery<Object[]> query = em.createQuery(
            "SELECT l.redBiblioteca, COUNT(p) " +
            "FROM Prestamo p JOIN p.lector l " +
            "GROUP BY l.redBiblioteca", Object[].class);

        List<Object[]> resultados = query.getResultList();
        return resultados.stream()
            .map(r -> new ZonaPrestamosDTO((String) r[0], (Long) r[1]))
            .collect(Collectors.toList());
    }

    // Obtener cantidad de préstamos para una zona específica
    public ZonaPrestamosDTO obtenerPrestamosPorZona(String zona) {
        EntityManager em = Conexion.getInstancia().getEntityManager();
        TypedQuery<Long> query = em.createQuery(
            "SELECT COUNT(p) FROM Prestamo p JOIN p.lector l WHERE l.redBiblioteca = :zona", Long.class);
        query.setParameter("zona", zona);
        Long cantidad = query.getSingleResult();
        return new ZonaPrestamosDTO(zona, cantidad);
    }

    // Obtener todas las zonas (para el selector)
    public List<String> obtenerTodasLasZonas() {
        EntityManager em = Conexion.getInstancia().getEntityManager();
        TypedQuery<String> query = em.createQuery(
            "SELECT DISTINCT l.redBiblioteca FROM Lector l", String.class);
        return query.getResultList();
    }
}
