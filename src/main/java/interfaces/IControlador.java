package interfaces;

import datatypes.DtLector;
import datatypes.DtBibliotecario;
import datatypes.DtLibro;
import datatypes.DtArticulo;
import datatypes.DtPrestamo;
import excepciones.UsuarioRepetidoException;
import excepciones.PrestamoDuplicadoException;
import logica.Prestamo;
import java.util.List;
import java.util.ArrayList;

public interface IControlador {
    
    // Métodos para usuarios (métodos específicos como en Controlador)
    void agregarLector(DtLector dtLector) throws UsuarioRepetidoException;
    void agregarBibliotecario(DtBibliotecario dtBibliotecario) throws UsuarioRepetidoException;
    
    // Métodos para materiales
    void agregarLibro(DtLibro dtLibro);
    void agregarArticulo(DtArticulo dtArticulo);

    // Métodos para préstamos
    void agregarPrestamo(DtPrestamo dtPrestamo) throws PrestamoDuplicadoException;
    void suspenderUsuario(String correo);

    void cambiarZona(String correo, String zona);
    
    // Métodos para obtener listas de datos
    List<String> listarLectores();
    List<String> listarBibliotecarios();
    ArrayList<String> listarMateriales();
    ArrayList<String> listarIdsMateriales();
    
    // Métodos para gestión de préstamos
    List<Prestamo> listarPrestamos();
    List<Prestamo> listarPrestamosLector(String correo);
    List<Prestamo> listarPrestamosBibliotecario(String correo);
    Prestamo buscarPrestamo(String lectorCorreo, String bibliotecarioCorreo, Long materialId);
    void actualizarPrestamo(DtPrestamo dtPrestamo);
    
    // Métodos para análisis de préstamos
    List<String> obtenerMaterialesConPrestamosPendientes();
}