package persistencia;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Conexion {
    
    private static Conexion instancia = null;
    private EntityManagerFactory emf;
    
    private Conexion() {
        emf = Persistence.createEntityManagerFactory("bibliotecaPU");
    }
    
    public static Conexion getInstancia() {
        if (instancia == null) {
            instancia = new Conexion();
        }
        return instancia;
    }
    
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
}