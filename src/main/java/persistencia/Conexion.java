package persistencia;

public class Conexion {
    
    private static Conexion instancia = null;
    
    private Conexion() {}
    
    public static Conexion getInstancia() {
        if (instancia == null) {
            instancia = new Conexion();
        }
        return instancia;
    }
    
    public Object getEntityManager() {
        return null;
    }
}