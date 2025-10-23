// ProgApProy/src/main/java/logica/ManejadorMaterial.java
package logica;

import datatypes.DtLibro;
import datatypes.DtArticulo;
import persistencia.Conexion;
import javax.persistence.*;
import java.util.List;
import java.util.ArrayList;

public class ManejadorMaterial {
    
    private static ManejadorMaterial instancia = null;
    private EntityManager em;
    
    private ManejadorMaterial() {
        em = Conexion.getInstancia().getEntityManager();
    }
    
    public static ManejadorMaterial getInstancia() {
        if (instancia == null) {
            instancia = new ManejadorMaterial();
        }
        return instancia;
    }

    public Material buscarMaterial(Long id) {
        return em.find(Material.class, id);
    }

    public void agregarLibro(DtLibro dtLibro) {
        em.getTransaction().begin();
        Libro libro = new Libro(
            dtLibro.getFechaIngreso(),
            dtLibro.getTitulo(),
            dtLibro.getCantidadPaginas()
        );
        em.persist(libro);
        em.getTransaction().commit();
    }
    
    public void agregarArticulo(DtArticulo dtArticulo) {
        em.getTransaction().begin();
        Articulo articulo = new Articulo(
            dtArticulo.getFechaIngreso(),
            dtArticulo.getDescripcion(),
            dtArticulo.getPesoKg(),
            dtArticulo.getDimensiones()
        );
        em.persist(articulo);
        em.getTransaction().commit();
    }
    
    public boolean existeMaterial(String id) {
        Material material = em.find(Material.class, id);
        return material != null;
    }
    
    // Listar todos los materiales con información detallada
    public ArrayList<String> listarMateriales() {
        Conexion conexion = Conexion.getInstancia();
        EntityManager em = conexion.getEntityManager();
        
        ArrayList<String> aRetornar = new ArrayList<>();
        
        // Obtener libros
        Query queryLibros = em.createQuery("SELECT l FROM Libro l");
        @SuppressWarnings("unchecked")
        List<Libro> listLibros = (List<Libro>) queryLibros.getResultList();
        for (Libro libro : listLibros) {
            String fechaIngreso;
            if (libro.getFechaIngreso() != null) {
                fechaIngreso = String.format("%02d/%02d/%04d", 
                    libro.getFechaIngreso().getDay(),
                    libro.getFechaIngreso().getMonth(),
                    libro.getFechaIngreso().getYear());
            } else {
                fechaIngreso = "No especificada";
            }
            
            String info = String.format("ID: %d | Tipo: LIBRO | Título: %s | Fecha Ingreso: %s",
                libro.getId(),
                libro.getTitulo(),
                fechaIngreso
            );
            aRetornar.add(info);
        }
        
        // Obtener artículos
        Query queryArticulos = em.createQuery("SELECT a FROM Articulo a");
        @SuppressWarnings("unchecked")
        List<Articulo> listArticulos = (List<Articulo>) queryArticulos.getResultList();
        for (Articulo articulo : listArticulos) {
            String fechaIngreso;
            if (articulo.getFechaIngreso() != null) {
                fechaIngreso = String.format("%02d/%02d/%04d", 
                    articulo.getFechaIngreso().getDay(),
                    articulo.getFechaIngreso().getMonth(),
                    articulo.getFechaIngreso().getYear());
            } else {
                fechaIngreso = "No especificada";
            }
            
            String info = String.format("ID: %d | Tipo: ARTÍCULO | Descripción: %s | Fecha Ingreso: %s",
                articulo.getId(),
                articulo.getDescripcion(),
                fechaIngreso
            );
            aRetornar.add(info);
        }
        
        return aRetornar;
    }
    
