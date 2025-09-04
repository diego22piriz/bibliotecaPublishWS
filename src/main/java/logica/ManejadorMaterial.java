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
    
    // Listar todos los materiales
    @SuppressWarnings("unchecked")
    public List<String> listarMateriales() {
        javax.persistence.Query query = em.createQuery("SELECT m FROM Material m");
        List<Material> materiales = query.getResultList();
        List<String> ids = new ArrayList<>();
        for (Material material : materiales) {
            ids.add(material.getId().toString());
        }
        return ids;
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
    
}