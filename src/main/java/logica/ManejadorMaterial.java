// ProgApProy/src/main/java/logica/ManejadorMaterial.java
package logica;

import datatypes.DtMaterial;
import datatypes.DtLibro;
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
    
    // Método genérico para agregar cualquier material
    public void agregarMaterial(Material material) {
        em.getTransaction().begin();
        em.persist(material);
        em.getTransaction().commit();
    }
    
    // Métodos específicos por tipo (similar a ManejadorUsuario)
    public void agregarLibro(Libro libro) {
        agregarMaterial(libro);
    }
    
    public void agregarArticulo(Articulo articulo) {
        agregarMaterial(articulo);
    }
    
    // Búsqueda por ID
    public Material buscarMaterial(String id) {
        return em.find(Material.class, id);
    }
    
    // Verificar si existe un material
    public boolean existeMaterial(String id) {
        Material material = buscarMaterial(id);
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
    
    // Eliminar material
    public void eliminarMaterial(String id) {
        Material material = buscarMaterial(id);
        if (material != null) {
            em.getTransaction().begin();
            em.remove(material);
            em.getTransaction().commit();
        }
    }
}