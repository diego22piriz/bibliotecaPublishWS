package interfaces;

import datatypes.DtLector;
import datatypes.DtBibliotecario;
import datatypes.DtLibro;
import datatypes.DtArticulo;
import datatypes.DtPrestamo;
import excepciones.UsuarioRepetidoException;
import java.util.List;

public interface IControlador {
    
    // Métodos para usuarios (métodos específicos como en Controlador)
    void agregarLector(DtLector dtLector) throws UsuarioRepetidoException;
    void agregarBibliotecario(DtBibliotecario dtBibliotecario) throws UsuarioRepetidoException;
    
    // Métodos para materiales
    void agregarLibro(DtLibro dtLibro);
    void agregarArticulo(DtArticulo dtArticulo);

    // Métodos para préstamos
    void agregarPrestamo(DtPrestamo dtPrestamo);
    void suspenderUsuario(String correo);

    void cambiarZona(String correo, String zona);
    
    // Métodos para obtener listas de datos
    List<String> listarLectores();
    List<String> listarBibliotecarios();
    List<String> listarMateriales();
}