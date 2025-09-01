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

    // Métodos específicos (EXACTAMENTE como agregarLector y agregarBibliotecario)
    public void agregarLibro(String titulo, String cantidadPaginas) {
        em.getTransaction().begin();
        Libro libro = MaterialFactory.crearLibro(id, fechaIngreso, titulo, cantidadPaginas);
        em.persist(libro);
        em.getTransaction().commit();
    }
    
    public void agregarArticulo(String descripcion, float pesoKg, String dimensiones) {
        em.getTransaction().begin();
        Articulo articulo = MaterialFactory.crearArticulo(descripcion, pesoKg, dimensiones);
        em.persist(articulo);
        em.getTransaction().commit();
    }
    
    // Búsqueda (EXACTAMENTE como existeUsuario)
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