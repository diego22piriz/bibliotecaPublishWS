package logica;

//import java.time.LocalDate;

import datatypes.DtFecha;

public class PrestamoFactory {

    // Método estático para crear un nuevo préstamo
    public static Prestamo crearPrestamo(Material material, Lector usuario, Bibliotecario bibliotecario, DtFecha fechaInicio, DtFecha fechaFin) {
        return new Prestamo(material, usuario, bibliotecario, fechaInicio, fechaFin);
    }
}