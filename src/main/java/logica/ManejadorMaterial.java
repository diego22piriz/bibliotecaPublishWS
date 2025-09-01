// ProgApProy/src/main/java/logica/ManejadorMaterial.java
package logica;

import datatypes.DtMaterial;
import datatypes.DtLibro;
import datatypes.DtArticulo;
import persistencia.Conexion;
import javax.persistence.*;
import java.util.List;

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

    public void agregarLibro(DtLibro dtLibro) {
        em.getTransaction().begin();
        Libro libro = MaterialFactory.crearLibro(dtLibro);
        em.persist(libro);
        em.getTransaction().commit();
    }
    
    public void agregarArticulo(DtArticulo dtArticulo) {
        em.getTransaction().begin();
        Articulo articulo = MaterialFactory.crearArticulo(dtArticulo);
        em.persist(articulo);
        em.getTransaction().commit();
    }
    
    public boolean existeMaterial(String id) {
        Material material = em.find(Material.class, id);
        return material != null;
    }
    
    // Listar todos los materiales
    public List<Material> listarMateriales() {
        Query query = em.createQuery("SELECT m FROM Material m");
        return query.getResultList();
    }
    
    // Listar por tipo específico
    public List<Libro> listarLibros() {
        Query query = em.createQuery("SELECT l FROM Libro l");
        return query.getResultList();
    }
    
    public List<Articulo> listarArticulos() {
        Query query = em.createQuery("SELECT a FROM Articulo a");
        return query.getResultList();
    }
    
}