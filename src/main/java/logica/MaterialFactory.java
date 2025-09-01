// ProgApProy/src/main/java/logica/MaterialFactory.java
package logica;

import datatypes.DtFecha;

public class MaterialFactory {
    
    public static Libro crearLibro(String id, DtFecha fechaIngreso, String titulo, String cantidadPaginas) {
        return new Libro(id, fechaIngreso, titulo, cantidadPaginas);
    }

    public static Articulo crearArticulo(String id, DtFecha fechaIngreso, String descripcion, float pesoKg, String dimensiones) {
        return new Articulo(id, fechaIngreso, descripcion, pesoKg, dimensiones);
    }
}