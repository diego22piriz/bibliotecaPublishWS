package logica;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import datatypes.DtFecha;
import datatypes.DtLibro;
import persistencia.Conexion;

public class ManejadorPrestamo {

    private static ManejadorPrestamo instancia = null;
    private EntityManager em;

    private ManejadorPrestamo() {
        em = Conexion.getInstancia().getEntityManager();
    }

    public static ManejadorPrestamo getInstancia() {
        if (instancia == null) {
            instancia = new ManejadorPrestamo();
        }
        return instancia;
    }

    public void agregarPrestamo(Material material, Lector usuario, Bibliotecario bibliotecario, DtFecha fechaInicio, DtFecha fechaFin) {
        em.getTransaction().begin();
        Prestamo prestamo = PrestamoFactory.crearPrestamo(material, usuario, bibliotecario, fechaInicio, fechaFin);
        em.persist(prestamo);
        em.getTransaction().commit();
    }
} 