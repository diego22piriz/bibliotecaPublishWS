package logica;

import datatypes.DtFecha;
import datatypes.RedBiblioteca;

public class UsuarioFactory {
    
    public static Usuario crearUsuario(String tipo, String nombre, String correo) {
        switch (tipo.toLowerCase()) {
            case "bibliotecario":
                // Crear un bibliotecario con valores por defecto
                DtFecha fechaContratacion = new DtFecha(1, 1, 2024); // Fecha por defecto
                return new Bibliotecario(nombre, correo, fechaContratacion, "General");
                
            case "lector":
                // Crear un lector con valores por defecto
                DtFecha fechaRegistro = new DtFecha(1, 1, 2024); // Fecha por defecto
                return new Lector(nombre, correo, "Sin dirección", fechaRegistro, true, RedBiblioteca.BIBLIOTECA_CENTRAL);
                
            default:
                return null; // Tipo no reconocido
        }
    }
}
