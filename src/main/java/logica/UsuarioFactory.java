package logica;

import datatypes.DtFecha;
import datatypes.RedBiblioteca;

public class UsuarioFactory {
    
    public static Usuario crearUsuario(String tipo, String nombre, String correo) {
        switch (tipo.toLowerCase()) {
            case "bibliotecario":
                return new Bibliotecario(nombre, correo);
            case "lector":
                DtFecha fechaRegistro = new DtFecha(1, 1, 2024);
                return new Lector(nombre, correo, "Sin dirección", fechaRegistro, true, RedBiblioteca.BIBLIOTECA_CENTRAL);
            default:
                return null;
        }
    }

    // Fábrica explícita por tipo (cuando se requieren datos completos)
    public static Usuario crearLector(String nombre,
                                      String correo,
                                      DtFecha fechaRegistro,
                                      String direccion,
                                      boolean activo,
                                      RedBiblioteca redBiblioteca) {
        return new Lector(nombre, correo, direccion, fechaRegistro, activo, redBiblioteca);
    }

    public static Usuario crearBibliotecario(String nombre, String correo) {
        return new Bibliotecario(nombre, correo);
    }
}
