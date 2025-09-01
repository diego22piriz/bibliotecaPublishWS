// ProgApProy/src/main/java/logica/MaterialFactory.java
package logica;

import datatypes.DtLibro;
import datatypes.DtArticulo;

public class MaterialFactory {
    
    // Método para crear un Libro desde DTO
    public static Libro crearLibro(DtLibro dtLibro) {
        return new Libro(dtLibro.getFechaIngreso(), dtLibro.getTitulo(), dtLibro.getCantidadPaginas());
    }

    // Método para crear un Articulo desde DTO
    public static Articulo crearArticulo(DtArticulo dtArticulo) {
        return new Articulo(dtArticulo.getFechaIngreso(), dtArticulo.getDescripcion(), dtArticulo.getPesoKg(), dtArticulo.getDimensiones());
    }
}