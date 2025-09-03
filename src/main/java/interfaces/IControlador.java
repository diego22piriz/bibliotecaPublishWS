package interfaces;

import datatypes.DtLector;
import datatypes.DtBibliotecario;
import datatypes.DtLibro;
import datatypes.DtArticulo;
import excepciones.UsuarioRepetidoException;

public interface IControlador {
    
    // Métodos para usuarios (métodos específicos como en Controlador)
    void agregarLector(DtLector dtLector) throws UsuarioRepetidoException;
    void agregarBibliotecario(DtBibliotecario dtBibliotecario) throws UsuarioRepetidoException;
    
    // Métodos para materiales
    void agregarLibro(DtLibro dtLibro);
    void agregarArticulo(DtArticulo dtArticulo);

    // Métodos para préstamos
    void agregarPrestamo(String usuarioCorreo, String bibliotecarioCorreo, int materialId);

    void suspenderUsuario(String correo);
}