    // Listar solo los IDs de los materiales (para formularios)
    public ArrayList<String> listarIdsMateriales() {
        Conexion conexion = Conexion.getInstancia();
        EntityManager em = conexion.getEntityManager();
        
        ArrayList<String> aRetornar = new ArrayList<>();
        
        // Obtener IDs de libros
        Query queryLibros = em.createQuery("SELECT l.id FROM Libro l");
        @SuppressWarnings("unchecked")
        List<Long> listIdsLibros = (List<Long>) queryLibros.getResultList();
        for (Long id : listIdsLibros) {
            aRetornar.add(String.valueOf(id));
        }
        
        // Obtener IDs de artículos
        Query queryArticulos = em.createQuery("SELECT a.id FROM Articulo a");
        @SuppressWarnings("unchecked")
        List<Long> listIdsArticulos = (List<Long>) queryArticulos.getResultList();
        for (Long id : listIdsArticulos) {
            aRetornar.add(String.valueOf(id));
        }
        
        return aRetornar;
    }
    
    // Listar por tipo específico
    @SuppressWarnings("unchecked")
    public List<Libro> listarLibros() {
        javax.persistence.Query query = em.createQuery("SELECT l FROM Libro l");
        return query.getResultList();
    }
    
    @SuppressWarnings("unchecked")
    public List<Articulo> listarArticulos() {
        javax.persistence.Query query = em.createQuery("SELECT a FROM Articulo a");
        return query.getResultList();
    }
    
    // Obtener materiales con 3 o más préstamos pendientes ordenados por cantidad
    public List<Object[]> obtenerMaterialesConPrestamosPendientes() {
        EntityManager em = Conexion.getInstancia().getEntityManager();
        
        // Consulta para obtener materiales con 3 o más préstamos pendientes, contando la cantidad
        Query query = em.createQuery(
            "SELECT p.material.id, COUNT(p) " +
            "FROM Prestamo p " +
            "WHERE p.estado = :estadoPendiente " +
            "GROUP BY p.material.id " +
            "HAVING COUNT(p) >= 3 " +
            "ORDER BY COUNT(p) DESC"
        );
        query.setParameter("estadoPendiente", datatypes.EstadoPrestamo.PENDIENTE);
        
        @SuppressWarnings("unchecked")
        List<Object[]> resultados = query.getResultList();
        return resultados;
    }
    
    // Obtener materiales registrados en un rango de fechas
    public List<Material> obtenerMaterialesPorRangoFechas(datatypes.DtFecha fechaInicio, datatypes.DtFecha fechaFin) {
        EntityManager em = Conexion.getInstancia().getEntityManager();
        
        // Consulta para obtener materiales registrados entre las fechas especificadas
        Query query = em.createQuery(
            "SELECT m FROM Material m " +
            "WHERE m.fechaIngreso.anio >= :añoInicio AND m.fechaIngreso.anio <= :añoFin " +
            "AND ((m.fechaIngreso.anio > :añoInicio) OR " +
            "     (m.fechaIngreso.anio = :añoInicio AND m.fechaIngreso.mes > :mesInicio) OR " +
            "     (m.fechaIngreso.anio = :añoInicio AND m.fechaIngreso.mes = :mesInicio AND m.fechaIngreso.dia >= :diaInicio)) " +
            "AND ((m.fechaIngreso.anio < :añoFin) OR " +
            "     (m.fechaIngreso.anio = :añoFin AND m.fechaIngreso.mes < :mesFin) OR " +
            "     (m.fechaIngreso.anio = :añoFin AND m.fechaIngreso.mes = :mesFin AND m.fechaIngreso.dia <= :diaFin)) " +
            "ORDER BY m.fechaIngreso.anio, m.fechaIngreso.mes, m.fechaIngreso.dia"
        );
        
        query.setParameter("añoInicio", fechaInicio.getAnio());
        query.setParameter("mesInicio", fechaInicio.getMes());
        query.setParameter("diaInicio", fechaInicio.getDia());
        query.setParameter("añoFin", fechaFin.getAnio());
        query.setParameter("mesFin", fechaFin.getMes());
        query.setParameter("diaFin", fechaFin.getDia());
        
        @SuppressWarnings("unchecked")
        List<Material> resultados = query.getResultList();
        return resultados;
    }
    
    
}