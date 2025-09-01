// ProgApProy/src/main/java/logica/MaterialFactory.java
package logica;

import datatypes.DtFecha;

public class MaterialFactory {
    
    // Método genérico con tipo
    public static Material crearMaterial(String tipo, String titulo, String autor, DtFecha fechaIngreso) {
        switch (tipo.toLowerCase()) {
            case "libro":
                return new Libro(titulo, autor, fechaIngreso, "0", "Sin ISBN");
            case "articulo":
                return new Articulo(titulo, autor, fechaIngreso, "Sin descripción", 0.0f, "Sin dimensiones");
            default:
                return null;
        }
    }

    // Fábricas explícitas por tipo
    public static Libro crearLibro(String titulo, String autor, DtFecha fechaIngreso, 
                                  String cantidadPaginas, String isbn) {
        return new Libro(titulo, autor, fechaIngreso, cantidadPaginas, isbn);
    }

    public static Articulo crearArticulo(String titulo, String autor, DtFecha fechaIngreso, 
                                        String descripcion, float pesoKg, String dimensiones) {
        return new Articulo(titulo, autor, fechaIngreso, descripcion, pesoKg, dimensiones);
    }
}